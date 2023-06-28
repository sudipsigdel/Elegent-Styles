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
 * Servlet implementation class userEditServlet
 */
@WebServlet("/userEditServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class userEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String password;
    public userEditServlet() {
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
	
//	It takes requests and responses as parameters and does not return anything.
//	This method extracts different parameters from form and calls updateUser and
//	updateUserPhoto from the DbConnection class to edit items according to
//	needs.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		String address = request.getParameter("address");
		Part imageName = request.getPart("image");
		System.out.println("Image Part Name is: "+ imageName);

	    String fileName = Constants.getImageUrl(imageName);
		
		if(fileName != null) {
			System.out.println("Image Is not null");
			String savePath = Constants.IMAGE_DIR_SAVE_PATH;
		    if(!fileName.isEmpty() && fileName != null) {
	    		imageName.write(savePath + fileName);
		    }
		    try {
				boolean success = DbConnection.updateUser(id, name, address,password, fileName);
				if(success) {

			    	response.sendRedirect("pages/profile.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}else {
			try {

				System.out.println("Image Is  null");
				boolean success = DbConnection.updateUserPhoto(id, name, address,password);
				if(success) {
					response.sendRedirect("pages/profile.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
