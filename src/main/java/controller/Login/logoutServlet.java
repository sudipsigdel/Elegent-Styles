package controller.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("pages/profile.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
//	It takes requests and responses as parameters and does not return
//	anything. This method destroys all the cookies and sessions too
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Clear cookie
				Cookie[] cookies = request.getCookies();
		    	if(cookies != null){
			    	for(Cookie cookie : cookies){
			    		cookie.setMaxAge(0);
			    		response.addCookie(cookie);
			    	}
		    	}
		    	
		    	// Clear session
		    	HttpSession session = request.getSession(false);
		    	if(session != null){
		    		session.invalidate();
		    	}
		    	response.sendRedirect("pages/login.jsp");
	}

}
