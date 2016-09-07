package com.nag.formbean;
import java.util.Date;

public class MultipleRequestForm {
	private String source;
	private String destination;
	private String travelType;
	private Date travelDate;
	private int travelOrder;
	
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
}
