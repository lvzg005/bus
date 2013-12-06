package com.company.bus.loaned.common.comm.mybatis;

public class MysqlDialect extends Dialect{

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect
				.append(" select row_.*  from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_  limit  ").append(offset)
				.append(" , ").append(limit);
		return pagingSelect.toString();
	}
}
