package scheduler.model;

import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

public class Flag {
	private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
	public static final String modelName = "FLAG";
	boolean f;
	
	public Flag(boolean b){
		f = b;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.removePropertyChangeListener(listener);
	}
	
	public void setFlag(boolean s){
		boolean y  = f;
		f = s;
		pcSupport.firePropertyChange(modelName, y, f);
	}
	
	public boolean getFlag(){
		return f;
	}
}
