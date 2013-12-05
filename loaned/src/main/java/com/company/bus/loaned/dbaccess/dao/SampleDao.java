package com.company.bus.loaned.dbaccess.dao;

import java.util.List;
import java.util.Map;

import com.company.bus.loaned.dbaccess.entity.Sample;
import com.company.bus.loaned.dbaccess.entity.SampleAccount;

/**
 * @Description 样例DAO
 * @author 
 * @date 2013-4-10
 * @version 1.0
 */
public interface SampleDao {
	
	
	/****************** crud part **************************/
	/**
	 * @Description 插入新记录
	 * @param account
	 * @author 
	 */
	void accInsert(SampleAccount account);
	
	/**
	 * @Description 更新记录
	 * @param account
	 * @author 
	 */
	void accUpdate(SampleAccount account);
	
	/**
	 * @Description 删除记录
	 * @param userId
	 * @author 
	 */
	void accDelete(String userId);
	
	/**
	 * @Description 查询分页记录
	 * @param params
	 * @return
	 * @author 
	 */
	List<SampleAccount> accQueryPaged(Map<String, Object> params);
	
	/**
	 * @Description 查询记录总数
	 * @param params
	 * @return
	 * @author 
	 */
	int accQueryTotalCount(Map<String, Object> params);
	
	
	/**
	 * @Description 通过主键查询记录
	 * @param id
	 * @return
	 * @author 
	 */
	SampleAccount accQueryById(String id);
	
	/***************** RESTful part ************************/
	/**
	 * @Description 持久化操作
	 * @param sample
	 * @return
	 * @author 
	 */
	int insert(Sample sample);

	/**
	 * @Description 查询操作
	 * @param id
	 * @return
	 * @author 
	 */
	Sample selectByPrimaryKey(String id);

}
