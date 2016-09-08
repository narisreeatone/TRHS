package com.nag.bean;

import java.util.Date;
public class TravelRequestComment {
	private int requestCommentId;
	private int requestMasterId;
	private String requestVersionId;
	private String senderId;
	private String recieverId;
	private String senderName;
	private String comment;
	private Date createdDate;
	
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public int getRequestCommentId() {
		return requestCommentId;
	}
	public void setRequestCommentId(int requestCommentId) {
		this.requestCommentId = requestCommentId;
	}
	public int getRequestMasterId() {
		return requestMasterId;
	}
	public void setRequestMasterId(int requestMasterId) {
		this.requestMasterId = requestMasterId;
	}
	public String getRequestVersionId() {
		return requestVersionId;
	}
	public void setRequestVersionId(String requestVersionId) {
		this.requestVersionId = requestVersionId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
}
