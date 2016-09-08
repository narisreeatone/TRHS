package com.nag;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.lang.StringUtils;

import javax.servlet.annotation.WebServlet;
import java.sql.*;
import com.nag.dao.*;
import com.nag.bean.*;
import com.nag.sql.*;

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
		HttpSession session = request.getSession(true);	
		session.setMaxInactiveInterval(900);
		RequestDispatcher rd;
		//ConstantInfoFromDB constantInfoFromDB = new ConstantInfoFromDB();		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		EmployeeDetails empDetails = null;
		if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)){
			empDetails = dbHandler.getEmployeeDetailsByLogin(username, password);
			if(empDetails != null){	
				if(empDetails.isAdmin()){					
					rd = request.getRequestDispatcher("/web/AdminHome.jsp");
					session.setAttribute("loginUserDetails", empDetails);
					session.setAttribute("isUserLoggedIn", false);
					session.setAttribute("isAdmin", true);
					session.removeAttribute("errorMsg");
				}else{
					System.out.println("empDetails.isRandomPwd():::"+empDetails.isRandomPwd());
					if(empDetails.isRandomPwd()){
						rd = request.getRequestDispatcher("/web/ChangePassword.jsp");
						session.setAttribute("loginUserDetails", empDetails);
						session.setAttribute("isUserLoggedIn", true);
						session.setAttribute("isRandomPwd", true);
						session.removeAttribute("errorMsg");						
					}else{						
						rd = request.getRequestDispatcher("/web/employeeHome.jsp");
						session.setAttribute("loginUserDetails", empDetails);
						session.setAttribute("isUserLoggedIn", true);
						session.setAttribute("isAdmin", false);	
						session.removeAttribute("errorMsg");						
					}
				}
				session.setAttribute("travelModesMap", ConstantInfoFromDB.getAllTravelModesFromDB());
				session.setAttribute("departmentMap", ConstantInfoFromDB.getDepartmentFromDB());
				session.setAttribute("designationMap", ConstantInfoFromDB.getDesignationFromDB());				
			}else{			
				rd = request.getRequestDispatcher("login.jsp");
				request.setAttribute("errorMsg", "Please enter correct username and password.");
			}
		}else{			
			request.setAttribute("errorMsg", "Please enter correct username and password."); 
			rd = request.getRequestDispatcher("login.jsp");			
		}		
		rd.forward(request,response);	
	}
}
