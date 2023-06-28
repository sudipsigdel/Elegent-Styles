package model;

import controller.DbConnection.DbConnection;

public class userDetail {
//	It takes the username as a parameter and returns an int value. This
//	method calls the getUserId from DbConnection class and returns userId
	public static int userId(String userName) {
		DbConnection conn = new DbConnection();
		int userId = conn.getUserId(userName);
		return userId;
	}
}
