package scheduler.model;

import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.event.SwingPropertyChangeSupport;

public class MeetingModel {
	private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
	public static final String modelName = "MEETING";
	private Meeting meeting;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.removePropertyChangeListener(listener);
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		Meeting oldMeeting = this.meeting;
		this.meeting = meeting;
		Meeting newMeeting = this.meeting;
		pcSupport.firePropertyChange(modelName, oldMeeting, newMeeting);
	}
}
