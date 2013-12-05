package com.company.bus.loaned.biz.shiro.service;

import java.util.List;

import com.company.bus.loaned.dbaccess.entity.SetFuncMenu;

/**
 * @Description 
 * @author 
 * @date 2013-2-27
 * @version 1.0
 */
public interface DefaultService {
	/**
	 * @Description 获取所有有效菜单
	 * @return
	 * @author 
	 */
	List<SetFuncMenu> findAllMenu();
	
	/**
	 * @Description 通过用户名称查找有效菜单
	 * @param username
	 * @return
	 * @author 
	 */
	List<SetFuncMenu> findMenuByUserName(String username);
}
