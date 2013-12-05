package com.company.bus.loaned.biz.shiro.web.filter;

import java.util.Map;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	/**
	 * @Description 获取应用到的路径
	 * @return
	 * @author 
	 */
	public Map<String, Object> getAppliedPaths(){
		return this.appliedPaths;
	}
}
