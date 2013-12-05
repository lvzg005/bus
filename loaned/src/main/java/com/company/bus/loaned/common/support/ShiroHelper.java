package com.company.bus.loaned.common.support;

import org.apache.shiro.SecurityUtils;

import com.company.bus.loaned.biz.shiro.ShiroDbRealm.ShiroUser;

/**
 * @Description shiro框架帮助类
 * @author 
 * @date 2013-3-21
 * @version 1.0
 */
public class ShiroHelper {
	
	/**
	 * @Description 获取当前登录用户名称
	 * @return
	 * @author 
	 */
	public static String getUsername(){
		ShiroUser su = ShiroUser.class.cast(SecurityUtils.getSubject().getPrincipal());
		return su == null ? null : su.getUserName();
	}
}
