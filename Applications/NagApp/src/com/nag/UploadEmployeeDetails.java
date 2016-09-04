package com.nag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import com.nag.dao.*;
import com.nag.util.ValidateUserSession;

/**
 * Servlet implementation class UploadEmployeeDetails
 */
@WebServlet("/web/UploadEmployeeDetails")
public class UploadEmployeeDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private boolean isMultipart;
	 private String filePath;
	 private int maxFileSize = 50 * 1024;
	 private int maxMemSize = 4 * 1024;
	 private File file ;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadEmployeeDetails() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload"); 
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		HttpSession session = request.getSession(false);
		ValidateUserSession validateUserSession = new ValidateUserSession();
		if(!validateUserSession.checkUserSession(session)){
			isMultipart = ServletFileUpload.isMultipartContent(request);
		      response.setContentType("text/html");
		      java.io.PrintWriter out = response.getWriter( );
		      if( !isMultipart ){
		    	  rd = request.getRequestDispatcher("/web/uploadEmployeeData.jsp");
		    	  request.setAttribute("errorMessage", "Please select xl file.");
		    	  rd.forward(request,response);
		         return;
		      }
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File("c:\\temp"));
	
		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
	
		      try{ 
		      // Parse the request to get file items.
		      List fileItems = upload.parseRequest(request);
			
		      // Process the uploaded file items
		      Iterator i = fileItems.iterator();     
		      while ( i.hasNext () ) 
		      {
		         FileItem fi = (FileItem)i.next();
		         if ( !fi.isFormField () )	
		         {
		            // Get the uploaded file parameters
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            String contentType = fi.getContentType();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            // Write the file
		            if( fileName.lastIndexOf("\\") >= 0 ){
		               //file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
		            	file = new File( "c:\\temp\\" + fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		               //file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		               file = new File( "c:\\temp\\" + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }	            
		            fi.write( file ) ;	           
		         }
		      }
		      UploadBulkDataToDB uploadEmpDetails = new UploadBulkDataToDB();
		      String status = uploadEmpDetails.uploadEmpDetailsToDB(file.getAbsolutePath());
		      if("true".equals(status)){
		    	  rd = request.getRequestDispatcher("/web/AdminHome.jsp");
		    	  request.setAttribute("displayMessage", "Successfully uploaded employee data to database.");
		    	  //rd.forward(request,response);
		      }else{
		    	  rd = request.getRequestDispatcher("/web/uploadEmployeeData.jsp");
		    	  request.setAttribute("errorMessage", status);
		    	  //rd.forward(request,response);
		      }
		      
		   }catch(Exception ex) {
		       System.out.println(ex);
		   }
		}else{
			rd = request.getRequestDispatcher("/NagApp/login.jsp");	
			request.setAttribute("displayMessage", "Please log in to your account.");
		}
		
		rd.forward(request,response);
	}

}
