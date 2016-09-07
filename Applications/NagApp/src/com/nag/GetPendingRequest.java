package com.nag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import com.nag.bean.*;
import com.nag.dao.*;
import com.nag.formbean.*;
import com.nag.util.ValidateUserSession;

/**
 * Servlet implementation class GetPendingRequest
 */
@WebServlet("/web/GetPendingRequest")
public class GetPendingRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPendingRequest() {
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
			String empDetailsId = empDetails.getEmployeeDetailsId();
			Map <String, TravelRequestMaster> pendingRequestMap = dbHandler.getPendingRequestForEmployee(empDetailsId);		
			rd = request.getRequestDispatcher("/web/DisplayPendingTravelRequest.jsp");		
			request.setAttribute("pendingRequestMap", pendingRequestMap);
			rd.forward(request,response);
		}else{		
			request.setAttribute("errorMsg", "Session is invalid. Please log in to your account.");
			response.sendRedirect("/NagApp/login.jsp");
		}
	}

}
