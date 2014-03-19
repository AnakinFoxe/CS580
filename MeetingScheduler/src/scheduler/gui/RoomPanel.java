package scheduler.gui;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;

import java.awt.CardLayout;

import javax.swing.JLabel;

import scheduler.controller.Controller;
import scheduler.model.DateModel;
import scheduler.model.Employee;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;

public class RoomPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JLabel roomLabel;
	private JButton finishBtn;
	private JPanel controller;
	private CardLayout cardlayout;
	private EmployeeListModel attendeeList;
	private DateModel selectedDate;
	private Box roomBox;
	private List<Room> availableRooms;
	private ButtonGroup roomGroup;
	private List<Employee> attendees;
	private EmployeeModel user;
	
	public RoomPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		roomBox = Box.createVerticalBox();
		scrollPane = new JScrollPane(roomBox);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 68, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 41, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 194, SpringLayout.WEST, this);
		add(scrollPane);
		
		roomLabel = new JLabel("Choose a Room");
		springLayout.putConstraint(SpringLayout.NORTH, roomLabel, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, roomLabel, 199, SpringLayout.WEST, this);
		add(roomLabel);
		
		finishBtn = new JButton("finish");
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, finishBtn);
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				boolean roomSelected = getRoom();
				if(roomSelected){
					cardlayout.show(controller,"home");
				}
            	
			}

		});
		springLayout.putConstraint(SpringLayout.NORTH, finishBtn, -58, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, finishBtn, -136, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, finishBtn, -26, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, finishBtn, -26, SpringLayout.EAST, this);
		add(finishBtn);
	}
	
	protected boolean getRoom() {
		// TODO Auto-generated method stub
		boolean isSelected = false;
		for(int i = 0; i < roomBox.getComponentCount(); i++){
			JRadioButton rdbtn = (JRadioButton) roomBox.getComponent(i);
			if(rdbtn.isSelected()){
//				Room selectedRoom = availableRooms.get(i);
//				List<Employee> attendees = attendeeList.getList();
//				Date selectedDate = this.selectedDate.getDate();
				Controller.insertMeeting(user.getEmployee(),
										attendeeList.getList(),
										this.selectedDate.getDate(),
										availableRooms.get(i));
				isSelected = true;
			}
		}
		return isSelected;
	}

	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	
	public void setModel(EmployeeListModel employeeList) {
		// TODO Auto-generated method stub
		this.attendeeList = employeeList;
		/*attendeeList.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if(EmployeeListModel.modelName.equals(evt.getPropertyName())){
					//add method for rooms
				}
			}
		});*/
	}
	
	public void setModel(EmployeeModel employee){
		this.user = employee;
	}
	public void setModel(DateModel dateModel){
		this.selectedDate = dateModel;
		selectedDate.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(DateModel.modelName.equals(arg0.getPropertyName())){
					//add method for date
					Date selected = selectedDate.getDate();
					attendees = attendeeList.getList();
					roomBox.removeAll();
					roomGroup = new ButtonGroup();
					scrollPane.setFocusable(true);
					availableRooms = Controller.genRoomList(selected, attendees.size());
					for(int i = 0; i < availableRooms.size(); i++){
						JRadioButton rdbtn = new JRadioButton(availableRooms.get(i).getName().toString()); 
						roomBox.add(rdbtn);
						roomGroup.add(rdbtn);
					}
					scrollPane.repaint();
				}
			}
		});
	}
}
