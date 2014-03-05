package scheduler.gui;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;

public class MeetingDetailsPanel extends JPanel {
	private JLabel lblRoom;
	private JLabel lblAttendees;
	private JList list;
	private JScrollBar scrollBar;
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
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Adam", "Bob", "Carl", "Danny", "Eric", "Frank", "Geoff"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, list, 32, SpringLayout.SOUTH, lblAttendees);
		springLayout.putConstraint(SpringLayout.WEST, list, 6, SpringLayout.EAST, scrollBar);
		
		scrollPane = new JScrollPane(list);
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
		
		timeDetails = new JLabel("01/27/2014 5:00PM");
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
		
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
}
