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

/**
 * Servlet implementation class SaveTravelRequestDetails
 */
@WebServlet("/SaveTravelRequestDetails")
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
		HttpSession session = request.getSession();
		String isUserLoggedIn = (String)session.getAttribute("isUserLoggedIn");
		EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
		String loggedInEmpDetailsId = empDetails.getEmployeeDetailsId();
		boolean reqSavingStatus = false;
		RequestDispatcher rd;
		if("true".equalsIgnoreCase(isUserLoggedIn)){
			String source = request.getParameter("source");
			String destination = request.getParameter("destination");
			String travelType = request.getParameter("travelType");
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
			TravelRequestForm requestForm = new TravelRequestForm();
			requestForm.setSource(source);
			requestForm.setDestination(destination);
			requestForm.setTravelType(travelType);
			requestForm.setExpenses(expenses);
			requestForm.setTravelDate(travelDateobj);
			requestForm.setPurpose(purpose);
			requestForm.setApproveEmpOrder(approveEmpOrder);
			requestForm.setEmployeeId(empDetails.getEmployeeDetailsId());
			
			DataBaseConnection dbHandler = new DataBaseConnection();
			reqSavingStatus = dbHandler.saveTravelRequestForm(requestForm);			
		}
		String displayMessage = "";
		if(reqSavingStatus){
			displayMessage = "Request has been successfully sent";
		}
		rd = request.getRequestDispatcher("employeeHome.jsp");
		request.setAttribute("displayMessage", displayMessage);			
		rd.forward(request,response);
				
	}

}
