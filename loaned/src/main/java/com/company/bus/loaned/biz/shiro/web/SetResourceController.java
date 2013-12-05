package com.company.bus.loaned.biz.shiro.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import com.company.bus.loaned.biz.shiro.service.SetResourceService;
import com.company.bus.loaned.common.comm.Constants;
import com.company.bus.loaned.common.comm.shiro.service.ShiroService;
import com.company.bus.loaned.common.support.BusinessUtil;
import com.company.bus.loaned.common.web.controller._BaseController;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.PaginatedList;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageRequest;
import com.company.bus.loaned.dbaccess.entity.SetResource;

public class SetResourceController extends _BaseController {
	
	/**
	 * @Description 显示资源管理页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView resourceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String _search_url = "_search_url";
		
		String urlPattern = this.findStringParameterValue(request,_search_url);

		String _search_key = null, _search_value = null;
		if (_search_url != null) {
			_search_key = _search_url;
			_search_value = urlPattern;
		} 

		Map<String, String> params = new HashMap<String, String>();
		params.put("urlPattern", urlPattern);
		
		Pageable pageable = null;
		
		
		if(request.getParameter("pageSize") == null){
			Integer start = findIntegerParameterValue(request, Constants._page);
			pageable = new PageRequest(start == null ? 1 : start,Constants.DEFAULT_LIMIT);
		} else {
			pageable = this.findPage(request);
		}
		
		// 查询分页记录
		Pageable result = setResourceService.queryPaged(pageable, params);
		
		//执行分页处理
		PaginatedList paginatedList = this.doPaging(result, pageable.getPageStart(), pageable.getPageLimit());
		
		return createMAV("/mt/resource/resource_read")
				.addObject("_search_key", _search_key)
				.addObject("_search_value", _search_value)
				.addObject("pageList", paginatedList);
	}
	
	
	/**
	 * @Description 显示创建资源页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView resourceCreateDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return createMAV("/mt/resource/resource_create");
	}
	
	/**
	 * @Description 创建资源页面提交
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView resourceCreate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		SetResource resource =  this.bindModel(request, SetResource.class);
		if(resource == null){
			return resourceCreateDisplay(request, response).addObject(Constants._message, "创建的资源信息为空");
		}
		
		String seq = setResourceService.querySeq();
		
		if(StringUtils.isBlank(seq)){
			return resourceCreateDisplay(request, response).addObject(Constants._message, "主键生成错误");
		}
		
		resource.setResourceId(NumberUtils.createBigDecimal(seq));
		
		resource.setStatus(Constants.RESOURCE_EFFECT);
		
		setResourceService.create(resource);
		
		String urlPattern = resource.getUrlPattern();
		String[] roles = new String[]{String.valueOf(resource.getResourceId())};
		shiroService.addSecurityFilter(urlPattern, roles);
		
		return resourceCreateDisplay(request, response).addObject(Constants._message, "创建成功");
	}
	
	public ModelAndView resourceDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String _delData = this.findStringParameterValue(request, Constants._delData);
		if(StringUtils.isBlank(_delData)){
			return resourceDisplay(request, response).addObject(Constants._message, "主键为空");
		}
		
		BigDecimal resourceId = NumberUtils.createBigDecimal(_delData);
		
		SetResource resource = setResourceService.queryById(resourceId);
		setResourceService.deleteMenu(resourceId);
		
		String urlPattern = resource.getUrlPattern();
		shiroService.deleteSecurityFilter(urlPattern);
		
		return resourceDisplay(request, response).addObject(Constants._message, "删除成功");
	}
	
	/**
	 * @Description 更新建资源显示
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView resourcePersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String _storage = this.findStringParameterValue(request, Constants._storage);
		LinkedHashMap<String, String> selectArr = BusinessUtil.getStorage(_storage);
		
		if(selectArr == null || selectArr.isEmpty()){
			return resourceDisplay(request, response);
		}
		//选中记录的主键
		String menuId = selectArr.get(Constants._col1);
		//根据主键查询对应的资源信息
		SetResource resource = setResourceService.queryById(NumberUtils.createBigDecimal(menuId));
		
		if(resource != null) {
			return createMAV("/mt/resource/resource_update").addObject("setResource", resource);
		}
		
		return resourceDisplay(request, response);
	}
	
	
	/**
	 * @Description 编辑资源页面提交
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView resourceUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		SetResource resource =  this.bindModel(request, SetResource.class);
		
		BigDecimal resourceId = resource.getResourceId();
		
		SetResource lastResource = setResourceService.queryById(resourceId);
		
		setResourceService.update(resource);
		String[] roles = new String[]{String.valueOf(resourceId)};
		shiroService.deleteSecurityFilter(lastResource.getUrlPattern());
		shiroService.addSecurityFilter(resource.getUrlPattern(), roles);
		return resourcePersistenceDisplay(request, response).addObject(Constants._message, "编辑成功");
	}
	
	//注入shiroService
	private ShiroService shiroService;
	//注入setResourceService
	private SetResourceService setResourceService;


	public void setSetResourceService(SetResourceService setResourceService) {
		this.setResourceService = setResourceService;
	}


	public void setShiroService(ShiroService shiroService) {
		this.shiroService = shiroService;
	}

}
