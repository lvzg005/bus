package com.company.bus.loaned.biz.shiro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.dbaccess.entity.SetResourceRole;
import com.company.bus.loaned.dbaccess.entity.SetRroleResource;

/**
 * @Description 资源角色管理Service层接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */
public interface RroleResourceService {
	
	/**
	 * @Description 按条件分页查询结算资源
	 * @param pageable 分页对象
	 * @param menu 查询条件
	 * @return
	 * @author 
	 */
	public Pageable queryPaged(Pageable pageable,  Map<String, String> params);
	
	/**
	 * @Description 新增角色以及用户资源关系
	 * @param role 角色对象
	 * @param list 角色资源关系集合
	 * @return
	 * @author 
	 */
	public void create(SetResourceRole role, List<SetRroleResource> list);
	
	/**
	 * @Description 编辑角色以及用户资源关系
	 * @param role 角色对象
	 * @param list 角色资源关系集合
	 * @return
	 * @author 
	 */
	public void update(SetResourceRole role, List<SetRroleResource> list);
	
	/**
	 * @Description 删除角色以及用户资源关系
	 * @param roleId 角色对象主键
	 * @return
	 * @author 
	 */
	public void delete(BigDecimal roleId);
	
	/**
	 * @Description 得到角色主键
	 * @return
	 * @author 
	 */
	public String queryRoleSeq();

}
