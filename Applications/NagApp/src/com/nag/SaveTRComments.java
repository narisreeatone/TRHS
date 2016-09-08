package com.nag;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nag.dao.DataBaseConnection;
import com.nag.mail.MailHandler;
import com.nag.util.ValidateUserSession;
import com.nag.bean.TravelRequestMaster;
/**
 * Servlet implementation class SaveTRComments
 */
@WebServlet("/web/SaveTRComments")
public class SaveTRComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveTRComments() {
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
		RequestDispatcher rd = null;
		String senderId = request.getParameter("senderId");
		String recieverId = request.getParameter("recieverId");
		String reqVersionId = request.getParameter("reqVersionId1");
		String reqMasterId = request.getParameter("reqMasterId1");
		String comments = request.getParameter("comment");		
		
		//String reqOwnerName = request.getParameter("reqOwnerName");
		String senderName = request.getParameter("senderName");
		String commentsBy = request.getParameter("commentsBy");
		String recieverName = request.getParameter("recieverName1");		
		String recieverMailId = request.getParameter("recieverMailId1");		
		
		ValidateUserSession validateUserSession = new ValidateUserSession();
		HttpSession session = request.getSession(false);
		if(!validateUserSession.checkUserSession(session)){
			DataBaseConnection dbHandler = new DataBaseConnection();
			boolean dbStatus = dbHandler.saveComment(senderId,senderName,recieverId,reqMasterId,reqVersionId,comments);
			if(dbStatus){	
				TravelRequestMaster requestDetails = dbHandler.getTravelRequestDetails(reqMasterId);
				MailHandler mailHandler = new MailHandler();
				mailHandler.sendTRComments(recieverMailId, senderName, comments, commentsBy, requestDetails);
				if("requestApprover".equals(commentsBy))
					request.setAttribute("displayMessage", "Clarification request has been sent to "+recieverName);
				if("requestOwner".equals(commentsBy))
					request.setAttribute("displayMessage", "Your clarification on travel request has been sent to "+recieverName);
			}else{
				request.setAttribute("displayMessage", "There is a problem in sending clarification request to "+recieverName);	
			}
			rd = request.getRequestDispatcher("/web/employeeHome.jsp");
			rd.forward(request,response);
		}else{
			request.setAttribute("errorMsg", "Session is invalid. Please log in to your account.");
			response.sendRedirect("/NagApp/login.jsp");
		}
	}

}
