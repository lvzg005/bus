package com.company.bus.loaned.dbaccess.dao;

import java.math.BigDecimal;
import java.util.List;

import com.company.bus.loaned.dbaccess.entity.SetFuncRole;


/**
 * @Description 功能角色数据访问接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */

public interface SetFuncRoleDao {
	
	/**
	 * @Description 查询Sequences
	 * @param 
	 * @author 
	 */
	String getSequences();
	
	/**
	 * @Description 插入新记录
	 * @param role
	 * @author 
	 */
	void insert(SetFuncRole role);
	
	/**
	 * @Description 根据主键删除记录
	 * @param roleId
	 * @return
	 * @author 
	 */
	void deleteByPrimaryKey(BigDecimal roleId);
	
	/**
	 * @Description 更新记录
	 * @param role
	 * @author 
	 */
	void updateByPrimaryKeySelective(SetFuncRole role);
	
	/**
	 * @Description 查询所有记录
	 * @return
	 * @author 
	 */
	List<SetFuncRole> queryAll();
	
	
	/**
	 * @Description 通过ID查询候选记录
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	List<SetFuncRole> queryCandidateResource(BigDecimal rgroupId);
	
	/**
	 * @Description 通过ID查询已选记录
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	List<SetFuncRole> querySelectedResource(BigDecimal rgroupId);

}
