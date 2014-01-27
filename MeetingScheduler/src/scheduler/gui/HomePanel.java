package scheduler.gui;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.SpringLayout;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.util.Date;

public class HomePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5218352851092235305L;
	private Button createMeetingBtn;
	private Button updateProfileBtn;
	private JLabel lblUsername;
	private JCalendar calenderPanel;
	private CardLayout cardlayout;
	private JPanel controller;
	
	public HomePanel() {
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		createMeetingBtn = new Button("Create Meeting");
		springLayout.putConstraint(SpringLayout.NORTH, createMeetingBtn, 33, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, createMeetingBtn, 48, SpringLayout.WEST, this);
		createMeetingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"meeting");
			}
		});
		add(createMeetingBtn);
		
		updateProfileBtn = new Button("Update Profile");
		springLayout.putConstraint(SpringLayout.NORTH, updateProfileBtn, 70, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, updateProfileBtn, 48, SpringLayout.WEST, this);
//		updateProfileBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if (controller == null){
//            		getData();
//            	}
//            	cardlayout.show(controller,"meeting");
//			}
//		});
		add(updateProfileBtn);
		
		lblUsername = new JLabel("username:");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -213, SpringLayout.EAST, this);
		add(lblUsername);
		
		JLabel lblJoe = new JLabel("Joe");
		springLayout.putConstraint(SpringLayout.WEST, lblJoe, 6, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.SOUTH, lblJoe, 0, SpringLayout.SOUTH, lblUsername);
		add(lblJoe);
		
		calenderPanel = new JCalendar();
		springLayout.putConstraint(SpringLayout.NORTH, calenderPanel, 49, SpringLayout.SOUTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, calenderPanel, -446, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, calenderPanel, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, calenderPanel, -10, SpringLayout.EAST, this);
		calenderPanel.setBackground(new Color(153, 204, 255));
		//calenderPanel.setDate(new Date());
		add(calenderPanel);
		calenderPanel.setLayout(new SpringLayout());
	}

	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
}
