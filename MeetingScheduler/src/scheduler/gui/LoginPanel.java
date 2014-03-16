package scheduler.gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import scheduler.controller.Controller;
import scheduler.model.Administrator;
import scheduler.model.Employee;
import scheduler.model.EmployeeModel;
import scheduler.model.User;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class LoginPanel extends JPanel {
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 4932513626147661013L;
	private JButton btnLogin;
	private JButton btnAdmin;	// Xing: only for GUI demo
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblMeetingScheduler;
	private JTextField txfUsername;
	private JPasswordField psfPassword;
	private CardLayout cardlayout;
	private JPanel controller;
	private EmployeeModel employee;

	public LoginPanel() {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		lblMeetingScheduler = new JLabel();
		lblMeetingScheduler.setText("Meeting Scheduler");
		lblMeetingScheduler.setFont(new Font("Arial", Font.PLAIN, 48));
		add(lblMeetingScheduler);
		
		lblUsername = new JLabel();
		lblUsername.setText("Username");
		lblUsername.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblUsername);
		
		txfUsername = new JTextField();
		add(txfUsername);
		txfUsername.setColumns(10);
		
		lblPassword = new JLabel();
		lblPassword.setText("Password");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblPassword.setBackground(new Color(240, 240, 240));
		add(lblPassword);
		
		psfPassword = new JPasswordField();
		add(psfPassword);
		psfPassword.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}
            	String username = txfUsername.getText();
            	String password = new String(psfPassword.getPassword());

            	if (username != null) {
            		Controller.connect();
            		Integer usr_id = Controller.checkLogin(username, password);
            		
            		if (usr_id != 0) {
            			User usr = Controller.getUser(usr_id);
            			
            			if (usr instanceof Employee) {
            				employee.setEmployee((Employee) usr);
            				
            				
//            				List<Employee> attendee = new ArrayList<Employee>();
//                        	Employee xing = (Employee) usr;
//                        	xing.setUsrId(1);
//                        	attendee.add(xing);
//                        	Controller.genAvailableTime(attendee);
            				
            				cardlayout.show(controller,"home");
            			} else if (usr instanceof Administrator) {
            				cardlayout.show(controller,"adminHome");
            			} 
            			
            		} else {
            			txfUsername.setText("");
            			psfPassword.setText("");
            		}
            		
            	}
            	
            	
            }
            
		});
		add(btnLogin);
			

		btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}
            	
            	cardlayout.show(controller,"adminHome");
            }
            
		});
		add(btnAdmin);
		
		
		// Adjust alignment relationship for all the components
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txfUsername, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, txfUsername, 0, SpringLayout.VERTICAL_CENTER, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, psfPassword, 0, SpringLayout.SOUTH, txfUsername);
		springLayout.putConstraint(SpringLayout.WEST, psfPassword, 0, SpringLayout.WEST, txfUsername);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblUsername, 0, SpringLayout.BASELINE, txfUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, 0, SpringLayout.WEST, txfUsername);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblPassword, 0, SpringLayout.BASELINE, psfPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.WEST, psfPassword);
		
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, txfUsername);
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 0, SpringLayout.SOUTH, psfPassword);
		
		springLayout.putConstraint(SpringLayout.BASELINE, btnAdmin, 0, SpringLayout.BASELINE, btnLogin);
		springLayout.putConstraint(SpringLayout.WEST, btnAdmin, 0, SpringLayout.EAST, btnLogin);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblMeetingScheduler, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblMeetingScheduler, -50, SpringLayout.NORTH, txfUsername);

	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	
	 public void setModel(EmployeeModel simpleModel) {
	      this.employee = simpleModel;
	   }

}
