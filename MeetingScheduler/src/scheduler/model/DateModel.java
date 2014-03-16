package scheduler.model;

import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.event.SwingPropertyChangeSupport;

public class DateModel {
	private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
	public static final String modelName = "DATE";
	private Date date;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.removePropertyChangeListener(listener);
	}

	public void set(Date date) {
		// TODO Auto-generated method stub
		
		Date oldDate = this.date;
		this.date = date;
		Date newDate = this.date; 
		pcSupport.firePropertyChange(modelName, oldDate, newDate);
	}
	
	public Date getDate(){
		return date;
	}
}
