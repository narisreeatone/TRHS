package com.nag.mail;

import javax.mail.*;

/**
 * @author Sravan
 * @version 1.0  -> Date: 2016-Aug-17
 */

public class MailAuthenticator extends Authenticator {
	String	bindingUser = "";
	String	bindingPassword = "";
	
	public MailAuthenticator(String bindingUser, String bindingPassword)
	{
		super();
		this.bindingUser = bindingUser;
		this.bindingPassword = bindingPassword;		
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(bindingUser,bindingPassword);
	}
}
