package controller.edit;

import java.io.IOException;
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
 * Servlet implementation class editProduct
 */
@WebServlet("/editProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class editProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProduct() {
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
//	
//	It takes requests and responses as parameters and does not return anything.
//	This method extracts different parameters from form and calls editItemProduct
//	and editItemProductImage from the DbConnection class to edit items.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idName = request.getParameter("product_id");
		int id = Integer.parseInt(request.getParameter("product_id"));
		String name = request.getParameter("product_name");
		String type = request.getParameter("product_type");
		String category = request.getParameter("category");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int stock = Integer.parseInt(request.getParameter("stock"));	
		int price = Integer.parseInt(request.getParameter("price"));
		//String image = request.getParameter("product_image");
		Part image = request.getPart("product_image");

	    String fileName = Constants.getImageUrl(image);
		
		if(fileName != null) {
			Part imagePart = request.getPart("product_image");
			String savePath = Constants.IMAGE_DIR_SAVE_PATH_ITEM;
		    if(!fileName.isEmpty() && fileName != null) {
	    		imagePart.write(savePath + fileName);
		    }
		    
		    boolean success = DbConnection.editItemProduct(id, name, type, category, rating, price, stock, fileName);
		    if(success) {
		    	System.out.println("Success with image");
		    	response.sendRedirect("pages/admin/product.jsp");
		    }else {
		    	System.out.println("Success with image");
		    }
		    
		}else {
			boolean success = DbConnection.editItemProductImage(id, name, type, category, rating, price, stock);
			if(success) {
				System.out.println("Success without image");
				response.sendRedirect("pages/admin/product.jsp");
			}
		}
	}

}
