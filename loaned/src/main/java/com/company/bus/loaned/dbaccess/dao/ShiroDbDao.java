
package com.company.bus.loaned.dbaccess.dao;

import java.util.List;

import com.company.bus.loaned.dbaccess.entity.SFilter;
import com.company.bus.loaned.dbaccess.entity.SetResource;
import com.company.bus.loaned.dbaccess.entity.SetUser;

/**
 * @Description Shiro用户数据访问
 * @author 
 * @date 2013-2-5
 * @version 1.0
 */

public interface ShiroDbDao {
	
	/**
	 * @Description 通过用户名称查询用户信息
	 * @param username 用户名称
	 * @return
	 * @author 
	 */
	SetUser findByUserName(String username);
	
	/**
	 * @Description 通过用户名称查询用户资源权限
	 * @param username 用户名称
	 * @return
	 * @author 
	 */
	List<SetResource> findResourceRolesByUserName(String username);
	
	
	/**
	 * @Description 查询所有过滤器
	 * @return
	 * @author 
	 */
	List<SFilter> findAllFilters();

}
