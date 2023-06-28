package controller.edit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DbConnection.DbConnection;
import model.userDetail;

/**
 * Servlet implementation class roleEdit
 */
@WebServlet("/roleEdit")
public class roleEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public roleEdit() {
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
	
//	It takes requests and responses as parameters and does not return anything.
//	This method extracts calls addRole method to add the admin role to the user
//	and removeRole to remove the role as an admin.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		if(method.equals("add")) {
			String email = request.getParameter("add_role");
			int id = userDetail.userId(email);
			if(id > 0) {
				boolean success = DbConnection.addRole(id);
				if(success) {
					request.setAttribute("addRole","Role Added Successfully");
					RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
					dispatcher.include(request,response);
				}
				else {
					request.setAttribute("addRoleError","Unable to add Role");
					RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
					dispatcher.include(request,response);
				}
			}
			else {
				request.setAttribute("userNotFound","User Not Found");
				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
				dispatcher.include(request,response);
			}
			
		}else if(method.equals("remove")) {
			String email = request.getParameter("remove_role");
			int id = userDetail.userId(email);
			if(id > 0) {
				boolean success = DbConnection.removeRole(id);
				if(success) {
					request.setAttribute("removeRole","Removed Role");
					RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
					dispatcher.include(request,response);
				}
				else {
					request.setAttribute("removeRoleError","Unable To Remove Role");
					RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
					dispatcher.include(request,response);
				}
			}
			else {
				request.setAttribute("userNotFoundRemove","User Not Found");
				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/user.jsp");
				dispatcher.include(request,response);
			}
		}
	}

}
