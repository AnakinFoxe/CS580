package scheduler.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import scheduler.model.Employee;

public class EmployeeController extends Controller {
	private Employee employee = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public EmployeeController(Employee employee) {
		this.employee = employee;
	}
	
	public List<Employee> genEmployeeList() {
		if (Controller.connection == null)
			return null;
		
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			String sql = "select * from employee";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
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
