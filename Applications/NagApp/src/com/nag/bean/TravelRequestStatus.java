package com.nag.bean;
import java.util.Date;
public class TravelRequestStatus {

	private String travelRequestStatusId;
	private String travelRequestName;
	private Date createdDate;
	public String getTravelRequestStatusId() {
		return travelRequestStatusId;
	}
	public void setTravelRequestStatusId(String travelRequestStatusId) {
		this.travelRequestStatusId = travelRequestStatusId;
	}
	public String getTravelRequestName() {
		return travelRequestName;
	}
	public void setTravelRequestName(String travelRequestName) {
		this.travelRequestName = travelRequestName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
