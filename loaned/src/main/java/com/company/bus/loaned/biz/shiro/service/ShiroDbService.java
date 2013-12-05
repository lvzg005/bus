package com.company.bus.loaned.biz.shiro.service;

import java.util.List;

import com.company.bus.loaned.dbaccess.entity.SFilter;
import com.company.bus.loaned.dbaccess.entity.SetResource;
import com.company.bus.loaned.dbaccess.entity.SetUser;

/**
 * @Description 
 * @author 
 * @date 2013-2-5
 * @version 1.0
 */
public interface ShiroDbService {
	/**
	 * @Description 通过用户名称查询用户信息
	 * @param userName 用户名称
	 * @return
	 * @author 
	 */
	SetUser findByUserName(String userName);
	
	/**
	 * @Description 通过用户名称查询用户资源权限
	 * @param userName 用户名称
	 * @return
	 * @author 
	 */
	List<SetResource> findResourceRolesByUserName(String userName);
	
	/**
	 * @Description 查询所有过滤器
	 * @return
	 * @author 
	 */
	List<SFilter> findAllFilters();
}
