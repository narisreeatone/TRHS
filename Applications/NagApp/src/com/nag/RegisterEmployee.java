package com.nag;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nag.bean.EmployeeDetails;
import com.nag.bean.EmployeeLoginDetails;
import com.nag.dao.*;
import com.nag.formbean.*;
import com.nag.mail.MailHandler;
import com.nag.sql.*;
import com.nag.util.ValidateUserSession;

import org.apache.commons.lang.*;

/**
 * Servlet implementation class RegisterEmployee
 */
@WebServlet("/web/RegisterEmployee")
public class RegisterEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		RequestDispatcher rd;
		ValidateUserSession validateUserSession = new ValidateUserSession();
		if(!validateUserSession.checkUserSession(session)){
			DataBaseConnection dbHandler = new DataBaseConnection();
			RegisterEmployeeForm registerEmployeeForm = new RegisterEmployeeForm();
			EmployeeLoginDetails empLoginDetails = new EmployeeLoginDetails();
			EmployeeDetails employeeDetails = new EmployeeDetails();
			MailHandler mailHandler = new MailHandler();
			String errorMessage = "Please fill required fields in red.";
			String displayMessage = "";
			
			String employeeId = request.getParameter("employeeId");
			String employeeName = request.getParameter("employeeName");
			String dobString = request.getParameter("dob");
			Date dob = null;
			try{
	        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	        	dob = df.parse(dobString);
	        }catch(Exception e){
	        	System.out.println("Unable to convert emp DOB to date object in bulk upload: "+e.getMessage());
	        }
			String designationId = request.getParameter("designationId");
			String departmentId = request.getParameter("departmentId");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String landline = request.getParameter("landline");
			String extension = request.getParameter("extension");
			
			if( StringUtils.isNotEmpty(employeeId) && 
					StringUtils.isNotEmpty(employeeName) && 
					StringUtils.isNotEmpty(dobString) && 
					StringUtils.isNotEmpty(designationId) && 
					StringUtils.isNotEmpty(departmentId) &&
					StringUtils.isNotEmpty(email) &&
					StringUtils.isNotEmpty(email)){
				registerEmployeeForm.setEmployeeId(employeeId);
				registerEmployeeForm.setEmployeeName(employeeName);;
				registerEmployeeForm.setDob(dob);
				registerEmployeeForm.setDesignationId(designationId);
				registerEmployeeForm.setDepartmentId(departmentId);
				registerEmployeeForm.setEmail(email);
				registerEmployeeForm.setMobile(mobile);
				registerEmployeeForm.setLandline(landline);
				registerEmployeeForm.setExtension(extension);
				String status = dbHandler.registerEmployee(registerEmployeeForm);
				if("duplicateEmpId".equals(status)){
					errorMessage = "Employee id is already existed. Please use another employee id.";
					request.setAttribute("errorMsg", errorMessage);
					rd = request.getRequestDispatcher("/web/EmployeeRegistration.jsp");	
				}else {
					if ("true".equals(status)){					
				
						empLoginDetails = dbHandler.getLoginDetails(employeeId);
						employeeDetails = dbHandler.getEmployeeDetailsByEmpId(employeeId);
						boolean mailStatus = mailHandler.sendEmployeeRegistrationDetails(empLoginDetails, employeeDetails);
						if(mailStatus){
							displayMessage = "Employee registration is success and sent credentials to employee mail.";
						}else
							displayMessage = "Employee registration is success but unable to send credentials to employee mail.";
					}
					else{
						errorMessage = "Problem with employee registration. Please check log for error.";
					}
					request.setAttribute("displayMessage", displayMessage);
					rd = request.getRequestDispatcher("/web/AdminHome.jsp");
				}
			}else{
				request.setAttribute("errorMsg", errorMessage);
				rd = request.getRequestDispatcher("/web/EmployeeRegistration.jsp");			
			}			
			//rd.forward(request,response);
		}else{
			rd = request.getRequestDispatcher("/NagApp/login.jsp");	
			request.setAttribute("errorMsg", "Please log in to your account.");
		}
		
		rd.forward(request,response);
	}

}
