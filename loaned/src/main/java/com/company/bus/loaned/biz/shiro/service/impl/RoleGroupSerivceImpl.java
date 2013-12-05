package com.company.bus.loaned.biz.shiro.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.RoleGroupSerivce;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageResponse;
import com.company.bus.loaned.dbaccess.dao.RoleGroupDao;
import com.company.bus.loaned.dbaccess.dao.SetFuncRoleDao;
import com.company.bus.loaned.dbaccess.dao.SetResourceRoleDao;
import com.company.bus.loaned.dbaccess.dao.SetRgroupFroleDao;
import com.company.bus.loaned.dbaccess.dao.SetRgroupRroleDao;
import com.company.bus.loaned.dbaccess.dao.SetRoleGroupDao;
import com.company.bus.loaned.dbaccess.entity.RoleGroup;
import com.company.bus.loaned.dbaccess.entity.SetFuncRole;
import com.company.bus.loaned.dbaccess.entity.SetResourceRole;
import com.company.bus.loaned.dbaccess.entity.SetRgroupFrole;
import com.company.bus.loaned.dbaccess.entity.SetRgroupRrole;
import com.company.bus.loaned.dbaccess.entity.SetRoleGroup;

@Service("roleGroupSerivce")
public class RoleGroupSerivceImpl implements RoleGroupSerivce {

	@Override
	public String queryGroupSeq() {
		return setRoleGroupDao.getSequences();
	}
	
	
	@Override
	public Pageable queryPaged(Pageable pageable, Map<String, String> params) {
		// 参数设置
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("page", pageable);
		sqlParams.putAll(params);
		// 分页结果集
		List<RoleGroup> list = roleGroupDao.queryPaged(sqlParams);
		// 符合条件的记录总数
		int totalCount = roleGroupDao.queryTotalCount(sqlParams);
		return new PageResponse(list, totalCount);
	}

	@Override
	public void create(SetRoleGroup roleGroup, List<SetRgroupFrole> frList, List<SetRgroupRrole> rrList) {
		setRoleGroupDao.insert(roleGroup);
		if(frList != null && !frList.isEmpty()){
			for(SetRgroupFrole rgroupf : frList){
				setRgroupFroleDao.insert(rgroupf);
			}
		}
		if(rrList != null && !rrList.isEmpty()){
			for(SetRgroupRrole rgroupr : rrList){
				setRgroupRroleDao.insert(rgroupr);
			}
		}
	}
	
	@Override
	public void update(SetRoleGroup roleGroup, List<SetRgroupFrole> frList, List<SetRgroupRrole> rrList, BigDecimal rgroupId) {
		setRoleGroupDao.updateByPrimaryKeySelective(roleGroup);
		setRgroupFroleDao.deleteByGroupId(rgroupId);
		setRgroupRroleDao.deleteByGroupId(rgroupId);
		if(frList != null && !frList.isEmpty()){
			for(SetRgroupFrole rgroupf : frList){
				setRgroupFroleDao.insert(rgroupf);
			}
		}
		if(rrList != null && !rrList.isEmpty()){
			for(SetRgroupRrole rgroupr : rrList){
				setRgroupRroleDao.insert(rgroupr);
			}
		}
	}
	
	@Override
	public void delete(BigDecimal rgroupId) {
		setRoleGroupDao.deleteByPrimaryKey(rgroupId);
		setRgroupFroleDao.deleteByGroupId(rgroupId);
		setRgroupRroleDao.deleteByGroupId(rgroupId);
	}

	@Override
	public List<SetFuncRole> queryFrAll() {
		return setFuncRoleDao.queryAll();
	}

	@Override
	public List<SetResourceRole> queryRrAll() {
		return setResourceRoleDao.queryAll();
	}
	
	
	@Override
	public List<SetFuncRole> queryFCandidateResource(BigDecimal rgroupId) {
		return setFuncRoleDao.queryCandidateResource(rgroupId);
	}
	@Override
	public List<SetFuncRole> queryFSelectedResource(BigDecimal rgroupId) {
		return setFuncRoleDao.querySelectedResource(rgroupId);
	}
	
	@Override
	public List<SetResourceRole> queryRCandidateResource(BigDecimal rgroupId) {
		return setResourceRoleDao.queryCandidateResource(rgroupId);
	}
	@Override
	public List<SetResourceRole> queryRSelectedResource(BigDecimal rgroupId) {
		return setResourceRoleDao.querySelectedResource(rgroupId);
	}
	
	
	@Autowired
	private RoleGroupDao roleGroupDao;
	@Autowired
	private SetRoleGroupDao setRoleGroupDao;
	@Autowired
	private SetRgroupFroleDao setRgroupFroleDao;
	@Autowired
	private SetRgroupRroleDao setRgroupRroleDao;
	@Autowired
	private SetFuncRoleDao setFuncRoleDao;
	@Autowired
	private SetResourceRoleDao setResourceRoleDao;

}
