package com.nag.sql;

public class RequestStatus {
	public String APPROVED = "1";
	public String REJECTED = "2";
	public String PENDING = "3";
	public String[] STATUSSTRING = {"","Approved","Rejected","Pending"};
	
	public String getAPPROVED() {
		return APPROVED;
	}
	public void setAPPROVED(String aPPROVED) {
		APPROVED = aPPROVED;
	}
	public String getREJECTED() {
		return REJECTED;
	}
	public void setREJECTED(String rEJECTED) {
		REJECTED = rEJECTED;
	}
	public String getPENDING() {
		return PENDING;
	}
	public void setPENDING(String pENDING) {
		PENDING = pENDING;
	}
	public String[] getSTATUSSTRING() {
		return STATUSSTRING;
	}
	public void setSTATUSSTRING(String[] sTATUSSTRING) {
		STATUSSTRING = sTATUSSTRING;
	}	
}
