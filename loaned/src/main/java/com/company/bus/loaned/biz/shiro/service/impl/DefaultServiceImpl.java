package com.company.bus.loaned.biz.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.bus.loaned.biz.shiro.service.DefaultService;
import com.company.bus.loaned.dbaccess.dao.DefaultDao;
import com.company.bus.loaned.dbaccess.entity.SetFuncMenu;

@Service("defaultService")
public class DefaultServiceImpl implements DefaultService {

	@Autowired
	private DefaultDao defaultDao;

	@Override
	public List<SetFuncMenu> findAllMenu() {
		return defaultDao.findAllMenu();
	}

	@Override
	public List<SetFuncMenu> findMenuByUserName(String username) {
		return defaultDao.findMenuByUserName(username);
	}
}
