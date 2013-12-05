package com.company.bus.loaned.dbaccess.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.company.bus.loaned.dbaccess.entity.SetFuncMenu;


/**
 * @Description 菜单数据访问接口
 * @author 
 * @date 2013-02-28
 * @version 1.0
 */

public interface SetFuncMenuDao {
	
	/**
	 * @Description 插入新记录
	 * @param menu
	 * @author 
	 */
	void insert(SetFuncMenu menu);
	
	/**
	 * @Description 更新记录
	 * @param menu
	 * @author 
	 */
	void updateByPrimaryKeySelective(SetFuncMenu menu);
	
	/**
	 * @Description 查询Sequences
	 * @param 
	 * @author 
	 */
	String getSequences();
	
	

	/**
	 * @Description 查询分页记录
	 * @param params
	 * @return 
	 * @author 
	 */
	List<SetFuncMenu> queryPaged(Map<String, Object> params);
	
	
	/**
	 * @Description 查询记录总数
	 * @param params
	 * @return
	 * @author 
	 */
	int queryTotalCount(Map<String, Object> params);
	
	/**
	 * @Description 查询所有记录
	 * @return
	 * @author 
	 */
	List<SetFuncMenu> queryAll();
	
	/**
	 * @Description 根据主键查询记录
	 * @param menuId
	 * @return
	 * @author 
	 */
	SetFuncMenu selectByPrimaryKey(BigDecimal menuId);
	
	/**
	 * @Description 根据主键删除记录
	 * @param menuId
	 * @return
	 * @author 
	 */
	void deleteByPrimaryKey(BigDecimal menuId);
	
	/**
	 * @Description 根据菜单代码删除记录
	 * @param params
	 * @return
	 * @author 
	 */
	void deleteByMenuCode(Map<String, String> params);
	
	
	/**
	 * @Description 通过ID查询候选记录
	 * @param funcRoleId
	 * @return
	 * @author 
	 */
	List<SetFuncMenu> queryCandidateResource(BigDecimal funcRoleId);
	
	/**
	 * @Description 通过ID查询已选记录
	 * @param funcRoleId
	 * @return
	 * @author 
	 */
	List<SetFuncMenu> querySelectedResource(BigDecimal funcRoleId);

}
