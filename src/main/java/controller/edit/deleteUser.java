package controller.edit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DbConnection.DbConnection;

/**
 * Servlet implementation class deleteUser
 */
@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("pages/admin/user.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
//		It takes requests and responses as parameters and does not return anything.
//		This method calls deleteUser method from DbConnection class and redirects
//		the user to user.jsp.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("action"));
		boolean success = DbConnection.deleteUser(id);
		if(success) {
			request.setAttribute("deleteUser","User Deleted Successfully");
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
			dispatcher.include(request,response);
		}else {
			request.setAttribute("deleteUserError","Unable to delete User");
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
			dispatcher.include(request,response);
		}
	}

}
