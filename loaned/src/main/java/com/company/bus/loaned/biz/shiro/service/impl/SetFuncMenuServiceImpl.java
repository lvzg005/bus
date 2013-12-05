package com.company.bus.loaned.biz.shiro.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.SetFuncMenuService;
import com.company.bus.loaned.common.comm.Constants;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageResponse;
import com.company.bus.loaned.dbaccess.dao.SetFuncMenuDao;
import com.company.bus.loaned.dbaccess.entity.SetFuncMenu;

@Service("setFuncMenuService")
public class SetFuncMenuServiceImpl implements SetFuncMenuService {

	@Override
	public Pageable queryPaged(Pageable pageable, Map<String, String> params) {
		// 参数设置
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("page", pageable);
		sqlParams.putAll(params);
		//分页结果集
		List<SetFuncMenu> list = menuDao.queryPaged(sqlParams);
		//符合条件的记录总数
		int totalCount = menuDao.queryTotalCount(sqlParams);
		return new PageResponse(list, totalCount);
	}

	@Override
	public void create(SetFuncMenu menu) {
		if(menu.getPmenuId().compareTo(new BigDecimal(-1)) != 0){
			SetFuncMenu pmenu = new SetFuncMenu();
			pmenu.setMenuId(menu.getPmenuId());
			pmenu.setIsLeaf(Constants.IS_NOT_LEAF);
			menuDao.updateByPrimaryKeySelective(pmenu);
		}
		menuDao.insert(menu);
	}

	@Override
	public void update(SetFuncMenu menu) {
		menuDao.updateByPrimaryKeySelective(menu);
	}

	@Override
	public String querySeq() {
		return menuDao.getSequences();
	}

	@Override
	public List<SetFuncMenu> queryAll() {
		return menuDao.queryAll();
	}

	@Override
	public SetFuncMenu queryById(BigDecimal menuId) {
		return menuDao.selectByPrimaryKey(menuId);
	}

	@Override
	public void deleteMenu(Map<String, String> params) {
		menuDao.deleteByMenuCode(params);
	}
	
	@Override
	public List<SetFuncMenu> queryCandidateResource(BigDecimal roleId) {
		return menuDao.queryCandidateResource(roleId);
	}

	@Override
	public List<SetFuncMenu> querySelectedResource(BigDecimal roleId) {
		return menuDao.querySelectedResource(roleId);
	}
	
	
	@Autowired
	private SetFuncMenuDao menuDao;
}
