package scheduler.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import scheduler.controller.Controller;
import scheduler.model.Employee;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DelEmpPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4762658305833154861L;
	private CardLayout cardlayout;
	private JPanel controller;
	private EmployeeModel employeeModel;
	private Flag adminVisible;
	/**
	 * Create the panel.
	 */
	public DelEmpPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Are you sure to delete it?");
		lblNewLabel.setBounds(49, 30, 162, 33);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller == null){
            		getData();
				}
				Employee employee = employeeModel.getEmployee();
				Controller.deleteEmployee(employee);
				adminVisible.setFlag(true);
				cardlayout.show(controller, "adminHome");
			}
		});
		btnNewButton.setBounds(6, 101, 117, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller == null){
            		getData();
            	}
				adminVisible.setFlag(true);
				cardlayout.show(controller, "adminHome");
			}
		});
		btnNewButton_1.setBounds(127, 101, 117, 29);
		add(btnNewButton_1);

	}

public void setModel(EmployeeModel model){
	this.employeeModel = model;
}

public void setModel(Flag model){
	this.adminVisible = model;
}
protected void getData() {
	// TODO Auto-generated method stub
	controller = (JPanel) this.getParent();
	cardlayout  = (CardLayout) controller.getLayout();
	}
}
