package com.company.bus.loaned.biz.shiro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.dbaccess.entity.SetFuncRole;
import com.company.bus.loaned.dbaccess.entity.SetResourceRole;
import com.company.bus.loaned.dbaccess.entity.SetRgroupFrole;
import com.company.bus.loaned.dbaccess.entity.SetRgroupRrole;
import com.company.bus.loaned.dbaccess.entity.SetRoleGroup;



/**
 * @Description 角色组管理Service层接口
 * @author 
 * @date 
 * @version 1.0
 */
public interface RoleGroupSerivce {
	
	/**
	 * @Description 按条件分页查询结算菜单
	 * @param pageable 分页对象
	 * @param menu 查询条件
	 * @return
	 * @author 
	 */
	public Pageable queryPaged(Pageable pageable,  Map<String, String> params);
	
	/**
	 * @Description 新增角色组以及角色组关系
	 * @param roleGroup 组对象
	 * @param frList 功能角色组关系集合
	 * @param rrList 资源角色组关系集合
	 * @return
	 * @author 
	 */
	public void create(SetRoleGroup roleGroup, List<SetRgroupFrole> frList, List<SetRgroupRrole> rrList);
	
	/**
	 * @Description 编辑角色组以及角色组关系
	 * @param roleGroup 组对象
	 * @param frList 功能角色组关系集合
	 * @param rrList 资源角色组关系集合
	 * @param rgroupId 角色组主键
	 * @return
	 * @author 
	 */
	public void update(SetRoleGroup roleGroup, List<SetRgroupFrole> frList, List<SetRgroupRrole> rrList, BigDecimal rgroupId);
	
	/**
	 * @Description 删除角色组以及角色组关系
	 * @param rgroupId 角色组主键
	 * @return
	 * @author 
	 */
	public void delete(BigDecimal rgroupId);
	
	/**
	 * @Description 得到角色主键
	 * @return
	 * @author 
	 */
	public String queryGroupSeq();

	/**
	 * @Description 查询所有功能角色
	 * @return
	 * @author 
	 */
	List<SetFuncRole> queryFrAll();
	
	/**
	 * @Description 查询所有资源角色
	 * @return
	 * @author 
	 */
	List<SetResourceRole> queryRrAll();
	
	/**
	 * @Description 通过角色组ID查询候选功能角色
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	public List<SetFuncRole> queryFCandidateResource(BigDecimal rgroupId);
	/**
	 * @Description 通过角色组ID查询已选功能角色
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	public List<SetFuncRole> queryFSelectedResource(BigDecimal rgroupId);
	
	/**
	 * @Description 通过角色组ID查询候选资源角色
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	public List<SetResourceRole> queryRCandidateResource(BigDecimal rgroupId);
	/**
	 * @Description 通过角色组ID查询已选资源角色
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	public List<SetResourceRole> queryRSelectedResource(BigDecimal rgroupId);

}
