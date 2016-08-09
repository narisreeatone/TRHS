package com.nag.bean;

import java.util.Date;

public class TravelRequestMaster {

	private String travelRequestMasterId;
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
	public String getTravelRequestStatusId() {
		return travelRequestStatusId;
	}
	public void setTravelRequestStatusId(String travelRequestStatusId) {
		this.travelRequestStatusId = travelRequestStatusId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	private String requestNumber;
	private String employeeDetailsId;
	private String attachmentPath;
	private String travelRequestStatusId;
	private Date createdDate;
	
	
}
