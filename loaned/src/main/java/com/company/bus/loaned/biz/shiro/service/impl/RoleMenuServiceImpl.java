package com.company.bus.loaned.biz.shiro.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.RoleMenuService;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageResponse;
import com.company.bus.loaned.dbaccess.dao.RoleMenuDao;
import com.company.bus.loaned.dbaccess.dao.SetFroleFmenuDao;
import com.company.bus.loaned.dbaccess.dao.SetFuncRoleDao;
import com.company.bus.loaned.dbaccess.entity.RoleMenu;
import com.company.bus.loaned.dbaccess.entity.SetFroleFmenu;
import com.company.bus.loaned.dbaccess.entity.SetFuncRole;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

	@Override
	public Pageable queryPaged(Pageable pageable, Map<String, String> params) {
		// 参数设置
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("page", pageable);
		sqlParams.putAll(params);
		// 分页结果集
		List<RoleMenu> list = roleMenuDao.queryPaged(sqlParams);
		// 符合条件的记录总数
		int totalCount = roleMenuDao.queryTotalCount(sqlParams);
		return new PageResponse(list, totalCount);
	}
	
	@Override
	public void create(SetFuncRole role, List<SetFroleFmenu> list) {
		setFuncRoleDao.insert(role);
		for(SetFroleFmenu roleMenu : list){
			setFroleFmenuDao.insert(roleMenu);
		}
	}
	
	@Override
	public void update(SetFuncRole role, List<SetFroleFmenu> list) {
		setFuncRoleDao.updateByPrimaryKeySelective(role);
		setFroleFmenuDao.deleteByRoleId(list.get(0).getFuncRoleId());
		for(SetFroleFmenu roleMenu : list){
			setFroleFmenuDao.insert(roleMenu);
		}
	}
	
	@Override
	public void delete(BigDecimal roleId) {
		setFuncRoleDao.deleteByPrimaryKey(roleId);
		setFroleFmenuDao.deleteByRoleId(roleId);
	}
	
	@Override
	public String queryRoleSeq(){
		return setFuncRoleDao.getSequences();
	}
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	@Autowired
	private SetFroleFmenuDao setFroleFmenuDao;
	@Autowired
	private SetFuncRoleDao setFuncRoleDao;

}
