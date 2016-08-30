package com.nag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nag.dao.DataBaseConnection;

/**
 * Servlet implementation class AddNewServices
 */
@WebServlet("/AddNewServices")
public class AddNewServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewServices() {
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
		RequestDispatcher rd;
		String serviceType = request.getParameter("seviceType");
		String serviceName = request.getParameter("newServiceName");
		System.out.println("service type:::"+serviceType+" \nserviceName:::"+serviceName);
		boolean status = dbHandler.addNewService(serviceType, serviceName);
		String displayMessage = "Some error has been found while adding service.";
		if(status){
			displayMessage = "Successfully added to Database.";
		}
		rd = request.getRequestDispatcher("AdminHome.jsp");	
		request.setAttribute("displayMessage", displayMessage);
		rd.forward(request,response);
	}

}
