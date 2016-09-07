package com.nag.bean;

import java.util.*;

public class TravelRequestMaster {

	private String travelRequestMasterId;
	private String requestNumber;
	private String employeeDetailsId;
	private String attachmentPath;
	private String travelRequestStatus;
	private Date createdDate;
	private String source;
	private String  destination;
	private String travelModeId;
	private String expenses;
	private String purpose;
	private Date travelDate; 
	private Date actionDate;
	private List<TravelRequestVersion> reqVersionList;
	private EmployeeDetails requestedEmpDetails;
	
	
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public EmployeeDetails getRequestedEmpDetails() {
		return requestedEmpDetails;
	}
	public void setRequestedEmpDetails(EmployeeDetails requestedEmpDetails) {
		this.requestedEmpDetails = requestedEmpDetails;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTravelModeId() {
		return travelModeId;
	}
	public void setTravelModeId(String travelModeId) {
		this.travelModeId = travelModeId;
	}
	public String getExpenses() {
		return expenses;
	}
	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public String getTravelRequestMasterId() {
		return travelRequestMasterId;
	}
	public void setTravelRequestMasterId(String travelRequestMasterId) {
		this.travelRequestMasterId = travelRequestMasterId;
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getEmployeeDetailsId() {
		return employeeDetailsId;
	}
	public void setEmployeeDetailsId(String employeeDetailsId) {
		this.employeeDetailsId = employeeDetailsId;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getTravelRequestStatus() {
		return travelRequestStatus;
	}
	public void setTravelRequestStatus(String travelRequestStatus) {
		this.travelRequestStatus = travelRequestStatus;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<TravelRequestVersion> getReqVersionList() {
		return reqVersionList;
	}
	public void setReqVersionList(List<TravelRequestVersion> reqVersionList) {
		this.reqVersionList = reqVersionList;
	}	
}
