package com.nag.bean;

import java.util.Date;

public class TravelRequestVersion {
	
	private String travelRequestVersionId;
	private String travelRequestMasterId;
	private String travelApproverId;
	private String approverOrder;
	private Date actionDate;
	private String statusId;
	private EmployeeDetails approverEmpDetails;
	
	public EmployeeDetails getApproverEmpDetails() {
		return approverEmpDetails;
	}
	public void setApproverEmpDetails(EmployeeDetails approverEmpDetails) {
		this.approverEmpDetails = approverEmpDetails;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getTravelRequestVersionId() {
		return travelRequestVersionId;
	}
	public void setTravelRequestVersionId(String travelRequestVersionId) {
		this.travelRequestVersionId = travelRequestVersionId;
	}
	public String getTravelRequestMasterId() {
		return travelRequestMasterId;
	}
	public void setTravelRequestMasterId(String travelRequestMasterId) {
		this.travelRequestMasterId = travelRequestMasterId;
	}
	public String getTravelApproverId() {
		return travelApproverId;
	}
	public void setTravelApproverId(String travelApproverId) {
		this.travelApproverId = travelApproverId;
	}
	public String getApproverOrder() {
		return approverOrder;
	}
	public void setApproverOrder(String approverOrder) {
		this.approverOrder = approverOrder;
	}	
}
