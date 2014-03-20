package scheduler.gui;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

import scheduler.controller.Controller;
import scheduler.model.DateModel;
import scheduler.model.Employee;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;
import scheduler.model.MeetingModel;
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
	private Flag isHomeVisible;
	private Flag rPanelVisible;
	private Flag fromMeetingDet;
	private Room selectedRoom;
	private MeetingModel meet;


	public RoomPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		this.setBackground(new Color(255, 255, 255));

		roomBox = Box.createVerticalBox();
		scrollPane = new JScrollPane(roomBox);
		add(scrollPane);

		roomLabel = new JLabel("Last Step: Choose a Room");
		roomLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		add(roomLabel);

		finishBtn = new JButton("FINISH");
		finishBtn.setPreferredSize(new Dimension(121, 30));
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, finishBtn);
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
					getData();
				}
				boolean roomSelected = getRoom();
				if(roomSelected){
					isHomeVisible.setFlag(true);
					rPanelVisible.setFlag(false);
					cardlayout.show(controller,"home");
				}

			}

		});
		add(finishBtn);

		springLayout.putConstraint(SpringLayout.NORTH, roomLabel, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, roomLabel, 20, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.SOUTH, finishBtn, -40, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, finishBtn, -50, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.SOUTH, roomLabel);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, finishBtn);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 300, SpringLayout.WEST, this);
		
		
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
				if(fromMeetingDet.getFlag()){
					Controller.updateMeeting(meet.getMeeting(), 
							attendeeList.getList(), 
							this.selectedDate.getDate(), 
							availableRooms.get(i));
					fromMeetingDet.setFlag(false);
				}else{
					Controller.insertMeeting(user.getEmployee(),
							attendeeList.getList(),
							this.selectedDate.getDate(),
							availableRooms.get(i));
				}
				
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
	}

	public void setModel(Flag flag){
		this.isHomeVisible = flag;
	}

	public void setFlag(Flag roomPVisible) {
		this.rPanelVisible = roomPVisible;

		rPanelVisible.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				boolean isVisible = rPanelVisible.getFlag();
				if(isVisible){
					Date selected = selectedDate.getDate();
					attendees = attendeeList.getList();
					roomBox.removeAll();
					roomGroup = new ButtonGroup();
					scrollPane.setFocusable(true);
					availableRooms = Controller.genRoomList(selected, attendees.size());
					for(int i = 0; i < availableRooms.size(); i++){
						String roomDisplay = availableRooms.get(i).getName().toString()
											+ "  (size: " + availableRooms.get(i).getCapacity().toString()
											+ ")";
						JRadioButton rdbtn = new JRadioButton(roomDisplay); 
						rdbtn.setFont(new Font("Arial", Font.PLAIN, 14));
						if(selectedRoom != null && fromMeetingDet.getFlag()){
							rdbtn.setSelected(true);
						}
						roomBox.add(rdbtn);
						roomGroup.add(rdbtn);
					}
					scrollPane.repaint();
				}
			}
		});
	}

	public void setDFlag(Flag fromMeetingDet) {
		// TODO Auto-generated method stub
		this.fromMeetingDet = fromMeetingDet;
	}
	
	public void setModel(Room model) {
		// TODO Auto-generated method stub
		this.selectedRoom = model;
	}
	
	public void setModel(MeetingModel meeting){
		this.meet = meeting;
	}
}
