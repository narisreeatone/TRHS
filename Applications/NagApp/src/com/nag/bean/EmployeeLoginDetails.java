package com.nag.bean;

import java.util.Date;

public class EmployeeLoginDetails {
	private int loginId;
	private String userName;
	private String loginPassword;
	private String employeeDetailsId;
	private Date lastLoginDate;
	private String isRandomPwd;
	private String isActive;
	private String employeeRoleId;
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getEmployeeDetailsId() {
		return employeeDetailsId;
	}
	public void setEmployeeDetailsId(String employeeDetailsId) {
		this.employeeDetailsId = employeeDetailsId;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getIsRandomPwd() {
		return isRandomPwd;
	}
	public void setIsRandomPwd(String isRandomPwd) {
		this.isRandomPwd = isRandomPwd;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getEmployeeRoleId() {
		return employeeRoleId;
	}
	public void setEmployeeRoleId(String employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}
	
	
}
