package com.nag.formbean;

import java.util.Date;
import java.util.Map;

public class TravelRequestForm {

	private String source;
	private String destination;
	private String travelType;
	private String expenses;
	private Date travelDate;
	private String purpose;	
	private String attachmentPath;
	private String employeeId;
	private boolean isMultipleRequest;
	private String approveEmpOrder[];
	private Map<Integer, MultipleRequestForm> multipleRequestFormMap;
	
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
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getExpenses() {
		return expenses;
	}
	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String[] getApproveEmpOrder() {
		return approveEmpOrder;
	}
	public void setApproveEmpOrder(String[] approveEmpOrder) {
		this.approveEmpOrder = approveEmpOrder;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public boolean isMultipleRequest() {
		return isMultipleRequest;
	}
	public void setMultipleRequest(boolean isMultipleRequest) {
		this.isMultipleRequest = isMultipleRequest;
	}
	public Map<Integer, MultipleRequestForm> getMultipleRequestFormMap() {
		return multipleRequestFormMap;
	}
	public void setMultipleRequestFormMap(Map<Integer, MultipleRequestForm> multipleRequestFormMap) {
		this.multipleRequestFormMap = multipleRequestFormMap;
	}	
	
}
