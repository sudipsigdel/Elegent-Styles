package controller.Login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

import controller.DbConnection.DbConnection;

/**
 * Servlet implementation class login
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("pages/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

//	It takes requests and responses as parameters and does not return anything.
//	This method extracts the values from the form and calls isUserRegisterd
//	method to let the user log in and it also creates cookies and session for further
//	process.

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// TODO Auto-generated method stub
		String userName = request.getParameter("email");
		String password = request.getParameter("password");

		DbConnection connection = new DbConnection();

		boolean isUserRegistered;
		ResultSet data;

		int time = 30*60;
		try {

			isUserRegistered = connection.isUserRegistered(userName, password);
			if (isUserRegistered == true) {
				HttpSession session = request.getSession();
				session.setAttribute("user", userName);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(time);
				
				
				
				data = connection.dataForCookies(userName);
				if (data.next()) {
					String id =  data.getString("id");
					String name = data.getString("name");
					String address = data.getString("address");
					String phone = data.getString("phone");
					String role = data.getString("isadmin");
					String replacedName = name.replaceAll("\\s+", ".");
					
					

					Cookie userNameCookies = new Cookie("username", userName);
					userNameCookies.setMaxAge(time);
					response.addCookie(userNameCookies);
					
					Cookie idCookie = new Cookie("id", id);
					idCookie.setMaxAge(time);
					response.addCookie(idCookie);

					Cookie nameCookies = new Cookie("name", replacedName);
					nameCookies.setMaxAge(time);
					response.addCookie(nameCookies);

					Cookie addressCookies = new Cookie("address", address);
					addressCookies.setMaxAge(time);
					response.addCookie(addressCookies);

					Cookie phoneCookies = new Cookie("phone", phone);
					phoneCookies.setMaxAge(time);
					response.addCookie(phoneCookies);

					Cookie roleCookies = new Cookie("role", role);
					roleCookies.setMaxAge(time);
					response.addCookie(roleCookies);
				} else {
					System.out.println("No Data Found");
				}

				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else {
				request.setAttribute("invalidIdPassword", "Invalid username or password");
				// forward request to login page
				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login.jsp");
				dispatcher.include(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Error");
			e.printStackTrace();
		}

	}

}
