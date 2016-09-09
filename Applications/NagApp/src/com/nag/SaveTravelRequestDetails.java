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
import java.util.HashMap;
import java.util.Map;
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
			boolean reqSavingStatus = false;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			MailHandler mailHandler = null;
			TravelRequestForm requestForm = null;
			if(isUserLoggedIn){
				String travelRequestCount = request.getParameter("travelRequestCount");
				int numberOfTravelReq = Integer.parseInt(travelRequestCount);	
				MultipleRequestForm multiReqForm = new MultipleRequestForm();
				Map<Integer, MultipleRequestForm> multiReqFormMap = new HashMap<Integer, MultipleRequestForm>();
				String sourceStart = null;
				String destinationLast = null;
				String travelType = null;
				String travelDate = null;
				Date travelDateobj = null;
				Date firstTravelDateObj = null;	
				String firstTravelType = null;
					for(int i = 1; i <= numberOfTravelReq; i++){
						
						String source = request.getParameter("source"+i);
						String destination = request.getParameter("destination"+i);
						travelType = request.getParameter("travelMode"+i);
						travelDate = request.getParameter("travelDate"+i);						
						try{
					    	travelDateobj = df.parse(travelDate);		        
					    }
					    catch ( Exception ex ){
					        System.out.println(ex);
					    }
						if( numberOfTravelReq > 1 ){
							multiReqForm = new MultipleRequestForm();
							multiReqForm.setSource(source);
							multiReqForm.setDestination(destination);
							multiReqForm.setTravelType(travelType);
							multiReqForm.setTravelDate(travelDateobj); 
							multiReqForm.setTravelOrder(i); 
							multiReqFormMap.put(i, multiReqForm);
						}
						if(i == 1){
							sourceStart = source;
							firstTravelDateObj = travelDateobj;
							firstTravelType = travelType;
						}
						if(i == numberOfTravelReq)
							destinationLast = destination;
						
					}
					String expenses = request.getParameter("expenses");					
					String purpose = request.getParameter("purpose");
					String approveEmpOrder[] = request.getParameterValues("approveOrder");	
					
					requestForm = new TravelRequestForm();
					requestForm.setSource(sourceStart);
					requestForm.setDestination(destinationLast);
					if( numberOfTravelReq > 1){
						requestForm.setTravelType(firstTravelType);
						requestForm.setTravelDate(firstTravelDateObj);
						requestForm.setMultipleRequest(true);
						requestForm.setMultipleRequestFormMap(multiReqFormMap);
					}else{
						requestForm.setTravelType(travelType);						
						requestForm.setTravelDate(travelDateobj);
						requestForm.setTravelType(travelType); 
						requestForm.setMultipleRequest(false);
					}
					requestForm.setExpenses(expenses);
					requestForm.setPurpose(purpose);
					requestForm.setApproveEmpOrder(approveEmpOrder);
					requestForm.setEmployeeId(empDetails.getEmployeeDetailsId());
					  
					
					dbHandler = new DataBaseConnection();
					reqSavingStatus = dbHandler.saveTravelRequestForm(requestForm);
			}
			String displayMessage = "";
			if(reqSavingStatus){
				displayMessage = "Travel request has been successfully sent to approver(s).";
				String aproveEmpOrder[] = requestForm.getApproveEmpOrder();
				mailHandler = new MailHandler();
				for(int i=0; i < aproveEmpOrder.length; i++){
					String[] empIdAndOrder = aproveEmpOrder[i].split("-");
					EmployeeDetails approverEmpDetails = dbHandler.getEmployeeDetailsById(empIdAndOrder[0]);
					String employeeId=approverEmpDetails.getEmailId();
					boolean mailstatus = false;
					try{
						mailstatus = mailHandler.sendNewTravelRequestMail(empDetails, requestForm, approverEmpDetails.getEmailId());
					}catch(Exception e){
						displayMessage = "Request has been successfully saved but unable to sent mail to approver(s).";
					}
					if(mailstatus)
						System.out.println("New travel request mail sent successfully for "+employeeId);
					else
						System.out.println("New travel request mail failed for "+employeeId);
				}
			}
			rd = request.getRequestDispatcher("/web/employeeHome.jsp");
			request.setAttribute("displayMessage", displayMessage);				
			rd.forward(request,response);
		}else{
			request.setAttribute("errorMsg", "Session is invalid. Please log in to your account.");
			response.sendRedirect("/NagApp/login.jsp");
		}
	}

}
