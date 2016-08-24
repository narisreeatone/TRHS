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

import com.nag.bean.EmployeeDetails;
import com.nag.dao.DataBaseConnection;
import com.nag.sql.ConstantInfoFromDB;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		RequestDispatcher rd;
		
		//String currentPwd = request.getParameter("currentPwd");
		String newPwd1 = request.getParameter("newPwd1");
		String newPwd2 = request.getParameter("newPwd2");
		EmployeeDetails empDetails = (EmployeeDetails)session.getAttribute("loginUserDetails");
		boolean isRandomPwd = (boolean)session.getAttribute("isRandomPwd");
		boolean updatePwdStatus = false;
		String displayMessage = "";
		rd = request.getRequestDispatcher("login.jsp");
		if(newPwd1.equals(newPwd2)){
			updatePwdStatus = dbHandler.updatePassword(empDetails.getEmployeeDetailsId(), isRandomPwd, newPwd1);
			if(updatePwdStatus){
				rd = request.getRequestDispatcher("employeeHome.jsp");
				displayMessage = "Password has been changed.";
			}
		}else{
			rd = request.getRequestDispatcher("ChangePassword.jsp");
			displayMessage = "Entered new password and re-enter new password doesn't match.";
		}
		session.setAttribute("displayMessage", displayMessage);
		rd.forward(request,response);
	}

}
