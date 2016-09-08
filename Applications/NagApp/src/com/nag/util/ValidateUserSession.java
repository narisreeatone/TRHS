package com.nag.util;

import javax.servlet.http.*; 

public class ValidateUserSession {
	public boolean checkUserSession(HttpSession session){
		//System.out.println("****start check session method");
		boolean isSessionNew = true;		
		if(session != null){			
			isSessionNew = false;
			if(session.getAttribute("loginUserDetails") == null)
				isSessionNew = true;	
		}
		//System.out.println("is session new:::"+isSessionNew+"\nisUserLoggedIn"+session.getAttribute("loginUserDetails"));
		return isSessionNew;
	}
}
