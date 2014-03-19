package scheduler.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JList;

import scheduler.controller.Controller;
import scheduler.model.Employee;

public class EmployeeDialog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmployeeDialog dialog = new EmployeeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmployeeDialog() {
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 239, 500, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
						
						Employee emp = new Employee();
						emp.setFirstName(textField.getText().toString());
						emp.setMiddleName(textField_1.getText());
						emp.setLastName(textField_2.getText());
						emp.setPosition(textField_3.getText());
						emp.setUsername(textField_4.getText());
						emp.setEmail(textField_6.getText());
											
						Controller Controller = new Controller();
						Controller.insertEmployee(emp, textField_5.getText());
						
						//Employee emp = new Employee();
						//Controller.insertEmployee(emp);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBounds(0, 0, 1, 239);
			getContentPane().add(layeredPane);
		}
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(98, 26, 80, 16);
		getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Middle Name");
		lblLastName.setBounds(98, 57, 90, 16);
		getContentPane().add(lblLastName);
		{
			JLabel lblPosition = new JLabel("Last Name");
			lblPosition.setBounds(98, 88, 80, 16);
			getContentPane().add(lblPosition);
		}
		{
			JLabel lblTitle = new JLabel("Position");
			lblTitle.setBounds(98, 116, 80, 16);
			getContentPane().add(lblTitle);
		}
		{
			textField = new JTextField();
			textField.setBounds(257, 23, 102, 22);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(257, 54, 102, 22);
			getContentPane().add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(257, 82, 102, 22);
			getContentPane().add(textField_2);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(257, 110, 102, 22);
			getContentPane().add(textField_3);
		}
		{
			JLabel lblUserName = new JLabel("User Name");
			lblUserName.setBounds(98, 144, 80, 16);
			getContentPane().add(lblUserName);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(98, 172, 80, 16);
			getContentPane().add(lblPassword);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(257, 138, 102, 22);
			getContentPane().add(textField_4);
		}
		{
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(257, 166, 102, 22);
			getContentPane().add(textField_5);
		}
		{
			textField_6 = new JTextField();
			textField_6.setColumns(10);
			textField_6.setBounds(257, 199, 207, 22);
			getContentPane().add(textField_6);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(98, 205, 80, 16);
			getContentPane().add(lblEmail);
		}
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
