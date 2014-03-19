
package scheduler.model;

import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

public class RoomModel {
	private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
	public static final String modelName = "ROOM";
	private Room room;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.removePropertyChangeListener(listener);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		Room oldRoom = this.room;
		this.room = room;
		Room newRoom = this.room;
		pcSupport.firePropertyChange(modelName, oldRoom, newRoom);
	}
	
	
}

