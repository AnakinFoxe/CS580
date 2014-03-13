package scheduler.model;

public class User {
	
	protected Integer usr_id;
	protected String username;
	
	public User() {
		
	}
	
	public Integer getUsrId() {
		return this.usr_id;
	}
	
	public void setUsrId(Integer usr_id) {
		this.usr_id = usr_id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

}
