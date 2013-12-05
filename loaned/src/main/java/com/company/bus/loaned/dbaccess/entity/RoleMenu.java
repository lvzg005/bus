package com.company.bus.loaned.dbaccess.entity;

import java.math.BigDecimal;

public class RoleMenu {
	
	 private BigDecimal menuId;
	 
	 private String menuName;
	 
	 private String roleId;
	 
	 private String roleName;

	public BigDecimal getMenuId() {
		return menuId;
	}

	public void setMenuId(BigDecimal menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
