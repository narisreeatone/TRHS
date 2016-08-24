package com.nag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nag.bean.EmployeeDetails;
import com.nag.bean.EmployeeLoginDetails;
import com.nag.dao.*;
import com.nag.formbean.*;
import com.nag.mail.MailHandler;
import com.nag.sql.*;

import org.apache.commons.lang.*;

/**
 * Servlet implementation class RegisterEmployee
 */
@WebServlet("/RegisterEmployee")
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
		RegisterEmployeeForm registerEmployeeForm = new RegisterEmployeeForm();
		EmployeeLoginDetails empLoginDetails = new EmployeeLoginDetails();
		EmployeeDetails employeeDetails = new EmployeeDetails();
		RequestDispatcher rd;
		DataBaseConnection dbHandler = new DataBaseConnection();
		MailHandler mailHandler = new MailHandler();
		String errorMessage = "Please fill required fields.";
		String displayMessage = "";
		
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String dob = request.getParameter("dob");
		String designationId = request.getParameter("designationId");
		String departmentId = request.getParameter("departmentId");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String landline = request.getParameter("landline");
		String extension = request.getParameter("extension");
		
		if( StringUtils.isNotEmpty(employeeId) && 
				StringUtils.isNotEmpty(employeeName) && 
				StringUtils.isNotEmpty(dob) && 
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
			boolean status = dbHandler.registerEmployee(registerEmployeeForm);
			if (status){
				displayMessage = "Employee registration is success. Mail sending failed.";
				empLoginDetails = dbHandler.getLoginDetails(employeeId);
				employeeDetails = dbHandler.getEmployeeDetailsByEmpId(employeeId);
				boolean mailStatus = mailHandler.sendEmployeeRegistrationDetails(empLoginDetails, employeeDetails);
				if(mailStatus){
					displayMessage = "Employee registration is success and sent credentials to employee mail.";
				}
			}
			else{
				displayMessage = "Problem with employee registration";
			}
			request.setAttribute("displayMessage", displayMessage);
			rd = request.getRequestDispatcher("AdminHome.jsp");
		}else{
			request.setAttribute("errorMessage", errorMessage);
			rd = request.getRequestDispatcher("EmployeeRegistration.jsp");			
		}
		rd.forward(request,response);
	}

}
