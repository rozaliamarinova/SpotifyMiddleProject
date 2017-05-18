package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidMobileNumberException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUserLoginException;
import Exceptions.InvalidUserNameException;
import Model.User;

public class UserDAO {
	
	
	private static final String SELECT_ALL_USERS = "SELECT userId,name,username,password,email,city,gender,birthday,mobilenumber,"
			+ "profilephoto_path from users"; 
	private static UserDAO instance;
	private static final String SELECT_USER = "SELECT userId,name,username,password,email,city,gender,birthday,mobilenumber,"
			+ "profilephoto_path from users where username = ?;";

	

	private UserDAO() {

	}

	public synchronized static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	
	public synchronized boolean saveUser(User user) {

		try {
			String sql = "INSERT INTO users (name,username,password,email,city,gender,birthday,mobilenumber,profilephoto_path) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
		
			// Date sqlDate = DateFormat.parse(5, user.getBirthday());
		
			statement.setString(1, user.getName());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getCity());
			statement.setString(6, user.getGender());
			 statement.setString(7, null);
			statement.setString(8, user.getMobileNumber());
			statement.setString(9, null);
			System.out.println("DAO" + user.getName());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Saving user operation successful.");
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Cannot add to DB." + e.getClass().getName() + " " + e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public boolean isValidLogin(String username, String password) {
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT username, password FROM spotify.users WHERE username = ? AND password = ?");

			ps.setString(1, username);
			ps.setString(2, password);
			System.out.println(username);
			System.out.println(password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()==false) {
				System.out.println("Wrong credentials.");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("User cannot be logged in.");

		}
		return true;
	}
	
	
//	 public List<User> getAllUsers() throws InvalidUserLoginException, InvalidEmailException, InvalidPasswordException, InvalidMobileNumberException, InvalidNameException, InvalidUserNameException {
//	        List<User> users = new ArrayList<User>();
//	        Statement st = null;
//	        try {
//	            st = DatabaseManager.getInstance().getConnection().createStatement();
//
//	            ResultSet resultSet = st.executeQuery(SELECT_ALL_USERS);
//	            while (resultSet.next()) {
//	                User user = new User( resultSet.getInt("userId"),
//	                					resultSet.getString("name"),
//	                					resultSet.getString("username"), 
//	                					resultSet.getString("password"),
//	                					resultSet.getString("city"),
//	                					resultSet.getString("gender"),
//	                					resultSet.getString("mobileNumber"),
//	                					resultSet.getDate("birthday"), 
//	                					resultSet.getString("mobileNumber"));
//	            
//	              
//	                users.add(user);
//	            }
//
//	        } catch (SQLException e) {
//
//	            System.out.println("Error in DB");
//	            e.printStackTrace();
//
//	        } finally {
//	                    try {
//							st.close();
//						} catch (SQLException e) {
//							System.out.println("ops");
//						}
//	               
//	        }
//	        return users;
//	    }
//	 
	 
	 public User getUser(String username)  {
	        User user = null;
	        PreparedStatement ps = null;
	        System.out.println("V DB SME" +username);
	        
	        try {
				ps = DatabaseManager.getInstance().getConnection().prepareStatement(SELECT_USER);
				ps.setString(1, username);
	            ResultSet resultSet = ps.executeQuery();
	            
	            while (resultSet.next()) {
	                try {
						user = new User( resultSet.getString("name"),
											resultSet.getString("username"), 
											resultSet.getString("password"),
											resultSet.getString("email"),
											resultSet.getString("city"),
											resultSet.getDate("birthday"),
											resultSet.getString("gender"),
											resultSet.getString("mobileNumber"));
					} catch (InvalidUserLoginException | InvalidEmailException | InvalidPasswordException
							| InvalidMobileNumberException | InvalidNameException | InvalidUserNameException e) {
						System.out.println("Invalid data.");
					}
	                user.setId(resultSet.getInt("userId"));
	                System.out.println("Eho" +user);
	              
	            }

	        } catch (SQLException e) {

	            System.out.println("Error in DB");
	            e.printStackTrace();

	        } finally {
	                    try {
							ps.close();
						} catch (SQLException e) {
							System.out.println("ops");
						}
	               
	        }
	        return user;
	    }
	 
	 
	 


}
