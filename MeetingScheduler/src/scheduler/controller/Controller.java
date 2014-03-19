package scheduler.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
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
import scheduler.model.Room;
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
		if (connection == null)
			return null;
		
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
												 resultSet.getString("emp_last_name"),
												 resultSet.getString("emp_position"),
												 resultSet.getString("emp_email"));
				employee.setUsrId(usr_id);
				
				return employee;
			}
			
			// then check Administrator table
			sql = "select * from administrator where adm_usr_id='" + usr_id + "'";
			
			//statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Administrator admin = new Administrator(resultSet.getString("adm_description"));
				
				admin.setUsrId(usr_id);
				
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
	
	public static List<Date> getMeetingDate(Employee emp) {
		if (connection == null)
			return null;
		
		if (emp == null)
			return null;
		
		try {
			sql = "select * from attendee where att_emp_id=" + emp.getUsrId().toString();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			List<Date> dates = new ArrayList<Date>();
			while (resultSet.next()) {
				Integer sch_id = resultSet.getInt("att_sch_id");
				
				sql = "select * from schedule where sch_id=" + sch_id.toString();
				statement = connection.createStatement();
				ResultSet moreResultSet = statement.executeQuery(sql);
				while (moreResultSet.next()) {
					dates.add(moreResultSet.getDate("sch_start_time"));
				}
			}
			
			if (dates.size() > 0)
				return dates;
			else
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
	
	public static Meeting getMeetingDetail(Integer sch_id) {
		if (connection == null)
			return null;
		
		if (sch_id == 0)
			return null;
		
		try {
			Meeting met = new Meeting();
			List<Employee> attendee = new ArrayList<Employee>();
			
			// Find the Meeting
			sql = "select * from meeting where met_sch_id='" + sch_id + "'";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);	
			
			// Fill info from Meeting Table
			if (resultSet.next()) {
				met.setRoom_id(resultSet.getInt("met_rom_id"));
				met.setEmp_id(resultSet.getInt("met_emp_id"));
				met.setMeetingDescription(resultSet.getString("met_description"));
				met.setSchId(sch_id);
			} else
				return null;
			
			// Fill date from Schedule Table
			sql = "select * from schedule where sch_id='" + sch_id + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				String startTime = resultSet.getString("sch_start_time");
				String endTime = resultSet.getString("sch_end_time");
				met.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(startTime));
				met.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(endTime));
			} else
				return null;
			
			// Fill attendee from Attendee Table
			sql = "select * from attendee where att_sch_id='" + sch_id + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Integer usr_id = resultSet.getInt("att_emp_id");
				
				sql = "select * from employee where emp_usr_id='" + usr_id + "'";
				statement = connection.createStatement();
				ResultSet moreResultSet = statement.executeQuery(sql);
				if (moreResultSet.next()) {
					Employee emp = new Employee(moreResultSet.getString("emp_first_name"),
												moreResultSet.getString("emp_middle_name"),
												moreResultSet.getString("emp_last_name"),
												moreResultSet.getString("emp_position"),
												moreResultSet.getString("emp_email"));
					emp.setUsrId(usr_id);
					emp.setTitle(moreResultSet.getString("emp_title"));
					
					attendee.add(emp);
				} else {
					System.out.println("Employee mismatching");  
					return null;
				}
			}
			
			met.setAttendee(attendee);
			
			return met;
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return null;
		} catch (ParseException e) {
			System.out.println("Parse Time Error");  
			return null;
		}
	}
	
	public static List<Employee> genEmployeeList(Employee host) {
		if (Controller.connection == null)
			return null;
		
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			sql = "select * from employee";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Employee emp = new Employee(resultSet.getString("emp_first_name"),
						 					resultSet.getString("emp_middle_name"),
						 					resultSet.getString("emp_last_name"),
						 					resultSet.getInt("emp_usr_id"));
				
				// For the case we want to exclude the host employee
				if ((host != null) && (host.getUsrId() == emp.getUsrId())) 
					continue;
				
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
	
	
	public static List<Room> genRoomList(Date date, Integer empNum) {
		if (Controller.connection == null)
			return null;
		
		if (date == null || empNum <= 0)
			return null;
		
		List<Room> romList = new ArrayList<Room>();
		
		try {
			sql = "select * from room";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Room rom = new Room(resultSet.getInt("rom_id"),
						 			resultSet.getString("rom_name"),
						 			resultSet.getInt("rom_capacity"));
				romList.add(rom);
			}
			
			// Get Available Rooms for valid input
			// Otherwise, just return all the rooms
			if ((date != null) && (empNum != 0)) {
				// Generate the list of rooms that are occupied at the date
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				String strDate = df.format(date);
				
				sql = "select * from schedule where sch_start_time='" + strDate + "'";
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				
				List<Integer> rmRomIdList = new ArrayList<Integer>();
				while (resultSet.next()) {
					Integer sch_id = resultSet.getInt("sch_id");
					sql = "select met_rom_id from meeting where met_sch_id='" + sch_id + "'";
					statement = connection.createStatement();
					ResultSet moreResultSet = statement.executeQuery(sql);
					while (moreResultSet.next()) {
						rmRomIdList.add(moreResultSet.getInt("met_rom_id"));
					}
				}
				
				// Remove all the rooms on the list
				int i=0;
				int j=0;
				for (j=0;j<romList.size();++j) {
					if (romList.get(j).getCapacity() < empNum) {
						romList.remove(j);
						--j;
					}
					for (i=0;i<rmRomIdList.size();++i) {
						if (rmRomIdList.get(i) == romList.get(j).getId()) {
							romList.remove(j);	
							rmRomIdList.remove(i);
							--i;
							--j;
						}
					}
				}
			}		
			
			return romList;
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
						//met.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(endTime));
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
				
				if (shift_hour >= 168)
					return null;
				
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
			System.out.println("Parse Time Error");  
			return null;
		}
		
	}
	
	public static List<Meeting> genMeetingList(Date date, Integer usr_id) {
		if (Controller.connection == null)
			return null;
		
		if (date == null || usr_id == null)
			return null;
		
		
		List<Meeting> metList = new ArrayList<Meeting>();
		
		try {
			sql = "select * from attendee where att_emp_id='" + usr_id + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Integer sch_id = resultSet.getInt("att_sch_id");
				Meeting met = new Meeting();
				
				// Only provide start/end time, description
				sql = "select * from schedule where sch_id='" + sch_id + "'";
				statement = connection.createStatement();
				ResultSet moreResultSet = statement.executeQuery(sql);
				if (moreResultSet.next()) {
					String startTime = moreResultSet.getString("sch_start_time");
					String endTime = moreResultSet.getString("sch_end_time");
					met.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH).parse(startTime));
					met.setSchId(sch_id);
				} else
					return null;
				
				sql = "select * from meeting where met_sch_id='" + sch_id + "'";
				statement = connection.createStatement();
				moreResultSet = statement.executeQuery(sql);
				if (moreResultSet.next()) {
					met.setMeetingDescription(moreResultSet.getString("met_description"));
				} else
					return null;
				
				metList.add(met);
			}

			return metList;
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return null;
		} catch (ParseException e) {
			System.out.println("Parse Time Error");  
			return null;
		}
	}
	
	
	public static boolean insertMeeting(Employee host, List<Employee> empList, Date date, Room rom) {
		if (host == null || empList == null || date == null || rom == null) 
			return false;
		
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String startDate = df.format(date);
			
			// Insert Schedule Table
			sql = "insert into schedule (`sch_start_time`) values ('" + startDate + "')";
			statement = connection.createStatement();
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			resultSet = statement.getGeneratedKeys();
			Integer sch_id = -1;
			if (resultSet.next()) 
				sch_id = resultSet.getInt(1);
			else
				return false;

			// Insert Meeting Table
			sql = "insert into meeting (`met_sch_id`, `met_rom_id`, `met_emp_id`) values (" 
					+ sch_id.toString() + ", " 
					+ rom.getId().toString() + ", "
					+ host.getUsrId().toString() + ")";	
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			// Insert Attendee Table
			int idx;
			for (idx=0;idx<empList.size();++idx){
				sql = "insert into attendee (`att_sch_id`, `att_emp_id`) values ("
						+ sch_id.toString() + ", "
						+ empList.get(idx).getUsrId().toString() + ")";
				statement = connection.createStatement();
				statement.executeUpdate(sql);
			}
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		
		return true;
	}
	
	
	public static boolean insertEmployee(Employee emp, String pwd) {
		if ((emp == null) || (pwd == null))
			return false;
		
		try {
			// Check existence 
			sql = "select * from user where usr_username='" + emp.getUsername() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next())
				return false;
			
			// Insert User
			sql = "insert into user (`usr_username`, `usr_password`) values ('" 
					+ emp.getUsername() + "', '"
					+ pwd + "')";
			statement = connection.createStatement();
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			resultSet = statement.getGeneratedKeys();
			Integer usr_id = -1;
			if (resultSet.next()) 
				usr_id = resultSet.getInt(1);
			else
				return false;
			
			// Insert Employee
			sql = "insert into employee (`emp_usr_id`, `emp_first_name`, `emp_middle_name`,"
					+ "`emp_last_name`, `emp_title`, `emp_position`, `emp_email`) values (" 
					+ usr_id + ", '"
					+ emp.getFirstName() + "','"
					+ emp.getMiddleName() + "','"
					+ emp.getLastName() + "','"
					+ emp.getTitle() + "','"
					+ emp.getPosition() + "','"
					+ emp.getEmail() + "')";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public static boolean insertRoom(Room rom) {
		if (rom == null) 
			return false;
		
		try {
			// Check existence 
			sql = "select * from room where rom_name='" + rom.getName() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next())
				return false;
			
			// Insert room
			sql = "insert into room (`rom_name`, `rom_capacity`) values ('" 
					+ rom.getName() + "', "
					+ rom.getCapacity() + ")";
			
			statement = connection.createStatement();
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			resultSet = statement.getGeneratedKeys();
			Integer rom_id = -1;
			if (resultSet.next()) 
				rom_id = resultSet.getInt(1);
			else
				return false;
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public static boolean updateMeeting(Meeting met, List<Employee> empList, Date date, Room rom) {
		if (met == null || empList == null || date == null || rom == null) 
			return false;
		
		try {
			sql = "select * from schedule where sch_id='" + met.getSchId().toString() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			Integer sch_id = -1;
			if (resultSet.next()) {
				sch_id = resultSet.getInt("sch_id");
				
			} else
				return false;
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String startDate = df.format(date);
			
			// Update Schedule Table
			sql = "update schedule set "
					+ "`sch_start_time`='" + startDate 
					+ "' where sch_id=" + sch_id.toString();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			// Update Meeting Table
			sql = "update meeting set "
					+ "`met_rom_id`=" + rom.getId().toString()  
					+ " where `met_sch_id`=" + sch_id.toString();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			// Update Attendee Table
			sql = "select * from attendee where att_sch_id='" + sch_id.toString() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			int idx;
			while (resultSet.next()) {				
				Integer att_emp_id = resultSet.getInt("att_emp_id");
				Integer listSize = empList.size();
				
				// Remove employees that already exist
				for (idx=0;idx<empList.size();++idx) {
					if (att_emp_id == empList.get(idx).getUsrId()) {
						empList.remove(idx);
						break;	// Xing: not sure if this break is right
					}
				}
				
				// Remove employees that not attending anymore
				if (idx >= listSize) {
					sql = "delete from attendee where att_emp_id='" + att_emp_id.toString() + "'";
					statement = connection.createStatement();
					statement.executeUpdate(sql);
				}
			}
			for (idx=0;idx<empList.size();++idx){	// Add employees that are new
				sql = "insert into attendee (`att_sch_id`, `att_emp_id`) values ("
						+ sch_id.toString() + ", "
						+ empList.get(idx).getUsrId().toString() + ")";
				statement = connection.createStatement();
				statement.executeUpdate(sql);
			}	
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		
		return true;
	}
	
	
	static String validateData(String username, String oldPassword, String newPassword, String newPasswordConfirmed,
			   String email){
		String errorMessage="";
		if(!username.equals("")&&username.length()<=3)
			errorMessage = errorMessage.concat("Username length must be greater than 3\r\n");
		if(oldPassword.equals(""))
			errorMessage = errorMessage.concat("You must enter your old password\r\n");
		if(!newPassword.equals("")&&!newPassword.equals(newPasswordConfirmed))
			errorMessage = errorMessage.concat("Your new passwords must match\r\n");
		if(!email.equals("")&&!email.matches("[A-Za-z0-9._%+-][A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3}"))
			errorMessage = errorMessage.concat("Enter valid Email address");

		return errorMessage;
	}

	public static String updateProfile(Employee employee, String newUsername, String oldPassword, String newPassword, String newPasswordConfirmed,
					String newFirstname, String newMiddlename, String newLastname, String newTitle, String newPosition, String newEmail){
		
		Integer id = employee.getUsrId();

		if (employee.getUsrId()==0)
			return "Invalid User";

		String errorMessage = validateData(newUsername, oldPassword, newPassword, newPasswordConfirmed, newEmail);

		if (!errorMessage.equals(""))
			return errorMessage;

		try{
			 
			String currentPassword="";
			sql = "select * from user where usr_id='" + id + "'";

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				currentPassword=resultSet.getString("usr_password");
			}			

			if(!currentPassword.equals(oldPassword))
				return "Invalid Old Password";

			//update user table
			if(!newUsername.equals("")&&!newPassword.equals("")){
				sql = "UPDATE user SET usr_username='"+newUsername+"', usr_password='"+newPassword+"' WHERE  usr_id='" + id + "'";
				statement.executeUpdate(sql);
			}
			else if(!newUsername.equals("")){
				sql = "UPDATE user SET usr_username='"+newUsername+"' usr_id='" + id + "'";
				statement.executeUpdate(sql);
			}
			else if (!newPassword.equals("")){
				sql = "UPDATE user SET usr_password='"+newPassword+"' WHERE  usr_id='" + id + "'";
				statement.executeUpdate(sql);
			}
			//	Update employee table
			if(!newFirstname.equals("")){
				sql = "UPDATE employee SET emp_first_name='"+newFirstname+"' WHERE  emp_usr_id='" + id + "'";
				statement.executeUpdate(sql);
				employee.setFirstName(newFirstname);
			}
			if(!newMiddlename.equals("")){
				sql = "UPDATE employee SET emp_middle_name='"+newMiddlename+"' WHERE  emp_usr_id='" + id + "'";
				statement.executeUpdate(sql);
				employee.setMiddleName(newMiddlename);
			}
			if(!newLastname.equals("")){
				sql = "UPDATE employee SET emp_last_name='"+newLastname+"' WHERE  emp_usr_id='" + id + "'";
				statement.executeUpdate(sql);
				employee.setLastName(newLastname);
			}
			if(!newTitle.equals("")){
				sql = "UPDATE employee SET emp_title='"+newLastname+"' WHERE  emp_usr_id='" + id + "'";
				statement.executeUpdate(sql);
				employee.setTitle(newTitle);
			}
			if(!newPosition.equals("")){
				sql = "UPDATE employee SET emp_position='"+newPosition+"' WHERE  emp_usr_id='" + id + "'";
				statement.executeUpdate(sql);
				employee.setPosition(newPosition);
			}
			if(!newEmail.equals("")){
				sql = "UPDATE employee SET emp_email='"+newEmail+"' WHERE  emp_usr_id='" + id + "'";
				statement.executeUpdate(sql);
				employee.setEmail(newEmail);
			}			
			return "";
		}catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
			System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
		}
		return"ERROR!! Check your information";
	}
		
	public static boolean updateRoom(Room rom) {
		if (rom == null) 
			return false;
		
		try {
			// Check existence 
			sql = "select * from room where rom_name='" + rom.getName() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			Integer rom_id = -1;
			if (resultSet.next())
				rom_id = resultSet.getInt("rom_id");
			else
				return false;
				
			// Update room
			sql = "update room set "
					+ "`rom_name`='" + rom.getName() + "', "
					+ "`rom_capacity`=" + rom.getCapacity().toString()
					+ " where rom_id='" + rom_id.toString() + "'";
			
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	
	public static boolean deleteMeeting(Meeting met) {
		if (met == null) 
			return false;
		
		try {
			sql = "select * from schedule where sch_id='" + met.getSchId().toString() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			Integer sch_id = -1;
			if (resultSet.next()) {
				sch_id = resultSet.getInt("sch_id");
				
			} else
				return false;
			
			// Delete Attendee Table
			sql = "delete * from attendee where att_sch_id='" + sch_id.toString() + "'";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
	
			// Delete Meeting Table
			sql = "delete * from meeting where met_sch_id='" + sch_id.toString() + "'";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			// Delete Schedule Table
			sql = "delete * from schedule where sch_id='" + sch_id.toString() + "'";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		
		return true;
	}
	
	public static boolean deleteEmployee(Employee emp) {
		if (emp == null)
			return false;
		
		try {
			// Check existence 
			sql = "select * from user where usr_username='" + emp.getUsername() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			Integer usr_id = -1;
			if (resultSet.next())
				usr_id = resultSet.getInt("usr_id");
			else
				return false;
			
			// Delete Attendee records
			sql = "delete from attendee where att_emp_id='" + usr_id.toString() + "'";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			// Delete Meeting records and related attendee, schedule records
			sql = "select * from meeting where met_emp_id=" + usr_id.toString();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Integer sch_id = resultSet.getInt("met_sch_id");
				
				// Delete related Attendee
				sql = "delete from attendee where att_sch_id=" + sch_id.toString();
				statement = connection.createStatement();
				statement.executeUpdate(sql);
				
				// Delete Meeting
				sql = "delete from meeting where met_sch_id=" + sch_id.toString();
				statement = connection.createStatement();
				statement.executeUpdate(sql);
				
				// Delete Schedule
				sql = "delete from schedule where sch_id=" + sch_id.toString();
				statement = connection.createStatement();
				statement.executeUpdate(sql);
			}
			
			// Delete Employee record
			sql = "delete from employee where emp_usr_id=" + usr_id.toString();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			// Delete User record
			sql = "delete from user where usr_id=" + usr_id.toString();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	
	public static boolean deleteRoom(Room rom) {
		if (rom == null)
			return false;
		
		try {
			// Check existence 
			sql = "select * from rom where rom_name='" + rom.getName() + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			Integer rom_id = -1;
			if (resultSet.next())
				rom_id = resultSet.getInt("rom_id");
			else
				return false;
			
			// Delete Meeting records and related attendee, schedule records
			sql = "select * from meeting where met_rom_id=" + rom_id.toString();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Integer sch_id = resultSet.getInt("met_sch_id");
				
				// Delete related Attendee
				sql = "delete from attendee where att_sch_id=" + sch_id.toString();
				statement = connection.createStatement();
				statement.executeUpdate(sql);
				
				// Delete Meeting
				sql = "delete from meeting where met_sch_id=" + sch_id.toString();
				statement = connection.createStatement();
				statement.executeUpdate(sql);
				
				// Delete Schedule
				sql = "delete from schedule where sch_id=" + sch_id.toString();
				statement = connection.createStatement();
				statement.executeUpdate(sql);
			}
			
			// Delete Room record
			sql = "delete from room where rom_id=" + rom_id.toString();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			Integer ec = e.getErrorCode();
			String msg = e.getMessage();  
			String state = e.getSQLState();
		    System.out.println("The problem is : "+ec+" : "+msg+" : "+state);  
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	

}
