package controller.register;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DbConnection.DbConnection;

/**
 * Servlet implementation class productDelete
 */
@WebServlet("/productDelete")
public class productDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("pages/admin/product.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
//	It takes requests and responses as parameters and does not return
//	anything. This method is called the delete Item method from
//	DbConnection class and deletes the items
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("delete"));
		System.out.println("Id For Deleting Product is: "+productId);
		boolean success = DbConnection.deleteItem(productId);
		if(success) {
			System.out.println("Item Delteed Successfully");
			request.setAttribute("deleteItem","Item Deleted Successfully");
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/product.jsp");
			dispatcher.include(request,response);
		}
		else {
			System.out.println("Item Not Delteed Successfully");
			request.setAttribute("deleteItemError","Cannot Delete Product");
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/product.jsp");
			dispatcher.include(request,response);
		}
		
		
				
	}

}
