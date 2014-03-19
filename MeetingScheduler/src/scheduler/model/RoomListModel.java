package scheduler.model;

import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.event.SwingPropertyChangeSupport;

public class RoomListModel {
	private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
	public static final String modelName = "ROOMLIST";
	private List<Room> RoomList;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.removePropertyChangeListener(listener);
	}
	
	public void setRoomList(List<Room> RoomList) {
		List<Room>  oldList = this.RoomList;
		this.RoomList = RoomList;
		List<Room>  newList = this.RoomList; 
		pcSupport.firePropertyChange(modelName, oldList, newList);
	}
	
	public List<Room> getList(){
		return RoomList;
	}
}
