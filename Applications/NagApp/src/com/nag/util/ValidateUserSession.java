package com.nag.util;

import javax.servlet.http.*; 

public class ValidateUserSession {
	public boolean checkUserSession(HttpSession session){
		boolean isSessionNew = true;		
		if(session != null){			
			isSessionNew = false;
			if(session.getAttribute("isUserLoggedIn") == null)
				isSessionNew = true;	
		}
		return isSessionNew;
	}
}
