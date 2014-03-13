package scheduler.model;

import java.util.Date;

public class Meeting extends Schedule {
	private Integer room_id;
	private Integer emp_id;
	private String meetingDescription;
	
	public Meeting() {
		
	}
	
	public Meeting(Integer room_id, Integer emp_id, String meetingDescription) {
		super();
		this.room_id = room_id;
		this.emp_id = emp_id;
		this.meetingDescription = meetingDescription;
	}
	
	public Meeting(Integer sch_id, Integer emp_id) {
		super(sch_id);
		this.emp_id = emp_id;
	}
	

	public Meeting(Integer sch_id, Date startTime, Date endTime) {
		super(sch_id, startTime, endTime);
		// TODO Auto-generated constructor stub
	}

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getMeetingDescription() {
		return meetingDescription;
	}

	public void setMeetingDescription(String meetingDescription) {
		this.meetingDescription = meetingDescription;
	}

}
