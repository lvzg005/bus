package com.company.bus.loaned.biz.shiro.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.RroleResourceService;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageResponse;
import com.company.bus.loaned.dbaccess.dao.RroleResourceDao;
import com.company.bus.loaned.dbaccess.dao.SetResourceRoleDao;
import com.company.bus.loaned.dbaccess.dao.SetRroleResourceDao;
import com.company.bus.loaned.dbaccess.entity.RroleResource;
import com.company.bus.loaned.dbaccess.entity.SetResourceRole;
import com.company.bus.loaned.dbaccess.entity.SetRroleResource;

@Service("rroleResourceService")
public class RroleResourceServiceImpl implements RroleResourceService {

	@Override
	public Pageable queryPaged(Pageable pageable, Map<String, String> params) {
		// 参数设置
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("page", pageable);
		sqlParams.putAll(params);
		// 分页结果集
		List<RroleResource> list = roleResourceDao.queryPaged(sqlParams);
		// 符合条件的记录总数
		int totalCount = roleResourceDao.queryTotalCount(sqlParams);
		return new PageResponse(list, totalCount);
	}

	@Override
	public void create(SetResourceRole role, List<SetRroleResource> list) {
		setResourceRoleDao.insert(role);
		for(SetRroleResource roleResource : list){
			setRroleResourceDao.insert(roleResource);
		}
	}

	@Override
	public void update(SetResourceRole role, List<SetRroleResource> list) {
		setResourceRoleDao.updateByPrimaryKeySelective(role);
		BigDecimal roleId = list.get(0).getResourceRoleId();
		setRroleResourceDao.deleteByRoleId(roleId);
		for(SetRroleResource roleResource : list){
			setRroleResourceDao.insert(roleResource);
		}
	}

	@Override
	public void delete(BigDecimal roleId) {
		setResourceRoleDao.deleteByPrimaryKey(roleId);
		setRroleResourceDao.deleteByRoleId(roleId);
	}

	@Override
	public String queryRoleSeq() {
		return setResourceRoleDao.getSequences();
	}
	
	@Autowired
	private RroleResourceDao roleResourceDao;
	@Autowired
	private SetResourceRoleDao setResourceRoleDao;
	@Autowired
	private SetRroleResourceDao setRroleResourceDao;

}
