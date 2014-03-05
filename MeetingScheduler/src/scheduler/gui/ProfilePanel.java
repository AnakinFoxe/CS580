package scheduler.gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ProfilePanel extends JPanel {

	//private static final long serialVersionUID = 4932513626147661013L;
	private JLabel lblUsername;
	private JLabel lblOldPassword;
	private JLabel lblNewPassword;
	private JLabel lblFirstname;
	private JLabel lblLastname;
	private JLabel lblTitle;
	private JLabel lblPosition;
	private JTextField txfUsername;
	private JPasswordField psfOldPassword;
	private JPasswordField psfNewPassword;
	private JPasswordField psfNewPasswordConfirmed;
	private JTextField txfFirstname;
	private JTextField txfLastname;
	private JTextField txfTitle;
	private JTextField txfPosition;
	private CardLayout cardlayout;
	private JPanel controller;
	
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnBack;
	
	
	
	/**
	 * Create the panel.
	 */
	public ProfilePanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		lblUsername = new JLabel();
		lblUsername.setText("Username");
		lblUsername.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblUsername);
		
		txfUsername = new JTextField();
		add(txfUsername);
		txfUsername.setColumns(10);
		
		lblOldPassword = new JLabel();
		lblOldPassword.setText("Old Password");
		lblOldPassword.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblOldPassword);
		
		psfOldPassword = new JPasswordField();
		add(psfOldPassword);
		psfOldPassword.setColumns(10);
		
		lblNewPassword = new JLabel();
		lblNewPassword.setText("New Password");
		lblNewPassword.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblNewPassword);
		
		psfNewPassword = new JPasswordField();
		add(psfNewPassword);
		psfNewPassword.setColumns(10);
		
		psfNewPasswordConfirmed = new JPasswordField();
		add(psfNewPasswordConfirmed);
		psfNewPasswordConfirmed.setColumns(10);
		
		lblFirstname = new JLabel();
		lblFirstname.setText("First Name");
		lblFirstname.setFont(new Font("Consolas", Font.PLAIN, 14));
		add(lblFirstname);
		
		txfFirstname = new JTextField();
		add(txfFirstname);
		txfFirstname.setColumns(10);
		
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
		
		btnUpdate = new JButton("Update");
//		btnUpdate.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//            	if (controller == null){
//            		getData();
//            	}
//            	cardlayout.show(controller,"home");
//            }
//            
//		});
		add(btnUpdate);
		
		btnClear = new JButton("Clear");
//		btnClear.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//            	if (controller == null){
//            		getData();
//            	}
//            	cardlayout.show(controller,"home");
//            }
//            
//		});
		add(btnClear);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"home");
            }
            
		});
		add(btnBack);
		
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txfUsername, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, txfUsername, 0, SpringLayout.VERTICAL_CENTER, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, psfOldPassword, 0, SpringLayout.SOUTH, txfUsername);
		springLayout.putConstraint(SpringLayout.WEST, psfOldPassword, 0, SpringLayout.WEST, txfUsername);
		
		springLayout.putConstraint(SpringLayout.NORTH, psfNewPassword, 0, SpringLayout.SOUTH, psfOldPassword);
		springLayout.putConstraint(SpringLayout.WEST, psfNewPassword, 0, SpringLayout.WEST, psfOldPassword);

		springLayout.putConstraint(SpringLayout.NORTH, psfNewPasswordConfirmed, 0, SpringLayout.SOUTH, psfNewPassword);
		springLayout.putConstraint(SpringLayout.WEST, psfNewPasswordConfirmed, 0, SpringLayout.WEST, psfNewPassword);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfFirstname, 0, SpringLayout.SOUTH, psfNewPasswordConfirmed);
		springLayout.putConstraint(SpringLayout.WEST, txfFirstname, 0, SpringLayout.WEST, psfNewPasswordConfirmed);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfLastname, 0, SpringLayout.SOUTH, txfFirstname);
		springLayout.putConstraint(SpringLayout.WEST, txfLastname, 0, SpringLayout.WEST, txfFirstname);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfTitle, 0, SpringLayout.SOUTH, txfLastname);
		springLayout.putConstraint(SpringLayout.WEST, txfTitle, 0, SpringLayout.WEST, txfLastname);
		
		springLayout.putConstraint(SpringLayout.NORTH, txfPosition, 0, SpringLayout.SOUTH, txfTitle);
		springLayout.putConstraint(SpringLayout.WEST, txfPosition, 0, SpringLayout.WEST, txfTitle);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblUsername, 0, SpringLayout.BASELINE, txfUsername);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, 0, SpringLayout.WEST, txfUsername);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblOldPassword, 0, SpringLayout.BASELINE, psfOldPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblOldPassword, 0, SpringLayout.WEST, psfOldPassword);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblNewPassword, 0, SpringLayout.BASELINE, psfNewPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblNewPassword, 0, SpringLayout.WEST, psfNewPassword);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblFirstname, 0, SpringLayout.BASELINE, txfFirstname);
		springLayout.putConstraint(SpringLayout.EAST, lblFirstname, 0, SpringLayout.WEST, txfFirstname);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblLastname, 0, SpringLayout.BASELINE, txfLastname);
		springLayout.putConstraint(SpringLayout.EAST, lblLastname, 0, SpringLayout.WEST, txfLastname);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblTitle, 0, SpringLayout.BASELINE, txfTitle);
		springLayout.putConstraint(SpringLayout.EAST, lblTitle, 0, SpringLayout.WEST, txfTitle);
		
		springLayout.putConstraint(SpringLayout.BASELINE, lblPosition, 0, SpringLayout.BASELINE, txfPosition);
		springLayout.putConstraint(SpringLayout.EAST, lblPosition, 0, SpringLayout.WEST, txfPosition);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 0, SpringLayout.SOUTH, txfPosition);
		springLayout.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, txfPosition);
		
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

}
