package com.company.bus.loaned.common.comm.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements org.apache.ibatis.plugin.Interceptor{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	 public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	 public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin
	 * .Invocation)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();

		BoundSql boundSql = statementHandler.getBoundSql();

		MetaObject metaStatementHandler = MetaObject
				.forObject(statementHandler,DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);

		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环   
		// 可以分离出最原始的的目标类)   
		while (metaStatementHandler.hasGetter("h")) {  
			Object object = metaStatementHandler.getValue("h");  
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,   DEFAULT_OBJECT_WRAPPER_FACTORY);  
		}  
		// 分离最后一个代理对象的目标类   
		while (metaStatementHandler.hasGetter("target")) {  
			Object object = metaStatementHandler.getValue("target");  
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,   
					DEFAULT_OBJECT_WRAPPER_FACTORY);  
		} 
		
		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");

		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {

			return invocation.proceed();

		}

		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");

		Dialect.Type databaseType = null;

		try {

			databaseType = Dialect.Type.valueOf(configuration.getVariables()
					.getProperty("dialect").toUpperCase());

		} catch (Exception e) {

		}

		if (databaseType == null) {

			throw new RuntimeException(
					"the value of the dialect property in configuration.xml is not defined : "
							+ configuration.getVariables().getProperty(
									"dialect"));

		}

		Dialect dialect = null;
		
		switch(databaseType) {
			case MYSQL : dialect = new MysqlDialect(); break;
			case ORACLE : dialect = new OracleDialect(); break;
		}


		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		String sql = " select count(1) from ( " + originalSql + ") a";
		Object parameterObject = boundSql.getParameterObject();
		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		int totpage = 0;
		try {
			connection = configuration.getEnvironment().getDataSource()
					.getConnection();
			countStmt = connection.prepareStatement(sql);
			BoundSql countBS = new BoundSql(configuration, sql,
					boundSql.getParameterMappings(), parameterObject);
			setParameters(countStmt, configuration, countBS, parameterObject);
			rs = countStmt.executeQuery();
			if (rs.next()) {
				totpage = rs.getInt(1);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (countStmt != null) {
				countStmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		if (parameterObject instanceof Map) {
			((Map<String, Object>) parameterObject).put("count", totpage);
		}
		
		if(totpage == 0){
			return invocation.proceed();
		}
		
		metaStatementHandler.setValue(
				"delegate.boundSql.sql",
				dialect.getLimitString(originalSql, (rowBounds.getOffset() - 1)
						* rowBounds.getLimit(), rowBounds.getLimit()));

		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);

		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);

		if (log.isDebugEnabled()) {

			log.debug("生成分页SQL : " + boundSql.getSql());

		}

		return invocation.proceed();

	}

	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.
	 * DefaultParameterHandler DefaultParameterHandler
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setParameters(PreparedStatement ps,
			Configuration configuration, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(
											propertyName.substring(prop
													.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					if (parameterMapping.getTypeHandler() == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement ");
					}
					((TypeHandler) parameterMapping.getTypeHandler())
							.setParameter(ps, i + 1, value,
									parameterMapping.getJdbcType());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 */
	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}
}
