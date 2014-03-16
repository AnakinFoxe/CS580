package scheduler.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.SpringLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;

import scheduler.model.Employee;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;

import java.util.Date;

public class HomePanel extends JPanel {
	/**
	 * 
	 */
	
	// some comments 
	private static final long serialVersionUID = -5218352851092235305L;
	private JButton btnCreateMeeting;
	private JButton btnUpdateProfile;
	private JLabel lblUsername;
	private JCalendar calenderPanel;
	private CardLayout cardlayout;
	private JPanel controller;
	private EmployeeModel employee;
	private String name;
	private JLabel lblName;
	private JLabel lblMiddleName;
	private JLabel lblLastName;
	private JLabel lblPosition;
	private JLabel lblEmail;
	private Flag flag;
	
	public HomePanel() {
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		btnCreateMeeting = new JButton("Create Meeting");
		btnCreateMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				flag.setFlag(true);
            	cardlayout.show(controller,"meeting");
			}
		});
		add(btnCreateMeeting);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"profile");
			}
		});
		add(btnUpdateProfile);
		
		lblUsername = new JLabel("Name:");
		add(lblUsername);
		
		lblName = new JLabel();
		add(lblName);
		lblMiddleName = new JLabel();
		add(lblMiddleName);
			
		
		lblLastName = new JLabel();
		add(lblLastName);
		
		lblPosition = new JLabel();
		add(lblPosition);
		
		lblEmail = new JLabel();
		add(lblEmail);
		
		calenderPanel = new JCalendar();
		
		calenderPanel.setBackground(new Color(153, 204, 255));
		//calenderPanel.setDate(new Date());
		add(calenderPanel);
		calenderPanel.setLayout(new SpringLayout());
		
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnCreateMeeting, -80, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnCreateMeeting, 50, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdateProfile, 10, SpringLayout.SOUTH, btnCreateMeeting);
		springLayout.putConstraint(SpringLayout.WEST, btnUpdateProfile, 0, SpringLayout.WEST, btnCreateMeeting);
		springLayout.putConstraint(SpringLayout.EAST, btnUpdateProfile, 0, SpringLayout.EAST, btnCreateMeeting);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 50, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.WEST, lblName, 6, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, 0, SpringLayout.SOUTH, lblUsername);
		
		springLayout.putConstraint(SpringLayout.WEST, lblMiddleName, 6, SpringLayout.EAST, lblName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblMiddleName, 0, SpringLayout.SOUTH, lblName);
				
		springLayout.putConstraint(SpringLayout.WEST, lblLastName, 6, SpringLayout.EAST, lblMiddleName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblLastName, 0, SpringLayout.SOUTH, lblName);
		
		springLayout.putConstraint(SpringLayout.WEST, lblPosition, 0, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.NORTH, lblPosition, 6, SpringLayout.SOUTH, lblName);
		
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 6, SpringLayout.SOUTH, lblPosition);
		
		springLayout.putConstraint(SpringLayout.NORTH, calenderPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, calenderPanel, -700, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, calenderPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, calenderPanel, 0, SpringLayout.EAST, this);
	}

	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	
	public void setModel(final EmployeeModel employee) {
		  this.employee = employee;
	      employee.addPropertyChangeListener(new PropertyChangeListener() {
	    	  
			public void propertyChange(PropertyChangeEvent evt) {
				
				if(EmployeeModel.modelName.equals(evt.getPropertyName())){
					name = employee.getEmployee().getFirstName();
					lblName.setText(name);
					lblMiddleName.setText(employee.getEmployee().getMiddleName());
					lblLastName.setText(employee.getEmployee().getLastName());
					lblPosition.setText(employee.getEmployee().getPosition());
					lblEmail.setText(employee.getEmployee().getEmail());
										
				}
				// TODO Auto-generated method stub
				
			}
	      });
	   }

	public void setModel(Flag flag1) {
		// TODO Auto-generated method stub
		flag = flag1;
	}
}
