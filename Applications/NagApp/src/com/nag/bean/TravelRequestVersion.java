package com.nag.bean;

import java.util.Date;

public class TravelRequestVersion {
	
	private String travelRequestVersionId;
	private String travelRequestMasterId;
	private String travelApproverId;
	private String approverOrder;
	private Date approvedDate;
	private Date createdDate;
	
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
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
		
}
