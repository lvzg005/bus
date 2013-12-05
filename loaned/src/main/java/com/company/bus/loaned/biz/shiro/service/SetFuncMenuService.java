package com.company.bus.loaned.biz.shiro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.dbaccess.entity.SetFuncMenu;

/**
 * @Description 菜单Service层接口
 * @author 
 * @date 2013-02-28
 * @version 1.0
 */
public interface SetFuncMenuService {
	
	/**
	 * @Description 按条件分页查询菜单
	 * @param pageable 分页对象
	 * @param menu 查询条件
	 * @return
	 * @author 
	 */
	public Pageable queryPaged(Pageable pageable,  Map<String, String> params);
	
	
	/**
	 * @Description 创建菜单
	 * @param menu 
	 * @author 
	 */
	void create(SetFuncMenu menu);
	
	/**
	 * @Description 更新菜单
	 * @param menu 
	 * @author 
	 */
	void update(SetFuncMenu menu);
	
	/**
	 * @Description 查询Sequences
	 * @author 
	 */
	String querySeq();
	

	/**
	 * @Description 查询所有菜单记录
	 * @author 
	 */
	List<SetFuncMenu> queryAll();
	
	/**
	 * @Description 根据主键查询菜单记录
	 * @param menuId 
	 * @author 
	 */
	SetFuncMenu queryById(BigDecimal menuId);
	
	/**
	 * @Description 根据主键删除菜单记录
	 * @param params 
	 * @author 
	 */
	void deleteMenu(Map<String, String> params);
	
	
	/**
	 * @Description 通过角色ID查询候选菜单
	 * @param roleId
	 * @return
	 * @author 
	 */
	public List<SetFuncMenu> queryCandidateResource(BigDecimal roleId);
	/**
	 * @Description 通过角色ID查询已选菜单
	 * @param roleId
	 * @return
	 * @author 
	 */
	public List<SetFuncMenu> querySelectedResource(BigDecimal roleId);

}
