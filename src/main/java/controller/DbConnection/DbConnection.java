package controller.DbConnection;

import java.sql.*;

import com.mysql.cj.xdevapi.Result;

import resources.Constants;


public class DbConnection {
//			It is a static method whose return type is a connection. It will returns the
//			connection with the database.
		public static Connection getConnection()
		{
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework", "root", "");
				
				return connection;
				
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
//			It is an instance method whose return type is a Boolean and takes username
//			and password as a parameter. It checks if the user provided id pass is correct
//			or not.
		public boolean isUserRegistered(String username, String password) throws Exception {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.checkUserLoginInfo);
					statement.setString(1, username);
					ResultSet result = statement.executeQuery();
					if(result.next()) {
						String dbPasswordEncrypt = result.getString("password");
						String dbPasswordDecrypt = Constants.decrypt(dbPasswordEncrypt, "my-secret-key-is");
						if(dbPasswordDecrypt != null && dbPasswordDecrypt.equals(password)) {
							System.out.println("Data Matched");
							return true;
						}
						else {
							System.out.println("data not matched");
							return false;
						
						}
					}
					else {
						System.out.println("No Data In DatabAse");
						return false;
					}
				}
				catch(SQLException e) {
					System.out.println("Login SQL Exception");
					return false;
				}
				catch(Exception e) {
					System.out.println("Login Exception");
					return false;
				}
			}
			return false;
			
			
		}
//		This method takes username as parameter and returns ResultSet. This method
//		collects the user information and returns it.
		public ResultSet dataForCookies(String username)throws Exception {
			Connection conn = getConnection();
			if(conn !=null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.userDetailCookies);
					statement.setString(1, username);
					ResultSet result = statement.executeQuery();
					return result;
					
				}catch(Exception e) {
					e.printStackTrace();
					return null;
					
				}
				
			}
			return null;
		}
		
//		This method takes a username as a parameter and returns an integer. This
//		method gets the id of the user and returns it.
		public int getUserId(String username) {
			Connection conn = getConnection();
			if(conn != null) {
				PreparedStatement statement;
				try {
					statement = conn.prepareStatement(Constants.getUserId);

					statement.setString(1, username);
					ResultSet result = statement.executeQuery();
					if(result.next()) {
						return result.getInt("id");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
				
			}
			return 0;
			
		}
		
//		This method takes a username, productId as a parameter and returns a
//		Boolean. This method adds the item to the cart.
		public boolean addItemCart(String username, int productId) {
			DbConnection connection = new DbConnection();
			Connection conn = getConnection();
			int userId = connection.getUserId(username);
			
			if(userId > 0) {
				if(conn != null) {
					try {
						PreparedStatement statement = conn.prepareStatement(Constants.cartAdd);
						System.out.println("UserId: "+ userId + "ProductId"+ productId);
						statement.setInt(1, userId);
						statement.setInt(2, productId);
						int i = statement.executeUpdate();
						if(i>0) {
							return true;
						}
						else {
							return false;
						}
							
						
						
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}
			}
			
			
		return false;	
		}
		
//		This method takes a username, productId as a parameter and returns a
//		Boolean. This method removes the item from the cart
		public static boolean deleteCartItem(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.deleteCart);
					statement.setInt(1,id);
					int i = statement.executeUpdate();
					if(i >0) {
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
			}
			return false;
		}
		
//		This method takes an id as a parameter and returns a Boolean. This method
//		allows user to buy item.
		public static boolean purchase(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.purchaseAdd);
					statement.setInt(1, id);
					System.out.println(Constants.purchaseAdd);
					int i = statement.executeUpdate();
					if(i>0) {
						boolean j = DbConnection.deleteCart(id);
						if(j) {
							return true;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
			}
			return false;
		}
		
//		This method takes an id as a parameter and returns a Boolean. This method
//		deletes all the item from the cart
		public static boolean deleteCart(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				PreparedStatement statement;
				try {
					statement = conn.prepareStatement(Constants.deleteAllCart);
					statement.setInt(1, id);
					int i = statement.executeUpdate();
					if(i > 0) {
						return true;
					}
					else {
						return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
				
			}
			return false;
		}
		
//		This method takes the product name, type, category, rating, and price. Stock
//		and image as a parameter and return a Boolean value. This method also adds
//		products in the database.
		public static boolean addProduct(String name, String type, String category, String rating, String price, String stock, String image) {
			Connection conn = getConnection();
			if(conn != null) {
				PreparedStatement statement;
				try {
					statement = conn.prepareStatement(Constants.addCategory);
					statement.setString(1,name);
					statement.setString(2,type);
					statement.setString(3, category);
					statement.setString(4,rating);
					statement.setString(5, price);
					statement.setString(6, stock);
					statement.setString(7,image);
					int i = statement.executeUpdate();
					if(i>0) {
						System.out.println("Data Entered Successfully");
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
			}
			
			
			return false;
			
		}
		
//		This method takes id as a parameter and returns a Boolean value. This
//		method deletes the products.
		public static boolean deleteItem(int id) {
			Connection conn = getConnection();
			boolean cartSuccess = DbConnection.deleteCartItemAdmin(id);
			boolean purchaseSuccess = DbConnection.deletePurchaseItemAdmin(id);
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.deleteItem);
					statement.setInt(1, id);
					int i = statement.executeUpdate();
					if(i>0) {
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
			}
			return false;
		}
		
//		This method takes the ID as a parameter and returns a Boolean value.
//		This method deletes products from cart table.
		public static boolean deleteCartItemAdmin(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				PreparedStatement statement;
				try {
					statement = conn.prepareStatement(Constants.deleteCartItem);
					statement.setInt(1, id);
					int i = statement.executeUpdate();
					if(i>0) {
						return true;
					}else {
						return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
				
			}
			return false;
		}
		
//		This method takes the ID as a parameter and returns a Boolean value.
//		This method deletes products from the purchase table.
		public static boolean deletePurchaseItemAdmin(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				PreparedStatement statement;
				try {
					statement = conn.prepareStatement(Constants.deletePurchaseItem);
					statement.setInt(1, id);
					int i = statement.executeUpdate();
					if(i>0) {
						return true;
					}else {
						return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
				
			}
			return false;
		}
		
//		This method takes the ID, name, type, category, rating, price, stock,
//		image as a parameter and returns a Boolean value. This method edits
//		the details of the products except image.
		public static boolean editItemProduct(int id, String name, String type, String category, int rating, int price, int stock, String image) {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.updateItem);
					statement.setString(1,name );
					statement.setString(2,type );
					statement.setString(3, category );
					statement.setInt(4,rating );
					statement.setInt(5,price );
					statement.setInt(6,stock );
					statement.setString(7,image);
					statement.setInt(8,id);
					int i = statement.executeUpdate();
					if(i > 0) {
						return true;
					}
					else {
						return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
			}
			
			return false;
		}
		
//		This method takes the ID, name, type, category, rating, price, stock,
//		image as a parameter and returns a Boolean value. This method edits
//		the details of the products including images
		public static boolean editItemProductImage(int id, String name, String type, String category, int rating, int price, int stock) {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.updateItemPhoto);
					statement.setString(1,name );
					statement.setString(2,type );
					statement.setString(3, category );
					statement.setInt(4,rating );
					statement.setInt(5,price );
					statement.setInt(6,stock );
					statement.setInt(7,id);
					int i = statement.executeUpdate();
					if(i > 0) {
						return true;
					}
					else {
						return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
			}
			
			return false;
		}
		
//		This method takes id as a parameter and returns Boolean. It gets the
//		connection from DbConnection and it will convert the user to admin.
		public static boolean addRole(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.addRole);
					statement.setInt(1, id);
					int i = statement.executeUpdate();
					if(i>0) {
						return true;
					}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
			return false;
		}
		
//		This method takes id as parameter and returns a Boolean. It gets the
//		connection from DbConnection and it will remove user from admin.
		public static boolean removeRole(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.removeRole);
					statement.setInt(1, id);
					int i = statement.executeUpdate();
					if(i>0) {
						return true;
					}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
			return false;
		}
		
//		This method takes id as parameter and returns Boolean. It gets the
//		connection of database and executes query to delete the users.
		public static boolean deleteUser(int id) {
			Connection conn = getConnection();
			if(conn != null) {
				
					try {
						PreparedStatement statement = conn.prepareStatement(Constants.removeUser);
						statement.setInt(1, id);
						int i = statement.executeUpdate();
						if(i>0) {
							return true;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
					return false;
					
				
			}
			return false;
		}
		
//		This method takes id, name, address, password, image as parameter
//		and returns a Boolean value. This method updates the details of users
//		except image
		public static boolean updateUser(int id, String name, String address,  String password, String image) throws Exception {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.editUser);
					statement.setString(1, name);
					statement.setString(2, address);
					statement.setString(3, Constants.encrypt(password, "my-secret-key-is"));
					statement.setString(4, image);
					statement.setInt(5, id);
					int i = statement.executeUpdate();
					if(i > 0) {
						return true;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				return false;
				
			}
			return false;
		}
		
//		This method takes id, name, address, password, image as parameter
//		and returns a Boolean value. This method updates the details of users
//		including image.
		public static boolean updateUserPhoto(int id, String name, String address,  String password) throws Exception {
			Connection conn = getConnection();
			if(conn != null) {
				try {
					PreparedStatement statement = conn.prepareStatement(Constants.editUserPhoto);
					statement.setString(1, name);
					statement.setString(2, address);
					statement.setString(3, Constants.encrypt(password, "my-secret-key-is"));
					statement.setInt(4, id);
					int i = statement.executeUpdate();
					if(i > 0) {
						return true;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				return false;
				
			}
			return false;
		}
}
