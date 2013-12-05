package com.company.bus.loaned.dbaccess.dao;

import java.util.List;
import java.util.Map;

import com.company.bus.loaned.dbaccess.entity.RroleResource;

/**
 * @Description 资源角色管理数据查询接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */

public interface RroleResourceDao {
	
	/**
	 * @Description 查询分页记录
	 * @param params
	 * @return 
	 * @author 
	 */
	List<RroleResource> queryPaged(Map<String, Object> params);
	
	
	/**
	 * @Description 查询记录总数
	 * @param params
	 * @return
	 * @author 
	 */
	int queryTotalCount(Map<String, Object> params);

}
