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
 * Servlet implementation class GetAllApprovedRequest
 */
@WebServlet("/web/GetAllApprovedRequest")
public class GetAllApprovedRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllApprovedRequest() {
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
		ValidateUserSession validateUserSession = new ValidateUserSession();
		RequestDispatcher rd = null;
		HttpSession session = request.getSession(false);
		if(!validateUserSession.checkUserSession(session) && !session.isNew()){
			DataBaseConnection dbHandler = new DataBaseConnection();
			RequestStatus reqStatus = new RequestStatus();
			EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
			String empDetailsId = empDetails.getEmployeeDetailsId();
			Map <String, TravelRequestMaster> allApprovedRequestMap = dbHandler.getAllTravelRequestByStatus(reqStatus.APPROVED);		
			rd = request.getRequestDispatcher("/web/DisplayAllApprovedRequest.jsp");		
			request.setAttribute("allApprovedRequestMap", allApprovedRequestMap);
			rd.forward(request,response);
		}else{			
			request.setAttribute("errorMsg", "Please log in to your account.");
			response.sendRedirect("/NagApp/login.jsp");
		}
		
		//rd.forward(request,response);
	}

}
