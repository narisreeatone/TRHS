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
/**
 * Servlet implementation class UpdateTravelRequest
 */
@WebServlet("/UpdateTravelRequest")
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
		System.out.println("begin - display req details servlet");
		DataBaseConnection dbHandler = new DataBaseConnection();		
		HttpSession session = request.getSession();	
		RequestDispatcher rd;
		EmployeeDetails loggedEmpDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
		String loggedEmpDetailsId = loggedEmpDetails.getEmployeeDetailsId();
		
		String travelRequestVersionId = request.getParameter("travelRequestVersionId");
		String action = request.getParameter("action");
		boolean updateStatus = dbHandler.updateTravelRequest(action, travelRequestVersionId, loggedEmpDetailsId);
		String message = "Error in updating travel request.";
		if(updateStatus){
			message = "Travel request has been updated.";
		}
		
		rd = request.getRequestDispatcher("employeeHome.jsp");	
		request.setAttribute("displayMessage", message);
		rd.forward(request,response);
	}

}
