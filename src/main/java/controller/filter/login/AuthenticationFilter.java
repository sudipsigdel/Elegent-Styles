package controller.filter.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	private ServletContext context;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

//		It takes requests and responses as parameters and does not return anything.
//		This method extracts the requested URL and will not allow the user to redirect
//		to different pages without meeting the necessary conditions.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		System.out.println(uri);
		boolean isProfile = uri.endsWith("profile.jsp");
		boolean isCart = uri.endsWith("cart.jsp");
		boolean isLoginJsp = uri.endsWith("login.jsp");
		boolean isRegisterJsp = uri.endsWith("register.jsp");
		boolean isloginServlet = uri.endsWith("loginServlet");
		boolean isRegisterServlet = uri.endsWith("registerServlet");

		boolean isCartServlet = uri.endsWith("cart");

		HttpSession session = req.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

		boolean isAdminJSP = uri.contains("/admin/");
		boolean isAdmin = false;

		if (isLoggedIn) {
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("role")) {
					System.out.println(cookie.getValue());
					isAdmin = cookie.getValue().equals("true");
				}
			}
		}
		if (isLoggedIn && (isLoginJsp || isRegisterJsp)) {
			res.sendRedirect(req.getContextPath() + "/index.jsp");

		} else if (!isLoggedIn && (isCart || isProfile || isCartServlet)) {
			res.sendRedirect(req.getContextPath() + "/pages/login.jsp");

		}else if(!isAdmin && isAdminJSP && isLoggedIn) {
			
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}else if(!isAdmin && isAdminJSP && !isLoggedIn)
			res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
		else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
