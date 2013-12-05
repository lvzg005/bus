package com.company.bus.loaned.biz.shiro.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.company.bus.loaned.common.web.controller._BaseController;

/**
 * @Description 未授权跳转
 * @author 
 * @date 2013-2-5
 * @version 1.0
 */
public class UnauthorizedController extends _BaseController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return createMAV("errors/403");
	}
}
