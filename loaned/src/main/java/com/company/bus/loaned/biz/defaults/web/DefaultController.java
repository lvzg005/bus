package com.company.bus.loaned.biz.defaults.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.company.bus.loaned.common.web.controller._BaseController;

/**
 * @Description 首页控制器
 * @author 
 * @date 
 * @version 1.0
 */
public class DefaultController extends _BaseController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return createMAV("default");
	}
	
}
