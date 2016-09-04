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
import com.nag.sql.RequestStatus;
import com.nag.util.ValidateUserSession;

/**
 * Servlet implementation class GetAllPendingRequest
 */
@WebServlet("/web/GetAllPendingRequest")
public class GetAllPendingRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllPendingRequest() {
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
		RequestDispatcher rd = null;
		ValidateUserSession validateUserSession = new ValidateUserSession();
		if(!validateUserSession.checkUserSession(session)){
			DataBaseConnection dbHandler = new DataBaseConnection();
			RequestStatus reqStatus = new RequestStatus();
			EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
			String empDetailsId = empDetails.getEmployeeDetailsId();
			Map <String, TravelRequestMaster> allPendingRequestMap = dbHandler.getAllTravelRequestByStatus(reqStatus.PENDING);		
			rd = request.getRequestDispatcher("/web/DisplayAllPendingRequest.jsp");		
			request.setAttribute("allPendingRequestMap", allPendingRequestMap);
			rd.forward(request,response);
		}else{
			//rd = request.getRequestDispatcher("/NagApp/login.jsp");	
			request.setAttribute("errorMsg", "Please log in to your account.");
			response.sendRedirect("/NagApp/login.jsp");
		}
		
		//rd.forward(request,response);
	}

}
