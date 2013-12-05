package com.company.bus.loaned.common.comm.shiro.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.ShiroException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.company.bus.loaned.biz.shiro.web.filter.CaptchaFormAuthenticationFilter;
import com.company.bus.loaned.biz.shiro.web.filter.CustomRolesAuthorizationFilter;
import com.company.bus.loaned.common.comm.shiro.service.ShiroService;

/**
 * @Description 
 * @author 
 * @date 2013-2-27
 * @version 1.0
 */
public class DefaultShiroService implements ShiroService, InitializingBean {

	@Override
	public synchronized void addSecurityFilter(String urlPattern, String[] roles) {
		//获取身份验证过滤器的全局匹配路径
		Map<String, Object> mapAuthenticationAppliedPaths = captchaFormAuthenticationFilter.getAppliedPaths();
		//判断匹配路径是否存在
		if(mapAuthenticationAppliedPaths.containsKey(urlPattern)){
			throw new ShiroException(String.format("cannot add authentication filter to applied paths dynamically as existing url pattern [%s]", urlPattern));
		}
		
		//克隆身份验证过滤器的全局匹配路径
		Map<String, Object> originalAuthenticationMap = (Map<String, Object>)((LinkedHashMap<String, Object>)mapAuthenticationAppliedPaths).clone();		
		//清空原身份验证过滤器的全局匹配路径
		mapAuthenticationAppliedPaths.clear();
		//将新插入的匹配路径放置于有序map的第一个位置
		mapAuthenticationAppliedPaths.put(urlPattern, null);
		mapAuthenticationAppliedPaths.putAll(originalAuthenticationMap);
		
		
		//获取角色授权的全局匹配路径
		Map<String, Object> mapAuthorizationAppliedPaths = customRolesAuthorizationFilter.getAppliedPaths();
		//判断匹配路径是否存在
		if(mapAuthorizationAppliedPaths.containsKey(urlPattern)){
			throw new ShiroException(String.format("cannot add authorization filter to applied paths dynamically as existing url pattern [%s]", urlPattern));
		}
		
		//克隆角色授权过滤器的全局匹配路径
		Map<String, Object> originalAuthorizationMap = (Map<String, Object>)((LinkedHashMap<String, Object>)mapAuthorizationAppliedPaths).clone();		
		//清空原角色授权过滤器的全局匹配路径
		mapAuthorizationAppliedPaths.clear();
		//将新插入的匹配路径放置于有序map的第一个位置
		mapAuthorizationAppliedPaths.put(urlPattern, roles);
		mapAuthorizationAppliedPaths.putAll(originalAuthorizationMap);
	}

	@Override
	public synchronized void deleteSecurityFilter(String urlPattern) {
		//获取身份验证过滤器的全局匹配路径
		Map<String, Object> mapAuthenticationAppliedPaths = captchaFormAuthenticationFilter.getAppliedPaths();
		//判断匹配路径是否存在
		if(!mapAuthenticationAppliedPaths.containsKey(urlPattern)){
			throw new ShiroException(String.format("cannot delete authentication filter to applied paths dynamically as unexisting url pattern [%s]", urlPattern));
		}
		mapAuthenticationAppliedPaths.remove(urlPattern);
			
		//获取角色授权的全局匹配路径
		Map<String, Object> mapAuthorizationAppliedPaths = customRolesAuthorizationFilter.getAppliedPaths();
		//判断匹配路径是否存在
		if(!mapAuthorizationAppliedPaths.containsKey(urlPattern)){
			throw new ShiroException(String.format("cannot delete authorization filter to applied paths dynamically as unexisting url pattern [%s]", urlPattern));
		}
		mapAuthorizationAppliedPaths.remove(urlPattern);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(captchaFormAuthenticationFilter);
		Assert.notNull(customRolesAuthorizationFilter);
	}
	
	private CustomRolesAuthorizationFilter customRolesAuthorizationFilter;
	
	private CaptchaFormAuthenticationFilter captchaFormAuthenticationFilter;

	/**
	 * @param customRolesAuthorizationFilter the customRolesAuthorizationFilter to set
	 */
	public void setCustomRolesAuthorizationFilter(
			CustomRolesAuthorizationFilter customRolesAuthorizationFilter) {
		this.customRolesAuthorizationFilter = customRolesAuthorizationFilter;
	}

	public void setCaptchaFormAuthenticationFilter(
			CaptchaFormAuthenticationFilter captchaFormAuthenticationFilter) {
		this.captchaFormAuthenticationFilter = captchaFormAuthenticationFilter;
	}

	
}
