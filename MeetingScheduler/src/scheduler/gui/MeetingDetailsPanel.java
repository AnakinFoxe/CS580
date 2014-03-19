package scheduler.gui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import scheduler.controller.Controller;
import scheduler.model.DateModel;
import scheduler.model.Employee;
import scheduler.model.EmployeeModel;
import scheduler.model.Meeting;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;

public class MeetingDetailsPanel extends JPanel {
	private JLabel lblRoom;
	private JLabel lblAttendees;
	private JButton btnBack;
	private JButton btnCancel;
	private JButton btnModify;
	private JLabel hostDetails;
	private JLabel lblHost;
	private JLabel timeDetails;
	private JLabel lblTime;
	private JScrollPane scrollPane;
	private JLabel rooDetails;
	private JPanel controller;
	private CardLayout cardlayout;
	private DateModel dateModel;
	private JComboBox<String> comboBox;
	private Box attendeesBox;
	private EmployeeModel user;
	protected Date selectedDate;
	protected List<Meeting> meetingDate;
	private List<Employee> attendeeList;
	private JLabel lblDescript;
	private JTextPane descriptDetail;
	
	public MeetingDetailsPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblMeetingDetails = new JLabel("Meeting Details");
		springLayout.putConstraint(SpringLayout.NORTH, lblMeetingDetails, 13, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblMeetingDetails, 125, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblMeetingDetails, 55, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblMeetingDetails, -116, SpringLayout.EAST, this);
		lblMeetingDetails.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add(lblMeetingDetails);
		
		lblAttendees = new JLabel("Attendees:");
		springLayout.putConstraint(SpringLayout.WEST, lblAttendees, 24, SpringLayout.WEST, this);
		lblAttendees.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblAttendees);
		
		lblRoom = new JLabel("Room:");
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblRoom);
		
		rooDetails = new JLabel("RoomA");
		springLayout.putConstraint(SpringLayout.NORTH, rooDetails, 122, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblRoom, 0, SpringLayout.NORTH, rooDetails);
		springLayout.putConstraint(SpringLayout.EAST, rooDetails, -68, SpringLayout.EAST, this);
		rooDetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(rooDetails);
		
		attendeesBox = Box.createVerticalBox();
		
		scrollPane = new JScrollPane(attendeesBox);
		springLayout.putConstraint(SpringLayout.WEST, lblRoom, 23, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, rooDetails, 64, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblAttendees, -8, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -269, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 24, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -115, SpringLayout.SOUTH, this);
		add(scrollPane);
		
		lblTime = new JLabel("Time:");
		springLayout.putConstraint(SpringLayout.NORTH, lblTime, 6, SpringLayout.SOUTH, lblRoom);
		springLayout.putConstraint(SpringLayout.WEST, lblTime, 0, SpringLayout.WEST, lblRoom);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblTime);
		
		timeDetails = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, timeDetails, 0, SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, timeDetails, 0, SpringLayout.WEST, rooDetails);
		timeDetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(timeDetails);
		
		lblHost = new JLabel("Host:");
		springLayout.putConstraint(SpringLayout.NORTH, lblHost, 34, SpringLayout.NORTH, lblAttendees);
		springLayout.putConstraint(SpringLayout.WEST, lblHost, 0, SpringLayout.WEST, lblRoom);
		springLayout.putConstraint(SpringLayout.SOUTH, lblHost, -6, SpringLayout.NORTH, lblRoom);
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHost);
		
		hostDetails = new JLabel("Brian");
		springLayout.putConstraint(SpringLayout.NORTH, hostDetails, 46, SpringLayout.SOUTH, lblMeetingDetails);
		springLayout.putConstraint(SpringLayout.WEST, hostDetails, 0, SpringLayout.WEST, rooDetails);
		springLayout.putConstraint(SpringLayout.SOUTH, hostDetails, -6, SpringLayout.NORTH, rooDetails);
		hostDetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(hostDetails);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"meeting");
			}

		});
		add(btnModify);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"home");
			}

		});
		springLayout.putConstraint(SpringLayout.NORTH, btnModify, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnModify, -5, SpringLayout.WEST, btnCancel);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -35, SpringLayout.EAST, this);
		add(btnCancel);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"home");
			}

		});
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, btnModify);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -8, SpringLayout.WEST, btnModify);
		add(btnBack);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedTime = comboBox.getSelectedIndex();
				displayMeetingDetails(selectedTime);
			}
		});
		
		springLayout.putConstraint(SpringLayout.WEST, comboBox, -30, SpringLayout.WEST, btnCancel);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -12, SpringLayout.NORTH, rooDetails);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -2, SpringLayout.EAST, this);
		add(comboBox);
		
		lblDescript = new JLabel("Details:");
		springLayout.putConstraint(SpringLayout.WEST, lblDescript, 0, SpringLayout.WEST, lblRoom);
		springLayout.putConstraint(SpringLayout.SOUTH, lblDescript, 0, SpringLayout.SOUTH, scrollPane);
		add(lblDescript);
		
		descriptDetail = new JTextPane();
		descriptDetail.setBackground(UIManager.getColor("Panel.background"));
		descriptDetail.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, descriptDetail, 6, SpringLayout.SOUTH, lblDescript);
		springLayout.putConstraint(SpringLayout.WEST, descriptDetail, 0, SpringLayout.WEST, lblRoom);
		springLayout.putConstraint(SpringLayout.SOUTH, descriptDetail, -8, SpringLayout.NORTH, btnModify);
		springLayout.putConstraint(SpringLayout.EAST, descriptDetail, -35, SpringLayout.EAST, this);
		add(descriptDetail);
		
	}
	
	protected void getData() {
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}

	public void setModel(DateModel calenderDateModel) {
		this.dateModel = calenderDateModel;
		dateModel.addPropertyChangeListener(new PropertyChangeListener() {
			

			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				SimpleDateFormat dtFormat = new SimpleDateFormat("hh:mm");
				selectedDate = dateModel.getDate();
				//timeDetails.setText(dtFormat.format(selectedDate.getDate()));
				meetingDate = Controller.genMeetingList(selectedDate, user.getEmployee().getUsrId());
				String timeString;
				comboBox.removeAllItems();
				for(int i=0; i < meetingDate.size(); i++){
					Meeting meeting = meetingDate.get(i);
					timeString = dtFormat.format(meeting.getStartTime());
					comboBox.addItem(timeString);
				}
				displayMeetingDetails(0);
			}
		});
	}
	
	protected void displayMeetingDetails(int index) {
		// TODO Auto-generated method stub
		Meeting meeting = meetingDate.get(index);
		attendeeList = meeting.getAttendee();
		Employee employee;
		if(attendeeList != null){
			for (int j = 0; j < attendeeList.size(); j++) {
				employee = attendeeList.get(j);
				JLabel attendee = new JLabel(employee.getFirstName() + " " + employee.getLastName());
				attendeesBox.add(attendee);
			}
		}
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("hh:mm");
		String startTime = dtFormat.format(meeting.getStartTime());
		//String endTime = dtFormat.format(meeting.getEndTime());
		timeDetails.setText(startTime);
		//rooDetails.setText(meeting.getRoom_id());
		//hostDetails.setText(meeting.getHost());
		descriptDetail.setText(meeting.getMeetingDescription());
		
	}

	public void setModel(EmployeeModel model){
		this.user = model;
	}
}
