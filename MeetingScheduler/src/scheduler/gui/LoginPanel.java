package scheduler.gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPanel extends JPanel {
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 4932513626147661013L;
	private JTextField usernameTextField;
	private JButton btnLogin;
	private JLabel usernameLabel;
	private JPasswordField passwordTextField;
	private JLabel meetingSchedulerLabel;
	private JLabel passwordLabel;
	private CardLayout cardlayout;
	private JPanel controller;

	public LoginPanel() {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		usernameLabel = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 131, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, usernameLabel, 129, SpringLayout.WEST, this);
		usernameLabel.setText("username");
		add(usernameLabel);
		
		usernameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 0, SpringLayout.NORTH, usernameLabel);
		springLayout.putConstraint(SpringLayout.WEST, usernameTextField, 6, SpringLayout.EAST, usernameLabel);
		add(usernameTextField);
		usernameTextField.setColumns(10);
		
		btnLogin = new JButton("Login");
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, usernameTextField);
		btnLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"home");
            }
            
		});
		add(btnLogin);
		
		passwordTextField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 6, SpringLayout.SOUTH, passwordTextField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordTextField, 5, SpringLayout.SOUTH, usernameTextField);
		springLayout.putConstraint(SpringLayout.EAST, passwordTextField, 0, SpringLayout.EAST, usernameTextField);
		add(passwordTextField);
		passwordTextField.setColumns(10);
		
		passwordLabel = new JLabel();
		passwordLabel.setText("password");
		passwordLabel.setBackground(new Color(240, 240, 240));
		springLayout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, usernameLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordLabel, 0, SpringLayout.SOUTH, passwordTextField);
		add(passwordLabel);
		
		meetingSchedulerLabel = new JLabel();
		springLayout.putConstraint(SpringLayout.WEST, meetingSchedulerLabel, 167, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, meetingSchedulerLabel, -39, SpringLayout.NORTH, usernameLabel);
		meetingSchedulerLabel.setText("Meeting Scheduler");
		add(meetingSchedulerLabel);
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	

}
