package com.nag.bean;

import java.util.Date;

public class TravelRequestComments {
	
	private String travelRequestCommentId;
	private String travelRequestVersiontId;
	private String senderId;
	private String receiverId;
	private String comments;
	private Date createdDate;
	public String getTravelRequestCommentId() {
		return travelRequestCommentId;
	}
	public void setTravelRequestCommentId(String travelRequestCommentId) {
		this.travelRequestCommentId = travelRequestCommentId;
	}
	public String getTravelRequestVersiontId() {
		return travelRequestVersiontId;
	}
	public void setTravelRequestVersiontId(String travelRequestVersiontId) {
		this.travelRequestVersiontId = travelRequestVersiontId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
