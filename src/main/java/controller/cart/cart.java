package controller.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import controller.DbConnection.DbConnection;


/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // It will redirect the user to index.jsp. It takes responses and requests as parameters.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// It takes request and response as parameters and returns nothing. This method calls addItemCart to add the item to the cart database.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnection connection = new DbConnection();
		int id = Integer.parseInt(request.getParameter("productId"));

		HttpSession session = request.getSession(false);
		String user =(String) session.getAttribute("user");
		System.out.println("UserNameCookies:"+user);
		System.out.println("ProductId:"+id);
		boolean success = connection.addItemCart(user,id);
		
		if(success) {
			System.out.println("Success");
			request.setAttribute("itemAdded","Item Added To cart");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.include(request,response);
		}
		else {
			System.out.println("Unsuccess");
			request.setAttribute("cartError","Unable to add into cart");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.include(request,response);
		}
	}

}
