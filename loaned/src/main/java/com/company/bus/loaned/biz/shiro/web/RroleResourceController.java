package com.company.bus.loaned.biz.shiro.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import com.company.bus.loaned.biz.shiro.service.RroleResourceService;
import com.company.bus.loaned.biz.shiro.service.SetResourceService;
import com.company.bus.loaned.common.comm.Constants;
import com.company.bus.loaned.common.web.controller._BaseController;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.PaginatedList;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageRequest;
import com.company.bus.loaned.dbaccess.entity.SetResource;
import com.company.bus.loaned.dbaccess.entity.SetResourceRole;
import com.company.bus.loaned.dbaccess.entity.SetRroleResource;

public class RroleResourceController extends _BaseController {
	
	
	/**
	 * @Description 显示角色管理页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView roleResourceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String _search_roleName = "_search_roleName";
		
		String roleName = this.findStringParameterValue(request,_search_roleName);

		String _search_key = null, _search_value = null;
		if (roleName != null) {
			_search_key = _search_roleName;
			_search_value = roleName;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("resourceRoleName", roleName);
		
		Pageable pageable = null;
		
		
		if(request.getParameter("pageSize") == null){
			Integer start = findIntegerParameterValue(request, Constants._page);
			pageable = new PageRequest(start == null ? 1 : start,Constants.DEFAULT_LIMIT);
		} else {
			pageable = this.findPage(request);
		}
		
		// 查询分页记录
		Pageable result = rroleResourceService.queryPaged(pageable, params);
		
		//执行分页处理
		PaginatedList paginatedList = this.doPaging(result, pageable.getPageStart(), pageable.getPageLimit());
		
		return createMAV("/mt/role/role_resource_read")
				.addObject("_search_key", _search_key)
				.addObject("_search_value", _search_value)
				.addObject("pageList", paginatedList);
	}
	
	/**
	 * @Description 显示角色创建/编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView roleResourcePersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String subBtn = this.findStringParameterValue(request, "subBtn");
		if(subBtn.equals(Constants._add)){
			return roleResourceAddDisPlay(request, response);
		} else if(subBtn.equals(Constants._update)){
			return roleResourceUpdateDisPlay(request, response);
		} else {
			return roleResourceDisplay(request, response);
		}
		
	}
	
	private ModelAndView roleResourceAddDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SetResource> resources = setResourceService.queryAll();
		return createMAV("/mt/role/role_resource_create").addObject("resources", resources);
	}
	
	
	private ModelAndView roleResourceUpdateDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception{			
		BigDecimal resourceRoleId = this.findBigDecimalParameterValue(request, "resourceRoleId");
		List<SetResource> candidateResources = setResourceService.queryCandidateResource(resourceRoleId);
		List<SetResource> selectedResources = setResourceService.querySelectedResource(resourceRoleId);	
		return createMAV("/mt/role/role_resource_update")
					.addObject("candidate_resources", candidateResources)
					.addObject("selected_resources", selectedResources)
					.addObject("roleName", this.findStringParameterValue(request, "resourceRoleName"))
					.addObject("roleId", resourceRoleId);
	}
	
	/**
	 * @Description 角色创建
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView roleResourceCreate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String idStr = this.findStringParameterValue(request,"ids");
		String roleName = this.findStringParameterValue(request,"roleName");
		
		if(StringUtils.isBlank(roleName)){
			return roleResourceAddDisPlay(request, response).addObject(Constants._message, "角色姓名为空");
		}
		
		if(StringUtils.isBlank(idStr)){
			return roleResourceAddDisPlay(request, response).addObject(Constants._message, "角色菜单主键串为空");
		}
		
		String[] ids = idStr.split(",");
		if(ArrayUtils.isEmpty(ids)){
			return roleResourceAddDisPlay(request, response).addObject(Constants._message, "角色菜单为空");
		}
		
		String roleSeq = rroleResourceService.queryRoleSeq();
		BigDecimal roleId = NumberUtils.createBigDecimal(roleSeq);
		
		List<SetRroleResource> list = new ArrayList<SetRroleResource>();
		
		for(String resourceId : ids){
			SetRroleResource setRroleResource = new SetRroleResource();
			setRroleResource.setResourceId(NumberUtils.createBigDecimal(resourceId));
			setRroleResource.setResourceRoleId(roleId);
			list.add(setRroleResource);
		}
		
		SetResourceRole role = new SetResourceRole();
		role.setResourceRoleId(roleId);
		role.setResourceRoleName(roleName);
		role.setStatus(Constants.ROLE_EFFECT);
		
		rroleResourceService.create(role, list);
		
		return roleResourceAddDisPlay(request, response).addObject(Constants._message, "创建成功");
		
	}
	
	
	/**
	 * @Description 角色编辑
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView roleResourceUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String idStr = this.findStringParameterValue(request,"ids");
		String roleName = this.findStringParameterValue(request,"roleName");
		String roleid = this.findStringParameterValue(request,"roleId");
		
		if(StringUtils.isBlank(roleid)){
			return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色主键为空");
		}
		
		if(StringUtils.isBlank(roleName)){
			return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色姓名为空");
		}
		
		if(StringUtils.isBlank(idStr)){
			return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色菜单主键串为空");
		}
		
		String[] ids = idStr.split(",");
		if(ArrayUtils.isEmpty(ids)){
			return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色菜单为空");
		}
		
		BigDecimal roleId = NumberUtils.createBigDecimal(roleid);
		
		List<SetRroleResource> list = new ArrayList<SetRroleResource>();
		
		for(String resourceId : ids){
			SetRroleResource setRroleResource = new SetRroleResource();
			setRroleResource.setResourceId(NumberUtils.createBigDecimal(resourceId));
			setRroleResource.setResourceRoleId(roleId);
			list.add(setRroleResource);
		}
		
		SetResourceRole role = new SetResourceRole();
		role.setResourceRoleId(roleId);
		role.setResourceRoleName(roleName);
		role.setStatus(Constants.ROLE_EFFECT);
		
		rroleResourceService.update(role, list);
		
		return roleResourceDisplay(request, response).addObject(Constants._message, "编辑成功");
	}
	
	/**
	 * @Description 角色删除
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView roleResourceDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String _delData = this.findStringParameterValue(request, Constants._delData);
		if(StringUtils.isBlank(_delData)){
			return roleResourceDisplay(request, response).addObject(Constants._message, "主键为空");
		}
		
		BigDecimal roleId = NumberUtils.createBigDecimal(_delData);
		
		rroleResourceService.delete(roleId);
		
		return roleResourceDisplay(request, response).addObject(Constants._message, "删除成功");
	}
	
	//注入rroleResourceService
	private RroleResourceService rroleResourceService;
	//注入setResourceService
	private SetResourceService setResourceService;

	public void setRroleResourceService(RroleResourceService rroleResourceService) {
		this.rroleResourceService = rroleResourceService;
	}

	public void setSetResourceService(SetResourceService setResourceService) {
		this.setResourceService = setResourceService;
	}

}
