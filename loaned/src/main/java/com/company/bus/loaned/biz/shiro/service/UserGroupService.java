package com.company.bus.loaned.biz.shiro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.company.bus.loaned.common.web.controller.pagination.Pageable;
import com.company.bus.loaned.dbaccess.entity.SetRoleGroup;
import com.company.bus.loaned.dbaccess.entity.SetUser;
import com.company.bus.loaned.dbaccess.entity.SetUserRgroup;



/**
 * @Description 用户组管理Service层接口
 * @author 
 * @date 2013-03-01
 * @version 1.0
 */
public interface UserGroupService {
	
	/**
	 * @Description 按条件分页查询结算菜单
	 * @param pageable 分页对象
	 * @param menu 查询条件
	 * @return
	 * @author 
	 */
	public Pageable queryPaged(Pageable pageable,  Map<String, String> params);
	
	/**
	 * @Description 新增用户以及用户组关系
	 * @param roleGroup 组对象
	 * @param list 角色组关系集合
	 * @return
	 * @author 
	 */
	public void create(SetUser user, List<SetUserRgroup> list);
	
	/**
	 * @Description 编辑用户以及用户组关系
	 * @param roleGroup 组对象
	 * @param list 角色组关系集合
	 * @return
	 * @author 
	 */
	public void update(SetUser user, List<SetUserRgroup> list);
	
	/**
	 * @Description 删除用户以及用户组关系
	 * @param userId 用户主键
	 * @return
	 * @author 
	 */
	public void delete(BigDecimal userId);
	
	/**
	 * @Description 得到用户主键
	 * @return
	 * @author 
	 */
	public String queryUserSeq();

	/**
	 * @Description 查询所有组
	 * @return
	 * @author 
	 */
	List<SetRoleGroup> queryAll();
	
	/**
	 * @Description 通过角色ID查询候选角色组
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	public List<SetRoleGroup> queryCandidateResource(BigDecimal userId);
	/**
	 * @Description 通过角色ID查询已选角色组
	 * @param rgroupId
	 * @return
	 * @author 
	 */
	public List<SetRoleGroup> querySelectedResource(BigDecimal userId);
	
	/**
	 * @Description 根据主键查询用户
	 * @param userId
	 * @return
	 * @author 
	 */
	public SetUser queryById(BigDecimal userId);
	
	/**
	 * 查询用户名是否存在
	 * @param userName
	 * @return
	 */
	Integer queryUserExist(String userName);
	

}
