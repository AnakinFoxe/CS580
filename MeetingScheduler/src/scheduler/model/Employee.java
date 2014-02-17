package scheduler.model;

public class Employee extends User {
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	
	
	public Employee() {
		
	}
	
	public Employee(String firstName, 
			String middleName, 
			String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return this.username;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
