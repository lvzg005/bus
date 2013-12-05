package com.company.bus.loaned.dbaccess.dao;

import java.math.BigDecimal;
import java.util.List;

import com.company.bus.loaned.dbaccess.entity.SetRoleGroup;


/**
 * @Description 角色组数据访问接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */

public interface SetRoleGroupDao {
	
	/**
	 * @Description 查询所有组
	 * @return
	 * @author 
	 */
	List<SetRoleGroup> queryAll();
	
	/**
	 * @Description 查询Sequences
	 * @param 
	 * @author 
	 */
	String getSequences();
	
	/**
	 * @Description 插入新记录
	 * @param setRoleGroup
	 * @author 
	 */
	void insert(SetRoleGroup setRoleGroup);
	
	/**
	 * @Description 根据主键删除记录
	 * @param roleId
	 * @return
	 * @author 
	 */
	void deleteByPrimaryKey(BigDecimal rgroupId);
	
	/**
	 * @Description 更新记录
	 * @param setRoleGroup
	 * @author 
	 */
	void updateByPrimaryKeySelective(SetRoleGroup setRoleGroup);
	
	
	/**
	 * @Description 通过ID查询候选记录
	 * @param userId
	 * @return
	 * @author 
	 */
	List<SetRoleGroup> queryCandidateResource(BigDecimal userId);
	
	/**
	 * @Description 通过ID查询已选记录
	 * @param userId
	 * @return
	 * @author 
	 */
	List<SetRoleGroup> querySelectedResource(BigDecimal userId);

}
