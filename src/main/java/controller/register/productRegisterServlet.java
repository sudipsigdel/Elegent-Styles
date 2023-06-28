package controller.register;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DbConnection.DbConnection;
import resources.Constants;

/**
 * Servlet implementation class productRegisterServlet
 */
@WebServlet("/productRegisterServlet")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class productRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productRegisterServlet() {
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
//	anything. This method calls addProduct from DbConnection and will add
//	the item to the database.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("product_name");
		String type = request.getParameter("product_type");
		String category = request.getParameter("category");
		String rating = request.getParameter("rating");
		String stock = request.getParameter("stock");
		String price = request.getParameter("price");
		Part image = request.getPart("product_image");
		
		String savePath = Constants.IMAGE_DIR_SAVE_PATH_ITEM;
	    String fileName = Constants.getImageUrl(image);
	    if(!fileName.isEmpty() && fileName != null) {
    		image.write(savePath + fileName);
	    }
		
		boolean success = DbConnection.addProduct(name, type, category, rating, price, stock, fileName);
		if(success) {
			System.out.println("Item added Successfully");
		}
		response.sendRedirect("pages/admin/product.jsp");
	}

}
