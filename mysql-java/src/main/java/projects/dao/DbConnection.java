package projects.dao;
import java.sql.*;

import java.sql.DriverManager;

public class DbConnection {
	private static String HOST = "localhost";
	private static String PASSWORD = "Passcode1";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	
	public static java.sql.Connection getConnection() throws SQLException{
		String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA, USER, PASSWORD);
		Connection dm = null;
		try {
			dm = DriverManager.getConnection(uri, USER, PASSWORD);
			System.out.println("Connection Succesfull");
			return dm;
			
		} catch(SQLException e) {
			System.out.println("Connection Failed! " + uri);
			throw new SQLException("DbException");
		}
		
	}
}
