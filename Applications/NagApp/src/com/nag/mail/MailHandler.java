package com.nag.mail;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.net.ssl.SSLSession;
import javax.mail.*;

import com.nag.bean.*;
import com.nag.formbean.*;

public class MailHandler {
	/*private String to = null;
	private String from = null;
	private String subject = null;
	private String message = null;
	private String replyto = null;
	private String bcc = null;
	private String cc = null;*/
	private String from = "admin";
	private String replyto = "amaravathicomputersservices@gmail.com";
	public static Properties props = null;
	public static Session session = null;	
	
	public boolean sendNewTravelRequestMail(EmployeeDetails empDetails, TravelRequestForm reqFrom, String toemail){	
		String host = "smtp.gmail.com";
		String port = "587";
		String username = "amaravathicomputersservices@gmail.com";
		String password = "amaravathi@sravan";		
		String auth = "true" ;		
		String starttls = "true";
		// create some properties and get the default Session
		Properties props = System.getProperties();		
		props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    //props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session1 = Session.getInstance(props, new MailAuthenticator(username, password));  		
		
		try {
			 String subject, messageBody = null;
            // create a message
            MimeMessage msg = new MimeMessage(session1);
            msg.setFrom(new InternetAddress(from));            
                         

            InternetAddress[] address_to = InternetAddress.parse(toemail);
            msg.setRecipients(Message.RecipientType.TO, address_to);
            // set BCC
                      
            subject = "Travel Request";
            messageBody = "<table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;padding-bottom:20px;'>Below travel request has been received from {0}</td></tr><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'><table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='150' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Source :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{1}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Destination :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{2}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Purpose :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{3}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Travel Date :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{4}</td></tr></tbody></table></td></tr></tbody></table>";
            messageBody = messageBody.replace("{0}", empDetails.getEmployeeName());
            messageBody = messageBody.replace("{1}", reqFrom.getSource());
            messageBody = messageBody.replace("{2}", reqFrom.getDestination());
            messageBody = messageBody.replace("{3}", reqFrom.getPurpose());
            Date travelDate = new Date();
            try{
            	DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
            	travelDate = df.parse(reqFrom.getTravelDate().toString());
            }catch(Exception e){            	
            }
            messageBody = messageBody.replace("{4}", travelDate.toString());            
            
            msg.setSubject(subject, "UTF-8");
            StringBuffer sb = new StringBuffer();
            sb.append("<HTML>\n");
            sb.append("<HEAD>\n");
            sb.append("<TITLE>\n");
            sb.append(subject + "\n");
            sb.append("</TITLE>\n");
            sb.append("</HEAD>\n");
            sb.append("<BODY>\n");
            sb.append( messageBody + "\n");                         
            sb.append("</BODY>\n");
            sb.append("</HTML>\n");          
            
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
            // set the Date: header
            msg.setSentDate(new Date());
            // send the message
            Transport.send(msg);
            System.out.println("Send Successful ! ");
            return Boolean.TRUE;
        } catch (Exception ex) {
        	System.out.println("Send Failed ! exception : " + ex);
            return Boolean.FALSE;
        }
	}
	
	public boolean sendTravelRequestStatusMail(String reqStatus, TravelRequestMaster reqMaster, EmployeeDetails reqOwnerDetails){	
		System.out.println("sendTravelRequestStatusMail");
		String username = "amaravathicomputersservices@gmail.com";
		String password = "amaravathi@sravan";		
		
		Properties props = System.getProperties();		
		props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    //props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session1 = Session.getInstance(props, new MailAuthenticator(username, password));  		
		
		try {
			 String subject, messageBody = null;			 
            // create a message
            MimeMessage msg = new MimeMessage(session1);
            msg.setFrom(new InternetAddress(from));
            
            if(replyto!=null && !replyto.equalsIgnoreCase("")){
                InternetAddress[] address_replyto = InternetAddress.parse(replyto);
                msg.setReplyTo(address_replyto);
            }                
            String toemail = reqOwnerDetails.getEmailId();
            String bcc = "";
            String cc = "";
            InternetAddress[] address_to = InternetAddress.parse(toemail);
            msg.setRecipients(Message.RecipientType.TO, address_to);
            // set BCC
            if(bcc!=null && !bcc.equalsIgnoreCase("")){
                InternetAddress[] address_bcc = InternetAddress.parse(bcc);
                msg.setRecipients(Message.RecipientType.BCC, address_bcc);
            }                
            if(cc!=null && !cc.equalsIgnoreCase("")){
                InternetAddress[] address_cc = InternetAddress.parse(cc);
                msg.setRecipients(Message.RecipientType.CC, address_cc);
            }      
            StringBuilder sb = null;
            if("allApproved".equals(reqStatus)){
            	System.out.println("sending approved mail");
            	subject = "Travel Request Approved";
                messageBody = "<table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;padding-bottom:20px;'>Below travel request has been approved.</td></tr><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'><table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='150' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Source :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{1}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Destination :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{2}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Purpose :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{3}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Travel Date :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{4}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Requested  Date :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{5}</td></tr></tbody></table></td>	</tr></tbody></table>";
                messageBody = messageBody.replace("{1}", reqMaster.getSource());
                messageBody = messageBody.replace("{2}", reqMaster.getDestination());
                messageBody = messageBody.replace("{3}", reqMaster.getPurpose());
                //messageBody = messageBody.replace("{4}", reqMaster.getTravelDate().toString());
                //messageBody = messageBody.replace("{5}", reqMaster.getCreatedDate().toString());
                Date travelDate = new Date();
                Date actionDate = new Date();
                try{
                	DateFormat df = new SimpleDateFormat("dd-mm-yyyy"); 
                	travelDate = df.parse(reqMaster.getTravelDate().toString());
                	actionDate = df.parse(reqMaster.getCreatedDate().toString());
                }catch(Exception e){}
                messageBody = messageBody.replace("{4}", travelDate.toString());
                messageBody = messageBody.replace("{5}", actionDate.toString());
                msg.setSubject(subject, "UTF-8");
                sb = new StringBuilder();
                sb.append("<HTML>\n");
                sb.append("<HEAD>\n");
                sb.append("<TITLE>\n");
                sb.append(subject + "\n");
                sb.append("</TITLE>\n");
                sb.append("</HEAD>\n");
                sb.append("<BODY>\n");
                sb.append( messageBody + "\n");                         
                sb.append("</BODY>\n");
                sb.append("</HTML>\n");    
            }else if("rejected".equals(reqStatus)){            	
            	subject = "Travel Request Rejected";
            	messageBody = "<table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;padding-bottom:20px;'>Below travel request has been rejected.</td></tr><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'><table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='150' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Source :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{1}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Destination :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{2}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Purpose :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{3}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Travel Date :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{4}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Requested  Date :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{5}</td></tr></tbody></table></td>	</tr></tbody></table>";
            	messageBody = messageBody.replace("{1}", reqMaster.getSource());
            	messageBody = messageBody.replace("{2}", reqMaster.getDestination());
            	messageBody = messageBody.replace("{3}", reqMaster.getPurpose());
            	messageBody = messageBody.replace("{4}", reqMaster.getTravelDate().toString());
            	messageBody = messageBody.replace("{5}", reqMaster.getCreatedDate().toString());
                
                msg.setSubject(subject, "UTF-8");
                sb = new StringBuilder();
                sb.append("<HTML>\n");
                sb.append("<HEAD>\n");
                sb.append("<TITLE>\n");
                sb.append(subject + "\n");
                sb.append("</TITLE>\n");
                sb.append("</HEAD>\n");
                sb.append("<BODY>\n");
                sb.append( messageBody + "\n");                         
                sb.append("</BODY>\n");
                sb.append("</HTML>\n");    
            }
                  
            System.out.println("before sending mail command");
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
            // set the Date: header
            msg.setSentDate(new Date());
            // send the message
            Transport.send(msg);
            System.out.println("Send Successful ! ");
            return Boolean.TRUE;
        } catch (Exception ex) {
        	System.out.println("Send Failed ! exception : " + ex);
            return Boolean.FALSE;
        }
	}
	
	public boolean sendEmployeeRegistrationDetails(EmployeeLoginDetails empLoginDetails, EmployeeDetails employeeDetails){
		String username = "amaravathicomputersservices@gmail.com";
		String password = "amaravathi@sravan";		
		
		Properties props = System.getProperties();		
		props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    //props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session1 = Session.getInstance(props, new MailAuthenticator(username, password));  		
		
		try {
			 String subject, messageBody = null;			 
            // create a message
            MimeMessage msg = new MimeMessage(session1);
            msg.setFrom(new InternetAddress(from));
            
            if(replyto!=null && !replyto.equalsIgnoreCase("")){
                InternetAddress[] address_replyto = InternetAddress.parse(replyto);
                msg.setReplyTo(address_replyto);
            }                
            String toemail = employeeDetails.getEmailId();
            String bcc = "";
            String cc = "";
            InternetAddress[] address_to = InternetAddress.parse(toemail);
            msg.setRecipients(Message.RecipientType.TO, address_to);
            // set BCC
            if(bcc!=null && !bcc.equalsIgnoreCase("")){
                InternetAddress[] address_bcc = InternetAddress.parse(bcc);
                msg.setRecipients(Message.RecipientType.BCC, address_bcc);
            }                
            if(cc!=null && !cc.equalsIgnoreCase("")){
                InternetAddress[] address_cc = InternetAddress.parse(cc);
                msg.setRecipients(Message.RecipientType.CC, address_cc);
            }      
            StringBuilder sb = null;
            
            	System.out.println("sending approved mail");
            	subject = "Employee registration details";
                messageBody = "<table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;padding-bottom:20px;'>Your details are successfully registered. Below are the login details.</td></tr><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'><table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='150' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>username :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{1}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>password :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{2}</td></tr></tbody></table></td></tr></tbody></table>";
                messageBody = messageBody.replace("{1}", empLoginDetails.getUserName());
                messageBody = messageBody.replace("{2}", empLoginDetails.getLoginPassword());
                
                msg.setSubject(subject, "UTF-8");
                sb = new StringBuilder();
                sb.append("<HTML>\n");
                sb.append("<HEAD>\n");
                sb.append("<TITLE>\n");
                sb.append(subject + "\n");
                sb.append("</TITLE>\n");
                sb.append("</HEAD>\n");
                sb.append("<BODY>\n");
                sb.append( messageBody + "\n");                         
                sb.append("</BODY>\n");
                sb.append("</HTML>\n");    
                              
            System.out.println("before sending mail command");
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
            // set the Date: header
            msg.setSentDate(new Date());
            // send the message
            Transport.send(msg);
            System.out.println("Send Successful ! ");
            return Boolean.TRUE;
        } catch (Exception ex) {
        	System.out.println("Send Failed ! exception : " + ex);
            return Boolean.FALSE;
        }		
	}
	
	public boolean sendTRComments(String recieverMailId, String senderName, String comments, String commentsBy, TravelRequestMaster requestDetails){
		String username = "amaravathicomputersservices@gmail.com";
		String password = "amaravathi@sravan";		
		
		Properties props = System.getProperties();		
		props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    //props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session1 = Session.getInstance(props, new MailAuthenticator(username, password));  		
		
		try {
			 String subject = null, messageBody = null;			 
            // create a message
            MimeMessage msg = new MimeMessage(session1);
            msg.setFrom(new InternetAddress(from));
            
            if(replyto!=null && !replyto.equalsIgnoreCase("")){
                InternetAddress[] address_replyto = InternetAddress.parse(replyto);
                msg.setReplyTo(address_replyto);
            }                
            String toemail = recieverMailId;
            String bcc = "";
            String cc = "";
            InternetAddress[] address_to = InternetAddress.parse(toemail);
            msg.setRecipients(Message.RecipientType.TO, address_to);
            // set BCC
            if(bcc!=null && !bcc.equalsIgnoreCase("")){
                InternetAddress[] address_bcc = InternetAddress.parse(bcc);
                msg.setRecipients(Message.RecipientType.BCC, address_bcc);
            }                
            if(cc!=null && !cc.equalsIgnoreCase("")){
                InternetAddress[] address_cc = InternetAddress.parse(cc);
                msg.setRecipients(Message.RecipientType.CC, address_cc);
            }      
            StringBuilder sb = null;
            String mainLeadin = null;
        	System.out.println("sending comments mail");
        	if("requestApprover".equals(commentsBy)){
        		subject = "Approver needs clarification for travel request";
        		mainLeadin="Below comment on travel request has been received from approver "+senderName;
        	}
        	if("requestOwner".equals(commentsBy)){
        		subject = "Travel request clarification";
        		mainLeadin="Clarification for travel request has been received from "+senderName;
        	}
        	if(mainLeadin != null){
	            messageBody = "<table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;padding-bottom:20px;'>{0}</td></tr><tr><td width='600' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'><table width='600' height='18' cellspacing='0' cellpadding='0' border='0' style=''><tbody><tr><td width='150' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Source :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{1}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Destination :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{2}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Purpose :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{3}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Travel Date :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{4}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Requested  Date :</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{5}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Commented By:</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{6}</td></tr><tr><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;text-align:right;padding-right:10px'>Comment:</td><td width='' height='18' valign='bottom' bgcolor='#ffffff' style='line-height:17px;font-family:Arial,Helvetica,Sans-serif;'>{7}</td></tr></tbody></table></td></tr></tbody></table>";
	            messageBody = messageBody.replace("{0}", mainLeadin);
	            messageBody = messageBody.replace("{1}", requestDetails.getSource());
	            messageBody = messageBody.replace("{2}", requestDetails.getDestination());
	            messageBody = messageBody.replace("{3}", requestDetails.getPurpose());
	            messageBody = messageBody.replace("{4}", requestDetails.getTravelDate().toString());
	            messageBody = messageBody.replace("{5}", requestDetails.getCreatedDate().toString());
	            messageBody = messageBody.replace("{6}", senderName);
	            messageBody = messageBody.replace("{7}", comments);
	            
	            msg.setSubject(subject, "UTF-8");
	            sb = new StringBuilder();
	            sb.append("<HTML>\n");
	            sb.append("<HEAD>\n");
	            sb.append("<TITLE>\n");
	            sb.append(subject + "\n");
	            sb.append("</TITLE>\n");
	            sb.append("</HEAD>\n");
	            sb.append("<BODY>\n");
	            sb.append( messageBody + "\n");                         
	            sb.append("</BODY>\n");
	            sb.append("</HTML>\n");    
	                              
	            System.out.println("before sending mail command");
	            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
	            // set the Date: header
	            msg.setSentDate(new Date());
	            // send the message
	            Transport.send(msg);
	            System.out.println("Send Successful ! ");
	            return Boolean.TRUE;
        	}else
        		return Boolean.FALSE;
        } catch (Exception ex) {
        	System.out.println("Send Failed ! exception : " + ex);
            return Boolean.FALSE;
        }		
	}
}
