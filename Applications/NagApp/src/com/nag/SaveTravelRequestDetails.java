package com.nag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.nag.bean.EmployeeDetails;
import com.nag.dao.*;
import com.nag.formbean.*;
import com.nag.mail.*;
import com.nag.util.ValidateUserSession;

/**
 * Servlet implementation class SaveTravelRequestDetails
 */
@WebServlet("/web/SaveTravelRequestDetails")
public class SaveTravelRequestDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveTravelRequestDetails() {
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
		RequestDispatcher rd;
		HttpSession session = request.getSession(false);
		ValidateUserSession validateUserSession = new ValidateUserSession();
		if(!validateUserSession.checkUserSession(session)){
			DataBaseConnection dbHandler = new DataBaseConnection();
			boolean isUserLoggedIn = (boolean)session.getAttribute("isUserLoggedIn");
			EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
			System.out.println("in saving req fservlet:::login emp id :::"+empDetails.getEmployeeDetailsId());
			System.out.println("in saving req fservlet:::login emp id :::"+empDetails.getEmployeeName());
			//String loggedInEmpDetailsId = empDetails.getEmployeeDetailsId();
			boolean reqSavingStatus = false;
			
			
			MailHandler mailHandler = null;
			TravelRequestForm requestForm = null;
			if(isUserLoggedIn){
				String source = request.getParameter("source");
				String destination = request.getParameter("destination");
				String travelType = request.getParameter("travelMode");
				String expenses = request.getParameter("expenses");
				String travelDate = request.getParameter("travelDate");
				String purpose = request.getParameter("purpose");		
				String approveEmpOrder[] = request.getParameterValues("approveOrder");
				
				Date travelDateobj = null;		   
			    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			    try{
			    	travelDateobj = df.parse(travelDate);		        
			    }
			    catch ( Exception ex ){
			        System.out.println(ex);
			    }			
				requestForm = new TravelRequestForm();
				requestForm.setSource(source);
				requestForm.setDestination(destination);
				requestForm.setTravelType(travelType);
				requestForm.setExpenses(expenses);
				requestForm.setTravelDate(travelDateobj);
				requestForm.setPurpose(purpose);
				requestForm.setApproveEmpOrder(approveEmpOrder);
				requestForm.setEmployeeId(empDetails.getEmployeeDetailsId());
				
				dbHandler = new DataBaseConnection();
				reqSavingStatus = dbHandler.saveTravelRequestForm(requestForm);			
			}
			String displayMessage = "";
			if(reqSavingStatus){
				displayMessage = "Request has been successfully sent";
				String aproveEmpOrder[] = requestForm.getApproveEmpOrder();
				mailHandler = new MailHandler();
				for(int i=0; i < aproveEmpOrder.length; i++){
					String[] empIdAndOrder = aproveEmpOrder[i].split("-");
					EmployeeDetails approverEmpDetails = dbHandler.getEmployeeDetailsById(empIdAndOrder[0]);				
					boolean mailstatus = mailHandler.sendNewTravelRequestMail(empDetails, requestForm, approverEmpDetails.getEmailId());
					if(mailstatus)
						System.out.println("travel request mail sent successfully for "+approverEmpDetails.getEmployeeName());
					else
						System.out.println("travel request mail sent failed for "+approverEmpDetails.getEmployeeName());
				}
			}
			rd = request.getRequestDispatcher("/web/employeeHome.jsp");
			request.setAttribute("displayMessage", displayMessage);				
			//rd.forward(request,response);
		}else{
			rd = request.getRequestDispatcher("/NagApp/login.jsp");	
			request.setAttribute("displayMessage", "Please log in to your account.");
		}
		
		rd.forward(request,response);
				
	}

}
