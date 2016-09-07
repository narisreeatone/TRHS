package com.nag.bean;

import java.util.Date;

public class MultipleRequest {
	private String source;
	private String destination;
	private String travelType;
	private Date travelDate;
	private int travelOrder;
	private int multipleRequestId;
	private int travelRequestMasterId;
	
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
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public int getTravelOrder() {
		return travelOrder;
	}
	public void setTravelOrder(int travelOrder) {
		this.travelOrder = travelOrder;
	}
	public int getMultipleRequestId() {
		return multipleRequestId;
	}
	public void setMultipleRequestId(int multipleRequestId) {
		this.multipleRequestId = multipleRequestId;
	}
	public int getTravelRequestMasterId() {
		return travelRequestMasterId;
	}
	public void setTravelRequestMasterId(int travelRequestMasterId) {
		this.travelRequestMasterId = travelRequestMasterId;
	}
	
}
