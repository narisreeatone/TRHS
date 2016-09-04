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
		if(session==null){
			//request.setAttribute("errorMsg", "Please log in to your account.");			
			//res.sendRedirect("/NagApp/login.jsp");
			noSession = true;
		}else if(session.getAttribute("isUserLoggedIn") == null){
			//request.setAttribute("errorMsg", "Please log in to your account.");			
			//res.sendRedirect("/NagApp/login.jsp");
			noSession = true;
		}if(noSession){
			/*rd = request.getRequestDispatcher("/NagApp/login.jsp");
			request.setAttribute("errorMsg", "Please log in to your account.");
			rd.forward(request,response);*/
			session = req.getSession(true);
			session.setAttribute("errorMsg", "Please log in to your account.");
			res.sendRedirect("/NagApp/login.jsp");
			
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
