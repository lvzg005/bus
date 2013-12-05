package com.company.bus.loaned.dbaccess.dao;

import java.math.BigDecimal;

import com.company.bus.loaned.dbaccess.entity.SetUserRgroup;
import com.company.bus.loaned.dbaccess.entity.SetUserRgroupKey;



/**
 * @Description 用户组关系数据访问接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */

public interface SetUserRgroupDao {
	
	/**
	 * @Description 插入新记录
	 * @param setUserRgroup
	 * @author 
	 */
	void insert(SetUserRgroup setUserRgroup);
	
	
	/**
	 * @Description 删除当前用户对应的所有组记录
	 * @param userId
	 * @return
	 * @author 
	 */
	void deleteByUserId(BigDecimal userId);
	
	/**
	 * @Description 更新记录
	 * @param setRgroupRoleKey
	 * @author 
	 */
	void updateByPrimaryKeySelective(SetUserRgroupKey setUserRgroupKey);
	
	

}
