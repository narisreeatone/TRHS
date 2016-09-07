package com.nag;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nag.dao.*;
import com.nag.util.*;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/web/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
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
		if (request.getSession(false) == null) {
			response.setStatus(403);
			return;
		}
		String action = request.getParameter("action");
		
		if("getEmpNames".equals(action)){			
			getEmployeeDetails(request,response);	
		} else if("saveComment".equals(action)){			
			saveComment(request, response);
		} else if("getLongDistanceRates".equals(action) || "getPPURates".equals(action)){			
			//getPPUAndLDRates(request, response);
		} else {
			// do nothing
		}
	}
	
	private void getEmployeeDetails(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String empName = request.getParameter("empName");
		if(empName != null){
			
		}
	}
	private void saveComment(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String senderId = request.getParameter("senderId");
		String recieverId = request.getParameter("recieverId");
		String reqVersionId = request.getParameter("reqVersionId");
		String reqMasterid = request.getParameter("reqMasterid");
		String comments = request.getParameter("comments");
		DataBaseConnection dbHandler = new DataBaseConnection();
		boolean dbStatus = dbHandler.saveComment(senderId,recieverId,reqMasterid,reqVersionId,comments);
		if(dbStatus)
			ServletUtil.writeJSONResponse(response, "true");
		else
			ServletUtil.writeJSONResponse(response, "false");
	}

}
