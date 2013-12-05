package com.company.bus.loaned.dbaccess.entity;

import java.math.BigDecimal;

public class UserGroup {
	
	private BigDecimal userId;
	
	private BigDecimal rgroupId;
	
	private String userName;
	
	private String rgroupName;
	
	private String status;
	
	private String password;

	public BigDecimal getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public BigDecimal getRgroupId() {
		return rgroupId;
	}

	public void setRgroupId(BigDecimal rgroupId) {
		this.rgroupId = rgroupId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRgroupName() {
		return rgroupName;
	}

	public void setRgroupName(String rgroupName) {
		this.rgroupName = rgroupName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
