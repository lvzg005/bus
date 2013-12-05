package com.company.bus.loaned.dbaccess.entity;

import java.math.BigDecimal;

public class RroleResource {
	
	private BigDecimal resourceRoleId;
	
	private BigDecimal resourceId;
	
	private String resourceRoleName;
	
	private String urlPattern;

	public BigDecimal getResourceRoleId() {
		return resourceRoleId;
	}

	public void setResourceRoleId(BigDecimal resourceRoleId) {
		this.resourceRoleId = resourceRoleId;
	}

	public BigDecimal getResourceId() {
		return resourceId;
	}

	public void setResourceId(BigDecimal resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceRoleName() {
		return resourceRoleName;
	}

	public void setResourceRoleName(String resourceRoleName) {
		this.resourceRoleName = resourceRoleName;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
}
