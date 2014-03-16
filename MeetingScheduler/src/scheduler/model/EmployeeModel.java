package scheduler.model;
//this is a model class
import java.beans.PropertyChangeListener;
import javax.swing.event.SwingPropertyChangeSupport;

public class EmployeeModel {
	
	private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
	public static final String modelName = "EMPLOYEE";
	private Employee employee;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.removePropertyChangeListener(listener);
	}
	
	public void setEmployee(Employee e){
		Employee oldEmployee = this.employee;
		this.employee = new Employee();
		if (e.getUsrId() != null)
			employee.setUsrId(e.getUsrId());
		employee.setFirstName (e.getFirstName());
		employee.setLastName(e.getLastName());
		employee.setMiddleName(e.getMiddleName());
		employee.setPosition(e.getPosition());
		employee.setEmail(e.getEmail());
		Employee newEmployee = this.employee;
		pcSupport.firePropertyChange(modelName, oldEmployee, newEmployee);
	}
	
	public Employee getEmployee(){
		return employee;
	}
}
