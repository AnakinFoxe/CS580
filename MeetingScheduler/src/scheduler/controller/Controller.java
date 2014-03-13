package scheduler.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import scheduler.model.Administrator;
import scheduler.model.Employee;
import scheduler.model.User;

public class Controller {
	protected static Connection connection = null;
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
	  }
	
	
	public static Integer checkLogin(String username, String password) {
		if (connection == null)
			return 0;
		
		try {
			String sql = "select * from user where usr_username='" + username + "' and usr_password='" + password +"'";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
	
			while (resultSet.next()) {
				Integer usr_id = resultSet.getInt("usr_id");
				String usr_username = resultSet.getString("usr_username");
				
				System.out.println("Successfully Login: id=" + usr_id + " username=" + usr_username);
				return usr_id;
			}
			
		
			return 0;
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return 0;
		}
	}
	
	
	public static User getUser(Integer usr_id) {
		if (usr_id == 0)
			return null;
		
		try {
			// check Employee table first
			String sql = "select * from employee where emp_usr_id='" + usr_id + "'";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Employee employee = new Employee(resultSet.getString("emp_first_name"),
												 resultSet.getString("emp_middle_name"),
												 resultSet.getString("emp_last_name"));
				
				return employee;
			}
			
			// then check Administrator table
			sql = "select * from administrator where adm_usr_id='" + usr_id + "'";
			
			//statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Administrator admin = new Administrator(resultSet.getString("adm_description"));
				
				return admin;
			}
			
			return null;
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static List<Employee> genEmployeeList() {
		if (Controller.connection == null)
			return null;
		
//		if (usr_id == null)
//			return null;
		
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			String sql = "select * from employee";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
//				System.out.println(resultSet.getInt("emp_usr_id"));
//				if (resultSet.getInt("emp_usr_id") ==  usr_id.intValue())
//					continue;
				Employee emp = new Employee(resultSet.getString("emp_first_name"),
						 					resultSet.getString("emp_middle_name"),
						 					resultSet.getString("emp_last_name"));
				empList.add(emp);
			}
			
			return empList;
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return null;
		}
	}
}
