package com.nag.mail;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
	
	public static void prepareMailProperties(){
		/*String host = "smtp.gmail.com";
		String port = "587";
		String username = "amaravathicomputersservices@gmail.com";
		String password = "amaravathi@sravan";		
		String auth = "true" ;		
		
		// create some properties and get the default Session
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);		
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", "true");	
		session = Session.getInstance(props, new MailAuthenticator(username, password));*/
	}
	public boolean sendTravelRequestMail(String toemail, String fromemail, String replytoemail, String bcc, String cc){	
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
		
		if (replytoemail != null)
			replyto = replytoemail;
		if (fromemail != null)
			from = fromemail;
		try {
			 String subject, messageBody = null;
            // create a message
            MimeMessage msg = new MimeMessage(session1);
            msg.setFrom(new InternetAddress(from));
            
            if(replyto!=null && !replyto.equalsIgnoreCase("")){
                InternetAddress[] address_replyto = InternetAddress.parse(replyto);
                msg.setReplyTo(address_replyto);
            }                

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
            subject = "Travel Request";
            messageBody = "New travel request";
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
	
	public boolean TravelRequestMaster(String reqStatus, TravelRequestMaster reqMaster, EmployeeDetails reqOwnerDetails){	
		
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
            StringBuffer sb = null;
            if("allApproved".equals(reqStatus)){
            	subject = "Travel Request Approved";
                messageBody = "New travel request";
                msg.setSubject(subject, "UTF-8");
                sb = new StringBuffer();
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
                messageBody = "New travel request";
                msg.setSubject(subject, "UTF-8");
                sb = new StringBuffer();
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
}
