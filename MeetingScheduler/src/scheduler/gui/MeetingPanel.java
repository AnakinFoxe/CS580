package scheduler.gui;


import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import scheduler.controller.Controller;
import scheduler.controller.EmployeeController;
import scheduler.model.Employee;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;

import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBoxMenuItem;

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
	private EmployeeModel employee;
	private JList employeeList; 
	private Box employeeBox;
	private JScrollPane jscrlBox;
	private Flag flag;
	
	public MeetingPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		rdbtnNewRadioButton = new JRadioButton("employee1");
		//springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 65, SpringLayout.NORTH, this);
		//springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 34, SpringLayout.WEST, this);
		//add(rdbtnNewRadioButton);
		
		rdbtnEmployee = new JRadioButton("employee2");
		//springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmployee, 8, SpringLayout.SOUTH, rdbtnNewRadioButton);
		//springLayout.putConstraint(SpringLayout.WEST, rdbtnEmployee, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		//add(rdbtnEmployee);
		
		rdbtnEmployee_1 = new JRadioButton("employee3");
		//springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmployee_1, 6, SpringLayout.SOUTH, rdbtnEmployee);
		//springLayout.putConstraint(SpringLayout.WEST, rdbtnEmployee_1, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		//add(rdbtnEmployee_1);
		
		rdbtnEmployee_2 = new JRadioButton("employee4");
		//springLayout.putConstraint(SpringLayout.NORTH, rdbtnEmployee_2, 6, SpringLayout.SOUTH, rdbtnEmployee_1);
		//springLayout.putConstraint(SpringLayout.EAST, rdbtnEmployee_2, 0, SpringLayout.EAST, rdbtnNewRadioButton);
		//add(rdbtnEmployee_2);
		
		lblSelectAttendees = new JLabel("Select Attendees");
		//springLayout.putConstraint(SpringLayout.NORTH, lblSelectAttendees, 28, SpringLayout.NORTH, this);
		//springLayout.putConstraint(SpringLayout.WEST, lblSelectAttendees, 175, SpringLayout.WEST, this);
		add(lblSelectAttendees);
		
		btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
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
		employeeBox = Box.createVerticalBox();
		employeeBox.add(rdbtnNewRadioButton);
		employeeBox.add(rdbtnEmployee);
		jscrlBox = new JScrollPane(employeeBox);
		springLayout.putConstraint(SpringLayout.NORTH, jscrlBox, 70, SpringLayout.SOUTH, lblSelectAttendees);
		springLayout.putConstraint(SpringLayout.WEST, jscrlBox, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlBox, 174, SpringLayout.NORTH, this);
		jscrlBox.setPreferredSize(new Dimension(140,90));
		add(jscrlBox);
		
		//add(list);
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}

	public void setModel(EmployeeModel employeeModel) {
		// TODO Auto-generated method stub
		this.employee = employeeModel;
		employee.addPropertyChangeListener(new PropertyChangeListener() {
	    	  
			public void propertyChange(PropertyChangeEvent evt) {
				
				if(EmployeeModel.modelName.equals(evt.getPropertyName())){
					
					// add some kind of function that uses the users ID
				}
				
			}
	      });
	}

	public void setModel(Flag flag1) {
		// TODO Auto-generated method stub
		this.flag = flag1;
		flag.addPropertyChangeListener(new PropertyChangeListener() {
	    	  
			public void propertyChange(PropertyChangeEvent evt) {
				
				if(Flag.modelName.equals(evt.getPropertyName())){
					
					// add some kind of function that generates user ID
					List<Employee> employeeList = Controller.genEmployeeList();
					
					for (Integer idx=0; idx<employeeList.size(); ++idx){
						System.out.println(employeeList.get(idx).getFirstName());
					}
				}
				
			}
	      });
	}
}
