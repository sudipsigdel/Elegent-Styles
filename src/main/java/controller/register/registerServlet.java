package controller.register;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DbConnection.DbConnection;

import java.io.File;

import resources.Constants;

import java.sql.*;

/**
 * Servlet implementation class register
 */
@WebServlet("/registerServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50)

public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.sendRedirect("pages/signup.jsp");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
//    It takes requests and responses as parameters and does not return
//    anything. This method registers the user and stores it in the database.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter pw = response.getWriter();
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String isAdmin = "false";
		Part imagePart = request.getPart("image");	
		
	    String savePath = Constants.IMAGE_DIR_SAVE_PATH;
	    String fileName = Constants.getImageUrl(imagePart);
	    if(!fileName.isEmpty() && fileName != null) {
    		imagePart.write(savePath + fileName);
	    }
	    
	    System.out.println(savePath+fileName);
		
		
    
        
		DbConnection connection = new DbConnection();
		
		Connection conn = connection.getConnection();
		PreparedStatement statement = null;
		boolean duplicate = false;
		try {
			   
			   //Database value insertion
			   statement = conn.prepareStatement(Constants.userInputSql);
			   statement.setString(1,name);
			   statement.setString(2,address);
			   statement.setString(3,email);
			   statement.setString(4,phone);
			   statement.setString(5,Constants.encrypt(password, "my-secret-key-is"));
			   statement.setString(6,fileName);
			   statement.setString(7,isAdmin);
			   int result = statement.executeUpdate();
			   pw.print(result);
			  
			   if(result > 0) {
				  
					pw.print("Data Inserted Successfullu");

			   }
			   else {
				   pw.print("Data was not inserted successfully");
			   }
			   
			   System.out.println("The name of the image is:  "+fileName);
			   
		}catch(ClassNotFoundException e) {
			System.out.println("Class Not Found");
		}catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			System.out.println("Duplicate Data");
			
			
			duplicate = true;
			if (duplicate) {
				request.setAttribute("dublicateEntry","User have already created an account with this Email or phone Number");
				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/signup.jsp");
				dispatcher.include(request,response);
			}
			
			
		}catch(SQLException e){
			System.out.println("SQLExcpetion");
		}catch(Exception e) {
			System.out.println("Excpetion");
		}finally {
			try {
				
				if(statement !=null ) {
					statement.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		if(!duplicate)
		{
			response.sendRedirect("pages/login.jsp");
		}
		
	}

}
