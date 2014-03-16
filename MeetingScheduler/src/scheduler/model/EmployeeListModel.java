package scheduler.model;

import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.event.SwingPropertyChangeSupport;

public class EmployeeListModel {
	private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
	public static final String modelName = "EMPLOYEELIST";
	private List<Employee> employeeList;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.removePropertyChangeListener(listener);
	}
	
	public void setEmployeeList(List<Employee> employeeList) {
		List<Employee>  oldList = this.employeeList;
		this.employeeList = employeeList;
		List<Employee>  newList = this.employeeList; 
		pcSupport.firePropertyChange(modelName, oldList, newList);
	}
	
	public List<Employee> getList(){
		return employeeList;
	}
}
