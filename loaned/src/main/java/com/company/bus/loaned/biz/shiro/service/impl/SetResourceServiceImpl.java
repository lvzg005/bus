package com.company.bus.loaned.biz.shiro.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.SetResourceService;
import com.company.bus.loaned.common.comm.Constants;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageResponse;
import com.company.bus.loaned.dbaccess.dao.SetResourceDao;
import com.company.bus.loaned.dbaccess.entity.SetResource;

@Service("setResourceService")
public class SetResourceServiceImpl implements SetResourceService {

	@Override
	public Pageable queryPaged(Pageable pageable, Map<String, String> params) {
		// 参数设置
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("page", pageable);
		sqlParams.putAll(params);
		//分页结果集
		List<SetResource> list = resourceDao.queryPaged(sqlParams);
		//符合条件的记录总数
		int totalCount = resourceDao.queryTotalCount(sqlParams);
		return new PageResponse(list, totalCount);
	}

	@Override
	public void create(SetResource resource) {
		resource.setSecurityFilter(Constants._securityFilter);
		resourceDao.insert(resource);
	}

	@Override
	public void update(SetResource resource) {
		resourceDao.updateByPrimaryKeySelective(resource);
	}

	@Override
	public String querySeq() {
		return resourceDao.getSequences();
	}

	@Override
	public List<SetResource> queryAll() {
		return resourceDao.queryAll();
	}

	@Override
	public SetResource queryById(BigDecimal resourceId) {
		return resourceDao.selectByPrimaryKey(resourceId);
	}

	@Override
	public void deleteMenu(BigDecimal resourceId) {
		resourceDao.deleteByPrimaryKey(resourceId);
	}

	@Override
	public List<SetResource> queryCandidateResource(BigDecimal resourceRoleId) {
		return resourceDao.queryCandidateResource(resourceRoleId);
	}

	@Override
	public List<SetResource> querySelectedResource(BigDecimal resourceRoleId) {
		return resourceDao.querySelectedResource(resourceRoleId);
	}

	@Autowired
	private SetResourceDao resourceDao;

}
