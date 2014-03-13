package scheduler.model;

import java.util.Date;

public class Schedule {
	
	protected Integer sch_id;
	protected Date startTime;
	protected Date endTime;
	
	public Schedule() {
		
	}
	
	public Schedule(Integer sch_id) {
		super();
		this.sch_id = sch_id;
	}
	
	public Schedule(Integer sch_id, Date startTime, Date endTime) {
		super();
		this.sch_id = sch_id;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Integer getSchId() {
		return this.sch_id;
	}
	
	public void setSchId(Integer sch_id) {
		this.sch_id = sch_id;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
