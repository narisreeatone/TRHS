package com.nag;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import com.nag.dao.*;
import com.nag.bean.*;

/*import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		DataBaseConnection dbHandler = new DataBaseConnection();
		ServletContext application = getServletContext();
		HttpSession session = request.getSession(true);	
		RequestDispatcher rd;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");				
		EmployeeDetails empDetails = dbHandler.getEmployeeDetailsByLogin(username, password);
		
		if(empDetails != null){			
			rd = request.getRequestDispatcher("employeeHome.jsp");
			session.setAttribute("loginUserDetails", empDetails);
			session.setAttribute("isUserLoggedIn", "true");
			session.setAttribute("isAdmin", false);
			rd.forward(request,response);
		}else{			
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request,response);
		}
	}
		

}