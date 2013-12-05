package com.company.bus.loaned.dbaccess.dao;

import java.math.BigDecimal;

import com.company.bus.loaned.dbaccess.entity.SetRroleResource;

/**
 * @Description 角色资源数据访问接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */

public interface SetRroleResourceDao {
	
	/**
	 * @Description 插入新记录
	 * @param setRroleResource
	 * @author 
	 */
	void insert(SetRroleResource setRroleResource);
	
	
	/**
	 * @Description 删除当前用户对应的所有菜单记录
	 * @param roleId
	 * @return
	 * @author 
	 */
	void deleteByRoleId(BigDecimal roleId);
	
	/**
	 * @Description 更新记录
	 * @param setRroleResource
	 * @author 
	 */
	void updateByPrimaryKeySelective(SetRroleResource setRroleResource);

}
