package com.company.bus.loaned.biz.shiro.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.UserGroupService;
import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.common.web.controller.pagination.impl.PageResponse;
import com.company.bus.loaned.dbaccess.dao.SetRoleGroupDao;
import com.company.bus.loaned.dbaccess.dao.SetUserDao;
import com.company.bus.loaned.dbaccess.dao.SetUserRgroupDao;
import com.company.bus.loaned.dbaccess.dao.UserGroupDao;
import com.company.bus.loaned.dbaccess.entity.SetRoleGroup;
import com.company.bus.loaned.dbaccess.entity.SetUser;
import com.company.bus.loaned.dbaccess.entity.SetUserRgroup;
import com.company.bus.loaned.dbaccess.entity.UserGroup;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {

	@Override
	public Pageable queryPaged(Pageable pageable, Map<String, String> params) {
		// 参数设置
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("page", pageable);
		sqlParams.putAll(params);
		// 分页结果集
		List<UserGroup> list = userGroupDao.queryPaged(sqlParams);
		// 符合条件的记录总数
		int totalCount = userGroupDao.queryTotalCount(sqlParams);
		return new PageResponse(list, totalCount);
	}

	@Override
	public void create(SetUser user, List<SetUserRgroup> list) {
		setUserDao.insert(user);
		for(SetUserRgroup userRgroup : list){
			setUserRgroupDao.insert(userRgroup);
		}
	}

	@Override
	public void update(SetUser user, List<SetUserRgroup> list) {
		setUserDao.updateByPrimaryKeySelective(user);
		BigDecimal userId = list.get(0).getUserId();
		setUserRgroupDao.deleteByUserId(userId);
		for(SetUserRgroup userRgroup : list){
			setUserRgroupDao.insert(userRgroup);
		}
	}

	@Override
	public void delete(BigDecimal userId) {
		setUserDao.deleteByPrimaryKey(userId);
		setUserRgroupDao.deleteByUserId(userId);
	}

	@Override
	public String queryUserSeq() {
		return setUserDao.getSequences();
	}

	@Override
	public List<SetRoleGroup> queryAll() {
		return setRoleGroupDao.queryAll();
	}
	
	public SetUser queryById(BigDecimal userId) {
		return setUserDao.selectByPrimaryKey(userId);
	}
	
	
	@Override
	public List<SetRoleGroup> queryCandidateResource(BigDecimal userId) {
		return setRoleGroupDao.queryCandidateResource(userId);
	}
	@Override
	public List<SetRoleGroup> querySelectedResource(BigDecimal userId) {
		return setRoleGroupDao.querySelectedResource(userId);
	}
	
	@Override
	public Integer queryUserExist(String userName) {
		return setUserDao.queryUserExist(userName);
	}
	
	@Autowired
	private UserGroupDao userGroupDao;
	@Autowired
	private SetUserDao setUserDao;
	@Autowired
	private SetUserRgroupDao setUserRgroupDao;
	@Autowired
	private SetRoleGroupDao setRoleGroupDao;

}
