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

/**
 * Servlet implementation class GetRejectedRequest
 */
@WebServlet("/GetRejectedRequest")
public class GetRejectedRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRejectedRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("begin - get aprroved req servlet");
		DataBaseConnection dbHandler = new DataBaseConnection();		
		HttpSession session = request.getSession();	
		RequestDispatcher rd;
		
		EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
		String empDetailsId = empDetails.getEmployeeDetailsId();
		Map <String, TravelRequestMaster> rejectedRequestMap = dbHandler.getRejectedRequestForEmployee(empDetailsId);
		System.out.println("rejected requests in servelt:::"+rejectedRequestMap.size());
		rd = request.getRequestDispatcher("DisplayRejectedRequest.jsp");		
		request.setAttribute("rejectedRequestMap", rejectedRequestMap);
		rd.forward(request,response);
	}

}