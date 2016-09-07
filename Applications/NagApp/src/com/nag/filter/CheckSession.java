package com.nag.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckSession
 */
@WebFilter("/CheckSession")
public class CheckSession implements Filter {

    /**
     * Default constructor. 
     */
    public CheckSession() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		RequestDispatcher rd = null;
		boolean noSession = false;
		if( session!= null ){
			if(session.getAttribute("isUserLoggedIn") == null)				
				noSession = true;			
		}		
		if( noSession ){
			session = req.getSession(true);
			session.setAttribute("errorMsg", "Please log in to your account.");
			res.sendRedirect("login.jsp");		
		}else if(res.getStatus() == 500){
			session = req.getSession(true);
			session.setAttribute("errorMsg", "Session is expired. Please log in to your account.");
			res.sendRedirect("login.jsp");
		}else
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
