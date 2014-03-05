package scheduler.model;

public class Administrator extends User {
	
	private String description;
	
	public Administrator() {
		
	}

	public Administrator(String description) {
		
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
