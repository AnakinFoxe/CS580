package scheduler.gui;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class MeetingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3159213351500534829L;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnEmployee_2;
	private JRadioButton rdbtnEmployee_1;
	private JRadioButton rdbtnEmployee;
	private JLabel lblSelectAttendees;
	private JButton btnContinue;
	private CardLayout cardlayout;
	private JPanel controller;
	
	public MeetingPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		rdbtnNewRadioButton = new JRadioButton("employee1");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 34, SpringLayout.WEST, this);
		add(rdbtnNewRadioButton);
		
		rdbtnEmployee = new JRadioButton("employee2");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmployee, 8, SpringLayout.SOUTH, rdbtnNewRadioButton);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnEmployee, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		add(rdbtnEmployee);
		
		rdbtnEmployee_1 = new JRadioButton("employee3");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmployee_1, 6, SpringLayout.SOUTH, rdbtnEmployee);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnEmployee_1, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		add(rdbtnEmployee_1);
		
		rdbtnEmployee_2 = new JRadioButton("employee4");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmployee_2, 6, SpringLayout.SOUTH, rdbtnEmployee_1);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnEmployee_2, 0, SpringLayout.EAST, rdbtnNewRadioButton);
		add(rdbtnEmployee_2);
		
		lblSelectAttendees = new JLabel("Select Attendees");
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectAttendees, 28, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectAttendees, 175, SpringLayout.WEST, this);
		add(lblSelectAttendees);
		
		btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"time");
			}

		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -44, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, -65, SpringLayout.EAST, this);
		add(btnContinue);
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
}
