package scheduler.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import scheduler.model.Administrator;
import scheduler.model.Employee;
import scheduler.model.Meeting;
import scheduler.model.User;

public class Controller {
	protected static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static String sql = null;
	
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
			sql = "select * from user where usr_username='" + username + "' and usr_password='" + password +"'";
			
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
			sql = "select * from employee where emp_usr_id='" + usr_id + "'";
			
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
			sql = "select * from employee";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
//				System.out.println(resultSet.getInt("emp_usr_id"));
//				if (resultSet.getInt("emp_usr_id") ==  usr_id.intValue())
//					continue;
				Employee emp = new Employee(resultSet.getString("emp_first_name"),
						 					resultSet.getString("emp_middle_name"),
						 					resultSet.getString("emp_last_name"),
						 					resultSet.getInt("emp_usr_id"));
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
	
	
	public static List<Date> genAvailableTime(List<Employee> attendee) {
		if (Controller.connection == null)
			return null;
		
		if (attendee == null)
			return null;
		
		List<Date> dateList = new ArrayList<Date>();
		List<Meeting> metList = new ArrayList<Meeting>();
		Date now = new Date();
		//Calendar now = Calendar.getInstance();
		//int hour = Calendar.HOUR_OF_DAY;
		try {
			Integer idx = 0;
			// Construct the List of Meetings based on attendee list
			for (idx=0; idx<attendee.size(); ++idx) {
				Employee emp = attendee.get(idx);
				Integer emp_id = emp.getUsrId();
				
				sql = "select * from attendee where att_emp_id='" + emp_id + "'";
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					Meeting met = new Meeting(resultSet.getInt("att_sch_id"),
							 					resultSet.getInt("att_emp_id"));
					
					sql = "select * from schedule where sch_id='" + met.getSchId() + "'";
					statement = connection.createStatement();
					ResultSet moreResultSet = statement.executeQuery(sql);
					while (moreResultSet.next()) {
						String startTime = moreResultSet.getString("sch_start_time");
						String endTime = moreResultSet.getString("sch_end_time");
						met.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(startTime));
						met.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(endTime));
					}
					
					// Only add those meetings that have not yet happened
					if (now.before(met.getStartTime()))
						metList.add(met);
				}
			}

			int[] timeslot = new int[168];
			for (idx=0;idx<168;++idx) {
				timeslot[idx] = 0;
			}
		
			for (idx=0;idx<metList.size();++idx) {
				long shift = metList.get(idx).getStartTime().getTime() - now.getTime();
				long shift_sec = shift / 1000;
				long shift_hour = shift_sec / 3600 - 1;	// to include element 0
				timeslot[(int)shift_hour] = 1;
			}
			
			for (idx=0;idx<168;++idx) {
				if (timeslot[idx] == 0) {
					long shift_hour = idx + 1;
					long shift_sec = shift_hour * 3600;
					long shift = shift_sec * 1000;
					long date_millisec = shift + now.getTime();
					Date date = new Date(date_millisec);
					Date availableDate = new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), 0);
					dateList.add(availableDate);
				}
			}
			
			return dateList;
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return null;
		} catch (ParseException e) {
			return null;
		}
		
	}
}
