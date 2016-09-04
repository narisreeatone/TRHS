package com.nag;

import java.io.IOException;
import java.util.Map;

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
import com.nag.util.ValidateUserSession;

/**
 * Servlet implementation class TravelRequestDetails
 */
@WebServlet("/web/TravelRequestDetails")
public class TravelRequestDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelRequestDetails() {
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
			EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
			String loggedEmpDetailsId = empDetails.getEmployeeDetailsId();
			String requestFrom = request.getParameter("requestFrom");
			String travelRequestMasterId = request.getParameter("travelRequestMasterId");
			TravelRequestMaster requestDetails = dbHandler.getTravelRequestDetails(travelRequestMasterId);
					
			rd = request.getRequestDispatcher("/web/DisplayRequestDetails.jsp");		
			request.setAttribute("requestDetails", requestDetails);
			if("owner".equals(requestFrom) || "completed".equals(requestFrom)){
				request.setAttribute("hideBtns", "hideBtns");			
			}
			//rd.forward(request,response);
		}else{
			rd = request.getRequestDispatcher("/NagApp/login.jsp");	
			request.setAttribute("displayMessage", "Please log in to your account.");
		}
		
		rd.forward(request,response);
	}

}
