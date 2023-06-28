package controller.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DbConnection.DbConnection;

/**
 * Servlet implementation class deleteCart
 */
@WebServlet("/deleteCart")
public class deleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("pages/cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
//	It will redirect the user to cart.jsp. It takes responses and requests as
//	parameters. It calls deleteCartItem, purchase method to delete item and
//	purchase item respectively.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("mode");
		
		DbConnection connection = new DbConnection();
		if(method.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("cartId"));
			boolean success = DbConnection.deleteCartItem(id);
			response.sendRedirect("pages/cart.jsp");
		}
		if(method.equals("checkout")) {
			int checkOut = Integer.parseInt(request.getParameter("checkOut"));
			boolean success = DbConnection.purchase(checkOut);
			if(success) {
				System.out.println("Purchase Successfull");
			}
			else {
				System.out.println("Purchase Not Successfull");
			}
			if(success) {
				System.out.println("Successfully Purchased");
				request.setAttribute("purchaseSuccess","Successfully Purchased");
				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/cart.jsp");
				dispatcher.include(request,response);
			}
			
		}
	}

}
