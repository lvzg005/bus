package com.company.bus.loaned.biz.shiro.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import com.company.bus.loaned.biz.shiro.service.UserGroupService;
import com.company.bus.loaned.common.comm.Constants;
import com.company.bus.loaned.common.web.controller._BaseController;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.PaginatedList;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageRequest;
import com.company.bus.loaned.dbaccess.entity.SetRoleGroup;
import com.company.bus.loaned.dbaccess.entity.SetUser;
import com.company.bus.loaned.dbaccess.entity.SetUserRgroup;


public class UserGroupController extends _BaseController {
	
	
	/**
	 * @Description 显示用户组管理页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView userGroupDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String _search_userName = "_search_userName";
		
		log.info("==========打印获取到的搜索参数==========");
		String userName  = this.findStringParameterValue(request,_search_userName);
		log.info("search menuName: {}", new Object[] { userName });

		String _search_key = null, _search_value = null;
		if (userName != null) {
			_search_key = _search_userName;
			_search_value = userName;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", userName);
		
		Pageable pageable = null;
		
		
		if(request.getParameter("pageSize") == null){
			Integer start = findIntegerParameterValue(request, Constants._page);
			pageable = new PageRequest(start == null ? 1 : start,Constants.DEFAULT_LIMIT);
		} else {
			pageable = this.findPage(request);
		}
		
		// 查询分页记录
		Pageable result = userGroupService.queryPaged(pageable, params);
		
		//执行分页处理
		PaginatedList paginatedList = this.doPaging(result, pageable.getPageStart(), pageable.getPageLimit());
		
		return createMAV("/mt/user/user_read")
				.addObject("_search_key", _search_key)
				.addObject("_search_value", _search_value)
				.addObject("pageList", paginatedList);
	}
	
	/**
	 * @Description 显示角色组创建/编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView userPersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String subBtn = this.findStringParameterValue(request, "subBtn");
		if(subBtn.equals(Constants._add)){
			return userAddDisPlay(request, response);
		} else if(subBtn.equals(Constants._update)){
			return userUpdateDisPlay(request, response);
		}  else {
			return userGroupDisplay(request, response);
		}
		
	}
	
	private ModelAndView userAddDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SetRoleGroup> groups = userGroupService.queryAll();
		return createMAV("/mt/user/user_create").addObject("groups", groups);
	}
	
	private ModelAndView userUpdateDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		BigDecimal userId = this.findBigDecimalParameterValue(request, "userId");
		List<SetRoleGroup> candidateResources = userGroupService.queryCandidateResource(userId);
		List<SetRoleGroup> selectedResources = userGroupService.querySelectedResource(userId);
		return createMAV("/mt/user/user_update")
					.addObject("candidate_resources", candidateResources)
					.addObject("selected_resources", selectedResources)
					.addObject("userName", this.findStringParameterValue(request, "userName"))
					.addObject("status", this.findStringParameterValue(request, "status"))
					.addObject("password", this.findStringParameterValue(request, "password"))
					.addObject("userId", userId);
	}
	
	/**
	 * @Description 角色组创建
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView userGroupCreate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String gids = this.findStringParameterValue(request,"gids");
		String userName = this.findStringParameterValue(request,"userName");
		String password = this.findStringParameterValue(request,"password");
		String status = this.findStringParameterValue(request,"status");
		
		if(StringUtils.isBlank(userName)){
			return userAddDisPlay(request, response).addObject(Constants._message, "用户名称为空");
		}
		
		Integer isExist = userGroupService.queryUserExist(userName);//查询用户名是否存在
		if(isExist > 0) {
			return userAddDisPlay(request, response).addObject(Constants._message, "用户名已存在");
		}
		
		if(StringUtils.isBlank(password)){
			return userAddDisPlay(request, response).addObject(Constants._message, "密码为空");
		}
		if(StringUtils.isBlank(status)){
			return userAddDisPlay(request, response).addObject(Constants._message, "用户状态为空");
		}
		
		
		if(StringUtils.isBlank(gids)){
			return userAddDisPlay(request, response).addObject(Constants._message, "角色组主键串为空");
		}
		
		String[] ids = gids.split(",");
		if(ArrayUtils.isEmpty(ids)){
			return userAddDisPlay(request, response).addObject(Constants._message, "角色组菜单为空");
		}
		
		String userSeq = userGroupService.queryUserSeq();
		BigDecimal userId = NumberUtils.createBigDecimal(userSeq);
		
		List<SetUserRgroup> list = new ArrayList<SetUserRgroup>();
		
		for(String rgroupId : ids){
			SetUserRgroup userGroup = new SetUserRgroup();
			userGroup.setRgroupId(NumberUtils.createBigDecimal(rgroupId));
			userGroup.setUserId(userId);
			list.add(userGroup);
		}
		
		SetUser user = new SetUser();
		user.setUserId(userId);
		user.setUsername(userName);
		user.setPassword(DigestUtils.md5Hex(password));
		user.setStatus(status);
		
		userGroupService.create(user, list);
		
		return userAddDisPlay(request, response).addObject(Constants._message, "创建成功");
	}
	
	
	/**
	 * @Description 角色组编辑
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 
	 */
	public ModelAndView userGroupUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String gids = this.findStringParameterValue(request,"gids");
		String userName = this.findStringParameterValue(request,"userName");
		String password = this.findStringParameterValue(request,"password");
		String status = this.findStringParameterValue(request,"status");
		BigDecimal userId = this.findBigDecimalParameterValue(request, "userId");
		
		if(userId == null) {
			return userUpdateDisPlay(request, response).addObject(Constants._message, "用户主键为空");
		}
		
		if(StringUtils.isBlank(userName)){
			return userUpdateDisPlay(request, response).addObject(Constants._message, "用户名称为空");
		}
		if(StringUtils.isBlank(password)){
			return userUpdateDisPlay(request, response).addObject(Constants._message, "密码为空");
		}
		if(StringUtils.isBlank(status)){
			return userUpdateDisPlay(request, response).addObject(Constants._message, "用户状态为空");
		}
		
		
		if(StringUtils.isBlank(gids)){
			return userUpdateDisPlay(request, response).addObject(Constants._message, "角色组主键串为空");
		}
		
		String[] ids = gids.split(",");
		if(ArrayUtils.isEmpty(ids)){
			return userUpdateDisPlay(request, response).addObject(Constants._message, "角色组菜单为空");
		}
		
		SetUser user = userGroupService.queryById(userId);
		
		List<SetUserRgroup> list = new ArrayList<SetUserRgroup>();
		
		for(String rgroupId : ids){
			SetUserRgroup userGroup = new SetUserRgroup();
			userGroup.setRgroupId(NumberUtils.createBigDecimal(rgroupId));
			userGroup.setUserId(userId);
			list.add(userGroup);
		}
		
		user.setUsername(userName);
		if(!user.getPassword().equals(password)) {
			user.setPassword(DigestUtils.md5Hex(password));
		}
		user.setStatus(status);
		
		userGroupService.update(user, list);
		
		return userGroupDisplay(request, response).addObject(Constants._message, "编辑成功");
	}
	
	
	public ModelAndView userGroupDelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		BigDecimal userId = this.findBigDecimalParameterValue(request, Constants._delData);
		if(userId == null){
			return userGroupDisplay(request, response).addObject(Constants._message, "主键为空");
		}
		userGroupService.delete(userId);
		return userGroupDisplay(request, response).addObject(Constants._message, "删除成功");
	}
	
	//注入userGroupService
	private UserGroupService userGroupService;

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
}
