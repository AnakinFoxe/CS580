package scheduler.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class Controller {
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	public static void connect() {
		 
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
	 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		
	 
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/meeting_scheduler","cs580", "cs580");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		try {
			readData();
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is :"+ec+":"+msg+":"+state);  
			e.printStackTrace();
		}
	  }
	
	public static void readData() throws SQLException {
		if (connection == null)
			return;
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select * from employee");

		resultSet.next();
		Integer usr_id = resultSet.getInt("emp_usr_id");
		String first_name = resultSet.getString("emp_first_name");
		String last_name = resultSet.getString("emp_last_name");
		
		System.out.println("First: " + first_name);
		System.out.println("Last: " + last_name);
		

		
	}
}
