package com.nag;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.nag.bean.EmployeeDetails;
import com.nag.bean.TravelRequestMaster;
import com.nag.dao.DataBaseConnection;

import com.nag.mail.*;
import com.nag.util.ValidateUserSession;
/**
 * Servlet implementation class UpdateTravelRequest
 */
@WebServlet("/web/UpdateTravelRequest")
public class UpdateTravelRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTravelRequest() {
        super();
        // TODO Auto-generated constructor stub
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
				
		HttpSession session = request.getSession(false);
		ValidateUserSession validateUserSession = new ValidateUserSession();
		RequestDispatcher rd;
		
		if(!validateUserSession.checkUserSession(session)){
			DataBaseConnection dbHandler = new DataBaseConnection();
			MailHandler mailHandler = new MailHandler();
			EmployeeDetails loggedEmpDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
			String loggedEmpDetailsId = loggedEmpDetails.getEmployeeDetailsId();		
			String travelRequestVersionId = request.getParameter("travelRequestVersionId");
			String travelRequestMasterId = request.getParameter("reqMasterId");
			String action = request.getParameter("action");		
			String updateStatus = dbHandler.updateTravelRequest(action, travelRequestVersionId, travelRequestMasterId, loggedEmpDetailsId);
			String message = "Error in updating travel request.";
			boolean mailstatus = false;
			TravelRequestMaster reqMaster = dbHandler.getTravelRequestDetails(travelRequestMasterId);
			EmployeeDetails reqOwnerDetails = dbHandler.getEmployeeDetailsById(reqMaster.getEmployeeDetailsId());
			if("allApproved".equals(updateStatus)){
				message = "Travel request has been updated.";
				mailstatus = mailHandler.sendTravelRequestStatusMail(updateStatus, reqMaster, reqOwnerDetails);
			}else if("rejected".equals(updateStatus)){
				message = "Travel request has been rejected.";
				mailstatus = mailHandler.sendTravelRequestStatusMail(updateStatus, reqMaster, reqOwnerDetails);
			}
			System.out.println("mail sent status:::"+mailstatus);
			rd = request.getRequestDispatcher("/web/employeeHome.jsp");	
			request.setAttribute("displayMessage", message);
			//rd.forward(request,response);
		}else{
			rd = request.getRequestDispatcher("/NagApp/login.jsp");	
			request.setAttribute("displayMessage", "Please log in to your account.");
		}
		
		rd.forward(request,response);
	}

}
