package scheduler.gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import scheduler.controller.Controller;
import scheduler.model.Employee;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;


public class AMEmpPanel extends JPanel {

	//private static final long serialVersionUID = 4932513626147661013L;
	private JLabel lblUsername;
	private JLabel lblNewPassword;
	private JLabel lblFirstname;
	private JLabel lblMiddlename;
	private JLabel lblLastname;
	private JLabel lblTitle;
	private JLabel lblPosition;
	private JLabel lblEmail;
	private JLabel lblHeading;
	
	private JTextField txfUsername;
	private JPasswordField psfNewPassword;
	private JTextField txfFirstname;
	private JTextField txfMiddlename;
	private JTextField txfLastname;
	private JTextField txfTitle;
	private JTextField txfPosition;
	private JTextField txfEmail;
	
	
	private String username;
	private String newPassword;
	private String firstname;
	private String lastname;
	private String middlename;
	private String title;
	private String position;
	private String email;
	
	
	private CardLayout cardlayout;
	private JPanel controller;
	
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnBack;
	
	private EmployeeListModel empListModel;
	
	
	public AMEmpPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		lblHeading = new JLabel();
		lblHeading.setText("Employee");
		lblHeading.setFont(new Font("Consolas", Font.PLAIN, 20));
		add(lblHeading);
		
		lblUsername = new JLabel();
		lblUsername.setText("Username");
		lblUsername.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblUsername);
		
		txfUsername = new JTextField();
		add(txfUsername);
		txfUsername.setColumns(10);
		
		lblNewPassword = new JLabel();
		lblNewPassword.setText("Password");
		lblNewPassword.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblNewPassword);
		
		psfNewPassword = new JPasswordField();
		add(psfNewPassword);
		psfNewPassword.setColumns(10);
						
		lblFirstname = new JLabel();
		lblFirstname.setText("First Name");
		lblFirstname.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblFirstname);
		
		txfFirstname = new JTextField();
		add(txfFirstname);
		txfFirstname.setColumns(10);
		
		lblMiddlename = new JLabel();
		lblMiddlename.setText("Middle Name");
		lblMiddlename.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblMiddlename);
		
		txfMiddlename = new JTextField();
		add(txfMiddlename);
		txfMiddlename.setColumns(10);
		
		
		lblLastname = new JLabel();
		lblLastname.setText("Last Name");
		lblLastname.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblLastname);
		
		txfLastname = new JTextField();
		add(txfLastname);
		txfLastname.setColumns(10);
		
		lblTitle = new JLabel();
		lblTitle.setText("Title");
		lblTitle.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblTitle);
		
		txfTitle = new JTextField();
		add(txfTitle);
		txfTitle.setColumns(10);
		
		lblPosition = new JLabel();
		lblPosition.setText("Position");
		lblPosition.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblPosition);
		
		txfPosition = new JTextField();
		add(txfPosition);
		txfPosition.setColumns(10);
		
		lblEmail = new JLabel();
		lblEmail.setText("Email");
		lblEmail.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblEmail);
		
		txfEmail = new JTextField();
		add(txfEmail);
		txfEmail.setColumns(10);		
		
		btnUpdate = new JButton("OK");
		btnUpdate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
           		getData();
            	}
            		
            	
            	username=txfUsername.getText().trim();
            	newPassword=psfNewPassword.getText().trim();
            	firstname=txfFirstname.getText().trim();
            	lastname=txfLastname.getText().trim();
            	middlename=txfMiddlename.getText().trim();
            	title=txfTitle.getText().trim();
            	position=txfPosition.getText().trim();
            	email=txfEmail.getText().trim();
            	Employee emp = new Employee(firstname, middlename, lastname, position, email);

				List <Employee> oldEmpList = new ArrayList<Employee>(empListModel.getList());
				oldEmpList.add(emp);
				
				empListModel.setEmployeeList(oldEmpList);
				Controller.insertEmployee(emp, newPassword);
				clearFields();
				cardlayout.show(controller, "adminHome");
  
            }           
		});
	
		add(btnUpdate);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}
            	clearFields();
            }
            
		});
		add(btnClear);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}
            	clearFields();
            	cardlayout.show(controller,"adminHome");
            }
            
		});
		add(btnBack);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeading, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblHeading, 50, SpringLayout.NORTH, this);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txfUsername, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.NORTH, txfUsername, 25, SpringLayout.SOUTH, lblHeading);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, psfNewPassword, 0, SpringLayout.SOUTH, txfUsername);
		springLayout.putConstraint(SpringLayout.WEST, psfNewPassword, 0, SpringLayout.WEST, txfUsername);

		
		springLayout.putConstraint(SpringLayout.NORTH, txfFirstname, 0, SpringLayout.SOUTH, psfNewPassword);
		springLayout.putConstraint(SpringLayout.WEST, txfFirstname, 0, SpringLayout.WEST, psfNewPassword);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfMiddlename, 0, SpringLayout.SOUTH, txfFirstname);
		springLayout.putConstraint(SpringLayout.WEST, txfMiddlename, 0, SpringLayout.WEST, txfFirstname);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfLastname, 0, SpringLayout.SOUTH, txfMiddlename);
		springLayout.putConstraint(SpringLayout.WEST, txfLastname, 0, SpringLayout.WEST, txfMiddlename);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfTitle, 0, SpringLayout.SOUTH, txfLastname);
		springLayout.putConstraint(SpringLayout.WEST, txfTitle, 0, SpringLayout.WEST, txfLastname);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfPosition, 0, SpringLayout.SOUTH, txfTitle);
		springLayout.putConstraint(SpringLayout.WEST, txfPosition, 0, SpringLayout.WEST, txfTitle);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfEmail, 0, SpringLayout.SOUTH, txfPosition);
		springLayout.putConstraint(SpringLayout.WEST, txfEmail, 0, SpringLayout.WEST, txfPosition);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblUsername, 0, SpringLayout.BASELINE, txfUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, 0, SpringLayout.WEST, txfUsername);
		
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblNewPassword, 0, SpringLayout.BASELINE, psfNewPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblNewPassword, 0, SpringLayout.WEST, psfNewPassword);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblFirstname, 0, SpringLayout.BASELINE, txfFirstname);
		springLayout.putConstraint(SpringLayout.EAST, lblFirstname, 0, SpringLayout.WEST, txfFirstname);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblMiddlename, 0, SpringLayout.BASELINE, txfMiddlename);
		springLayout.putConstraint(SpringLayout.EAST, lblMiddlename, 0, SpringLayout.WEST, txfMiddlename);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblLastname, 0, SpringLayout.BASELINE, txfLastname);
		springLayout.putConstraint(SpringLayout.EAST, lblLastname, 0, SpringLayout.WEST, txfLastname);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblTitle, 0, SpringLayout.BASELINE, txfTitle);
		springLayout.putConstraint(SpringLayout.EAST, lblTitle, 0, SpringLayout.WEST, txfTitle);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblPosition, 0, SpringLayout.BASELINE, txfPosition);
		springLayout.putConstraint(SpringLayout.EAST, lblPosition, 0, SpringLayout.WEST, txfPosition);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblEmail, 0, SpringLayout.BASELINE, txfEmail);
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, 0, SpringLayout.WEST, txfEmail);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 15, SpringLayout.SOUTH, txfEmail);
		springLayout.putConstraint(SpringLayout.WEST, btnUpdate, -30, SpringLayout.WEST, txfEmail);
		
		springLayout.putConstraint(SpringLayout.BASELINE, btnClear, 0, SpringLayout.BASELINE, btnUpdate);
		springLayout.putConstraint(SpringLayout.WEST, btnClear, 0, SpringLayout.EAST, btnUpdate);
		
		springLayout.putConstraint(SpringLayout.BASELINE, btnBack, 0, SpringLayout.BASELINE, btnClear);
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 0, SpringLayout.EAST, btnClear);
	}
	
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	protected void clearFields(){
		txfUsername.setText(null);
		psfNewPassword.setText(null);	
		txfFirstname.setText(null);
		txfMiddlename.setText(null);
		txfLastname.setText(null);
		txfTitle.setText(null);
		txfPosition.setText(null);		
		txfEmail.setText(null);
	}
	
	public void setModel( EmployeeListModel empList) {
		  this.empListModel = empList;
	}
	      
}