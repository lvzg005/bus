package com.company.bus.loaned.dbaccess.dao;

import java.math.BigDecimal;

import com.company.bus.loaned.dbaccess.entity.SetRgroupFrole;
import com.company.bus.loaned.dbaccess.entity.SetRgroupFroleKey;

/**
 * @Description 功能角色组关系数据访问接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */

public interface SetRgroupFroleDao {
	
	/**
	 * @Description 插入新记录
	 * @param setRgroupFrole
	 * @author 
	 */
	void insert(SetRgroupFrole setRgroupFrole);
	
	
	/**
	 * @Description 删除当前用户对应的所有菜单记录
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	void deleteByGroupId(BigDecimal rgroupId);
	
	/**
	 * @Description 更新记录
	 * @param setRgroupFroleKey
	 * @author 
	 */
	void updateByPrimaryKeySelective(SetRgroupFroleKey setRgroupFroleKey);
	
	

}
