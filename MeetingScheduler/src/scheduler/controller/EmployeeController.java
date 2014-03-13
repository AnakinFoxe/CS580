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
	
	
}
