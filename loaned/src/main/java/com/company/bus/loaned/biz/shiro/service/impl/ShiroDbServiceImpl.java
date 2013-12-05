package com.company.bus.loaned.biz.shiro.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.ShiroDbService;
import com.company.bus.loaned.dbaccess.dao.ShiroDbDao;
import com.company.bus.loaned.dbaccess.entity.SFilter;
import com.company.bus.loaned.dbaccess.entity.SetResource;
import com.company.bus.loaned.dbaccess.entity.SetUser;

/**
 * @author 
 *
 */
@Service("shiroDbService")
public class ShiroDbServiceImpl implements ShiroDbService {

	private transient Logger log = LoggerFactory.getLogger(ShiroDbServiceImpl.class);
	
	@Autowired
	private ShiroDbDao shiroDbDao;

	@Override
	public SetUser findByUserName(String userName) {
		return shiroDbDao.findByUserName(userName);
	}

	@Override
	public List<SetResource> findResourceRolesByUserName(String userName) {
		return shiroDbDao.findResourceRolesByUserName(userName);
	}

	@Override
	public List<SFilter> findAllFilters() {
		return shiroDbDao.findAllFilters();
	}	
}
