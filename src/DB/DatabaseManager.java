package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static DatabaseManager instance;
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "spotify";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "vdk123";
	
	private Connection con = null;
	
	private DatabaseManager(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
		}
		//create the connection
		try {
			con = DriverManager.getConnection("jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_NAME,DB_USER, DB_PASS);
		} catch (SQLException e) {
			System.out.println("Error connecting to Database - " + e.getMessage());
		}
	}
	
	public static synchronized DatabaseManager getInstance(){
		if(instance == null){
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return con;
	}

}
