package scheduler.gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import scheduler.controller.Controller;
import scheduler.model.DateModel;
import scheduler.model.Employee;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;
import scheduler.model.Meeting;
import scheduler.model.MeetingModel;
import scheduler.model.RoomModel;

import javax.swing.JComboBox;
import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JSplitPane;

import java.awt.Component;
import java.awt.Button;

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
	private EmployeeModel employeeModel;
	protected Date selectedDate;
	protected List<Meeting> meetingDate;
	private List<Employee> attendeeList;
	private List<String> acceptList;
	private JLabel lblDescript;
	private JTextPane descriptDetail;
	private EmployeeListModel attendeeListModel;
	private Flag meetingPVisible;
	private DateModel timeModel;
	private RoomModel roomModel;
	private Meeting meeting;
	private Flag fromMeetingDet;
	private MeetingModel meetingModel;
	private Flag homeVisible;
	private Flag meetingDetVisible;
	private Box acceptBox;
	private Box mainbox;
	private JOptionPane optionPane;
	private List<Date> need2Accept;
	private JButton btnReject;
	private JButton btnAccept;
	
	public MeetingDetailsPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		this.setBackground(new Color(255, 255, 255));
		
		JLabel lblMeetingDetails = new JLabel("Meeting Details");
		lblMeetingDetails.setFont(new Font("Arial", Font.PLAIN, 24));
		add(lblMeetingDetails);
		
//		lblAttendees = new JLabel("Attendees:");
//		lblAttendees.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		add(lblAttendees);
		
		lblRoom = new JLabel("Room:");
		lblRoom.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblRoom);
		
		rooDetails = new JLabel("RoomA");
		rooDetails.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(rooDetails);
		
		attendeesBox = Box.createVerticalBox();
		attendeesBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		attendeesBox.setAlignmentY(Component.TOP_ALIGNMENT);
		acceptBox = Box.createVerticalBox();
		acceptBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		acceptBox.setAlignmentY(Component.TOP_ALIGNMENT);
		mainbox = Box.createHorizontalBox();
		mainbox.add(attendeesBox);
		mainbox.add(acceptBox);
		JLabel accept = new JLabel("Accept");
		accept.setAlignmentX(Component.RIGHT_ALIGNMENT);
		acceptBox.add(accept);
		scrollPane = new JScrollPane(mainbox);
		JLabel accept1 = new JLabel("blah");
		attendeesBox.add(accept1);
		
		add(scrollPane);
		
		lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblTime);
		
		timeDetails = new JLabel();
		timeDetails.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(timeDetails);
		
		lblHost = new JLabel("Host:");
		lblHost.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblHost);
		
		hostDetails = new JLabel("Brian");
		hostDetails.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(hostDetails);
		
		btnModify = new JButton("Modify Meeting");
		btnModify.setPreferredSize(new Dimension(121, 30));
		
		optionPane = new JOptionPane(
			    "The only way to close this dialog is by\n"
			    + "pressing one of the following buttons.\n"
			    + "Do you understand?",
			    JOptionPane.QUESTION_MESSAGE,
			    JOptionPane.YES_NO_OPTION);
		optionPane.setVisible(false);
		
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				meetingPVisible.setFlag(true);
				attendeeListModel.setEmployeeList(attendeeList);
				timeModel.set(meeting.getStartTime());
				roomModel.setRoom(meeting.getRom());
				fromMeetingDet.setFlag(true);
				meetingModel.setMeeting(meeting);
				meetingDetVisible.setFlag(false);
            	cardlayout.show(controller,"meeting");
			}

		});
		add(btnModify);
		
		btnCancel = new JButton("Cancel Meeting");
		btnCancel.setPreferredSize(new Dimension(121, 30));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (controller == null){
            		getData();
            	}
				Object[] options = {"Confrm",
						"Cancel",};
				int isDeleted = JOptionPane.showOptionDialog(controller,
						"Are you sure you want to"
								+ "delete this meeting?",
								"Cancel meeting",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
				
				if (isDeleted == 0){
					Controller.deleteMeeting(meeting);
					homeVisible.setFlag(true);
					meetingDetVisible.setFlag(false);
	            	cardlayout.show(controller,"home");
				}
			}

		});
		add(btnCancel);
		
		btnBack = new JButton("Back");
		btnBack.setPreferredSize(new Dimension(121, 30));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				fromMeetingDet.setFlag(false);
				meetingDetVisible.setFlag(false);
				homeVisible.setFlag(true);
            	cardlayout.show(controller,"home");
			}

		});
		add(btnBack);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedTime = comboBox.getSelectedIndex();
				if(selectedTime >= 0){
					displayMeetingDetails(selectedTime);
				}
			}
		});
		add(comboBox);
		
		lblDescript = new JLabel("Description:");
		lblDescript.setFont(new Font("Arial", Font.PLAIN, 18));
		add(lblDescript);
		
		descriptDetail = new JTextPane();
		descriptDetail.setBackground(UIManager.getColor("Panel.background"));
		descriptDetail.setEditable(false);
		add(descriptDetail);
		
		btnAccept = new JButton("Accept");
		btnAccept.setPreferredSize(new Dimension(121, 30));
		btnAccept.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (controller == null){
            		getData();
            	}
				// TODO Auto-generated method stub
				Employee user = employeeModel.getEmployee();
				Controller.updateAcceptance(user.getUsrId(), meeting.getSchId(), "YES");
				btnAccept.setVisible(false);
				btnReject.setVisible(false);
				
				fromMeetingDet.setFlag(false);
				meetingDetVisible.setFlag(false);
				homeVisible.setFlag(true);
            	cardlayout.show(controller,"home");
			}
		});
		add(btnAccept);
		
		btnReject = new JButton("Reject");
		btnReject.setForeground(Color.RED); //setBackground(Color.RED);
		btnReject.setPreferredSize(new Dimension(121, 30));
		btnReject.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (controller == null){
            		getData();
            	}
				// TODO Auto-generated method stub
				Employee user = employeeModel.getEmployee();
				Controller.updateAcceptance(user.getUsrId(), meeting.getSchId(), "NO");
				btnAccept.setVisible(false);
				btnReject.setVisible(false);
				
				fromMeetingDet.setFlag(false);
				meetingDetVisible.setFlag(false);
				homeVisible.setFlag(true);
            	cardlayout.show(controller,"home");
			}
		});
		add(btnReject);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblMeetingDetails, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblMeetingDetails, 20, SpringLayout.WEST, this);
//		springLayout.putConstraint(SpringLayout.SOUTH, lblMeetingDetails, 55, SpringLayout.NORTH, this);
//		springLayout.putConstraint(SpringLayout.EAST, lblMeetingDetails, -116, SpringLayout.EAST, this);
		
//		springLayout.putConstraint(SpringLayout.WEST, lblAttendees, 24, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -40, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -50, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.NORTH, btnBack);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, btnBack);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnModify, -10, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnModify, 0, SpringLayout.EAST, btnCancel);
		
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 300, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.SOUTH, lblMeetingDetails);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, btnBack);
		
		springLayout.putConstraint(SpringLayout.WEST, lblDescript, 30, SpringLayout.EAST, scrollPane);
//		springLayout.putConstraint(SpringLayout.SOUTH, lblDescript, 0, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, lblDescript, -400, SpringLayout.SOUTH, scrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, descriptDetail, 10, SpringLayout.SOUTH, lblDescript);
		springLayout.putConstraint(SpringLayout.WEST, descriptDetail, 0, SpringLayout.WEST, lblDescript);
		springLayout.putConstraint(SpringLayout.SOUTH, descriptDetail, 0, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, descriptDetail, 330, SpringLayout.EAST, scrollPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblHost, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, lblHost, 30, SpringLayout.EAST, scrollPane);
//		springLayout.putConstraint(SpringLayout.SOUTH, lblHost, -6, SpringLayout.NORTH, lblRoom);
		
		springLayout.putConstraint(SpringLayout.NORTH, hostDetails, 0, SpringLayout.NORTH, lblHost);
		springLayout.putConstraint(SpringLayout.WEST, hostDetails, 20, SpringLayout.EAST, lblHost);
//		springLayout.putConstraint(SpringLayout.SOUTH, hostDetails, -6, SpringLayout.NORTH, rooDetails);
		
		springLayout.putConstraint(SpringLayout.WEST, lblRoom, 0, SpringLayout.WEST, lblHost);
		springLayout.putConstraint(SpringLayout.NORTH, lblRoom, 10, SpringLayout.SOUTH, lblHost);
		
		springLayout.putConstraint(SpringLayout.NORTH, rooDetails, 0, SpringLayout.NORTH, lblRoom);
		springLayout.putConstraint(SpringLayout.WEST, rooDetails, 0, SpringLayout.WEST, hostDetails);
//		springLayout.putConstraint(SpringLayout.WEST, rooDetails, 64, SpringLayout.EAST, scrollPane);
		
//		springLayout.putConstraint(SpringLayout.SOUTH, lblAttendees, -8, SpringLayout.NORTH, scrollPane);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, lblTime, 10, SpringLayout.SOUTH, lblRoom);
		springLayout.putConstraint(SpringLayout.WEST, lblTime, 0, SpringLayout.WEST, lblRoom);
		
		springLayout.putConstraint(SpringLayout.NORTH, timeDetails, 0, SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, timeDetails, 0, SpringLayout.WEST, rooDetails);
		

		springLayout.putConstraint(SpringLayout.WEST, comboBox, -200, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 0, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -50, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.EAST, btnReject, -30, SpringLayout.WEST, btnBack);
		springLayout.putConstraint(SpringLayout.SOUTH, btnReject, 0, SpringLayout.SOUTH, btnBack);
		
		springLayout.putConstraint(SpringLayout.EAST, btnAccept, 0, SpringLayout.EAST, btnReject);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAccept, -10, SpringLayout.NORTH, btnReject);
	
//		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.NORTH, btnBack);
//		springLayout.putConstraint(SpringLayout.WEST, acceptBox, -100, SpringLayout.EAST, attendeesBox);
	}
	
	protected void getData() {
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}

	public void setModel(DateModel calenderDateModel) {
		this.dateModel = calenderDateModel;
	}
	
	protected void displayMeetingDetails(int index) {
		// TODO Auto-generated method stub
		meeting = meetingDate.get(index);
		descriptDetail.setText(meeting.getMeetingDescription());
		meeting = Controller.getMeetingDetail(meeting.getSchId());
		attendeeList = meeting.getAttendee();
		acceptList = meeting.getAccept();
		Employee employee;
		attendeesBox.removeAll();
		acceptBox.removeAll();
		JLabel attendee = new JLabel("Attendees");
		attendee.setFont(new Font("Arial", Font.BOLD, 14));
		JLabel accept = new JLabel("          Status");
		accept.setFont(new Font("Arial", Font.BOLD, 14));
		attendeesBox.add(attendee);
		acceptBox.add(accept);
		if(attendeeList != null){
			for (int j = 0; j < attendeeList.size(); j++) {
				employee = attendeeList.get(j);
				String acceptStatus = acceptList.get(j);
				attendee = new JLabel(employee.getFirstName() + " " + employee.getLastName());
				attendee.setFont(new Font("Arial", Font.PLAIN, 14));
				attendeesBox.add(attendee);
				accept = new JLabel("NA");
				if (acceptStatus == null) 
					accept.setText("          Not Respond");
				else if (acceptStatus.equals("YES")) {
					accept.setText("          Accepted");
					accept.setForeground(Color.GREEN);
				}
				else if (acceptStatus.equals("NO")) {
					accept.setText("          Rejected");
					accept.setForeground(Color.RED);
				}
				accept.setFont(new Font("Arial", Font.PLAIN, 14));

				acceptBox.add(accept);
			}
		}
		
		if(need2Accept != null && need2Accept.contains(meeting.getStartTime())){
			btnAccept.setVisible(true);
			btnReject.setVisible(true);
		}else{
			btnAccept.setVisible(false);
			btnReject.setVisible(false);
		}
		SimpleDateFormat dtFormat = new SimpleDateFormat("hh:mm");
		String startTime = dtFormat.format(meeting.getStartTime());
		//String endTime = dtFormat.format(meeting.getEndTime());
		timeDetails.setText(startTime);
		rooDetails.setText(meeting.getRom().getName());
		hostDetails.setText(meeting.getHost().getFirstName());
		
		
	}

	public void setModel(EmployeeModel model){
		this.employeeModel = model;
	}
	
	public void setModel(EmployeeListModel model){
		this.attendeeListModel = model;
	}

	public void setModel(Flag flag1) {
		this.meetingPVisible = flag1;
	}

	public void setTimeModel(DateModel model) {
		this.timeModel = model;
		
	}

	public void setModel(RoomModel room) {
		this.roomModel = room;
	}

	public void setFlag(Flag fromMeetingDet) {
		// TODO Auto-generated method stub
		this.fromMeetingDet = fromMeetingDet;
	}
	
	public void setModel(MeetingModel model){
		this.meetingModel = model;
	}

	public void setHFlag(Flag homeVisible) {
		// TODO Auto-generated method stub
		this.homeVisible = homeVisible;
	}

	public void setMeetFlag(Flag flag) {
		// TODO Auto-generated method stub
		this.meetingDetVisible = flag;
		meetingDetVisible.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				Employee user = employeeModel.getEmployee();
				if(meetingDetVisible.getFlag()){
					SimpleDateFormat dtFormat = new SimpleDateFormat("hh:mm");
					selectedDate = dateModel.getDate();
					//timeDetails.setText(dtFormat.format(selectedDate.getDate()));
					meetingDate = Controller.genMeetingList(selectedDate, user.getUsrId());

					String timeString;
					comboBox.removeAllItems();
					for(int i=0; i < meetingDate.size(); i++){
						Meeting meeting = meetingDate.get(i);
						timeString = dtFormat.format(meeting.getStartTime());
						comboBox.addItem(timeString);
					}
					List<Meeting> meetUnAccept = Controller.checkAcceptance(user.getUsrId());
					need2Accept = new ArrayList<Date>();
					Meeting meet;
					Date date;
					for(int i = 0; i < meetUnAccept.size(); i++){
						 meet = meetUnAccept.get(i);
						 date = meet.getStartTime();
						need2Accept.add(date);
					}
					displayMeetingDetails(0);
					comboBox.setSelectedIndex(0);
				}
			}
		});
	}
}
