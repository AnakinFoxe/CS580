package scheduler.model;

public class Employee extends User {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
	private String position;
	private String email;
	
	
	
	
	public Employee() {
		
	}
	
	public Employee(String firstName, 
					String middleName, 
					String lastName,
					String position,
					String email) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.position = position;
		this.email = email;
	}
	
	public Employee(String firstName, 
					String middleName, 
					String lastName,
					Integer usr_id) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.setUsrId(usr_id);
	}
	
	public String getUsername() {
		return this.username;
	}
	public Integer getUsrId() {
		return this.usr_id;
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosition() {
		return this.position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String lastName) {
		this.email = lastName;
	}
}
