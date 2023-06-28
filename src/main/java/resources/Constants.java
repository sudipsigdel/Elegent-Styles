package resources;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Part;

public class Constants {
	public static String userInputSql = "INSERT INTO `register` (`name`, `address`, `email`, `phone`, `password`, `image`, `isadmin`) VALUES ( ?,?,?,?,?,?,?);";
	public static final String checkUserLoginInfo = "SELECT `email`,`password` from `register` where `email`= ?;";
	public static final String userDetailCookies = "SELECT * from `register` where email = ?;";
	private static final String algorithm = "AES";
	
	public static final String IMAGE_DIR = "Users\\Acer\\1. Java\\Coursework\\src\\main\\webapp\\images\\user\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR;
	
	public static final String IMAGE_DIR_ITEM = "Users\\Acer\\1. Java\\Coursework\\src\\main\\webapp\\images\\product\\";
	public static final String IMAGE_DIR_SAVE_PATH_ITEM = "C:" + File.separator + IMAGE_DIR_ITEM;
	
	public static final String addCategory = "INSERT INTO `product` ( `product_name`, `product_type`, `category`, `rating`, `price`, `stock`, `product_image`) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
	
	public static final String products = "SELECT * from `product`;";
	public static final String womensWear = "SELECT * from `product` WHERE `category`= Women's Wear;";
	public static final String mensWear = "SELECT * from `product` WHERE `category`= Men's Wear;";
	public static final String shoes = "SELECT * from `product` WHERE `category`= Shoes;";
	public static final String bags = "SELECT * from `product` WHERE `category`= Bags;";
	public static final String getUserId = "SELECT id from `register` WHERE email= (?);";
	public static final String cartAdd = "INSERT INTO `cart` ( `user_id`, `product_id`) VALUES (?,?);";
	public static final String deleteCart = "DELETE FROM `cart` WHERE cart_id = (?)";
	public static final String purchaseAdd = "INSERT INTO `purchase` (`user_id`, `product_id`)  SELECT user_id, product_id FROM cart WHERE user_id = ? ;";
	public static final String deleteAllCart = "DELETE FROM `cart` WHERE user_id = ?";
	public static final String deleteItem = "DELETE FROM `product` WHERE product_id = ?";
	public static final String updateItem = "UPDATE product SET product_name = ?, product_type = ?, category = ?, rating=?, price=?, stock = ?, product_image = ? WHERE product_id = ?";
	public static final String updateItemPhoto = "UPDATE product SET product_name = ?, product_type = ?, category = ?, rating=?, price=?, stock = ? WHERE product_id = ?";
	public static final String addRole = "UPDATE register SET isadmin = 'true' WHERE id = ?";
	public static final String removeRole = "UPDATE register SET isadmin = 'false' WHERE id = ?";
	public static final String removeUser = "DELETE FROM register WHERE id = ?";
	public static final String editUser = "UPDATE register SET name = ?, address = ?, password = ?, image = ? WHERE id = ?";
	public static final String editUserPhoto = "UPDATE register SET name = ?, address = ?, password = ? WHERE id = ?";
	public static final String deleteCartItem = "DELETE FROM `cart` WHERE product_id = (?)";
	public static final String deletePurchaseItem = "DELETE FROM purchase WHERE product_id = ? ";
	
//	It takes a password and secret password as parameters and returns a
//	String value. This method encrypts the username using AES method.
	public static String encrypt(String passwordEncrypt, String secretPassword) throws Exception {
		Key key = generateKey(secretPassword);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedBytes = cipher.doFinal(passwordEncrypt.getBytes(StandardCharsets.UTF_8));
		return new String(encryptedBytes,StandardCharsets.ISO_8859_1);
	}
	
//	It takes a username and secret password as parameters and returns a
//	String value. This method decrypts the password using AES method. 
	public static String decrypt(String passwordDecrypt, String secretPassword) throws Exception {
		Key key = generateKey(secretPassword);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedBytes = cipher.doFinal(passwordDecrypt.getBytes(StandardCharsets.ISO_8859_1));
		return new String(decryptedBytes,StandardCharsets.UTF_8);
	}
	
	private static Key generateKey(String secretPassword) {
		byte [] byteKey = secretPassword.getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(byteKey,algorithm);
	}
	   
//	It takes part as a parameter and returns String. This method returns the
//	name of the image from the part
	public static String getImageUrl(Part part) {
		String savePath = IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if(imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			return null;
		}
		return imageUrlFromPart;
	}
	
}
