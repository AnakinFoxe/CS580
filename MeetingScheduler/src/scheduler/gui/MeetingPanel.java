package scheduler.gui;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import scheduler.model.Employee;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;
import scheduler.model.MeetingModel;

public class MeetingPanel extends JPanel {
	/**
	 * 
	 */
	//
	private static final long serialVersionUID = 3159213351500534829L;
	private JLabel lblSelectAttendees;
	private JButton btnContinue;
	private CardLayout cardlayout;
	private JPanel controller;
	private EmployeeModel employee;
	private Box employeeBox;
	private JScrollPane jscrlBox;
	private List<Employee> employeeList;
	private List<Employee> attendeeList;
	private EmployeeListModel attendeeListModel;
	private Flag flag;
	private Flag timePVisible;

	public MeetingPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		this.setBackground(new Color(255, 255, 255));

		lblSelectAttendees = new JLabel("Step 1: Select Attendees");
		lblSelectAttendees.setFont(new Font("Arial", Font.PLAIN, 24));
		add(lblSelectAttendees);

		btnContinue = new JButton("Continue");
		btnContinue.setPreferredSize(new Dimension(121, 30));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
					getData();
				}
				getAttendees();
				flag.setFlag(false);
				timePVisible.setFlag(true);
				cardlayout.show(controller,"time");
			}

		});
		
		add(btnContinue);
		employeeBox = Box.createVerticalBox();
		jscrlBox = new JScrollPane(employeeBox);
		jscrlBox.setPreferredSize(new Dimension(140,90));
		add(jscrlBox);
		
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -40, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, -50, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblSelectAttendees, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSelectAttendees, 20, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, jscrlBox, 30, SpringLayout.SOUTH, lblSelectAttendees);
		springLayout.putConstraint(SpringLayout.WEST, jscrlBox, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlBox, 0, SpringLayout.SOUTH, btnContinue);
		springLayout.putConstraint(SpringLayout.EAST, jscrlBox, 300, SpringLayout.WEST, this);
	}

	protected void getAttendees() {
		//System.out.println(employeeBox.getComponentCount());
		attendeeList = new ArrayList<Employee>();
		for (int i=0; i < employeeBox.getComponentCount(); i++){
			JRadioButton rdbtn = (JRadioButton) employeeBox.getComponent(i);
			if(rdbtn.isSelected()){
				attendeeList.add(employeeList.get(i));
				//System.out.println("attendee "+employeeList.get(i).getFirstName());
			}
		}
		attendeeListModel.setEmployeeList(attendeeList);
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

		this.flag = flag1;
		flag.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {

				if(Flag.modelName.equals(evt.getPropertyName())){

					// add some kind of function that generates user ID
					Employee host = employee.getEmployee();
					employeeList = Controller.genEmployeeList(host);	
					employeeBox.removeAll();
					for (Integer idx=0; idx<employeeList.size(); ++idx){
						//System.out.println(employeeList.get(idx).getFirstName());
						JRadioButton rdbtn = new JRadioButton(employeeList.get(idx).getFirstName());
						employeeBox.add(rdbtn);
					}
				}

			}
		});
	}

	public void setModel(EmployeeListModel attendeeList2) {
		attendeeListModel = attendeeList2;
		attendeeListModel.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				attendeeList = attendeeListModel.getList();
				if(employeeList != null && attendeeList != null){
					
					if(employeeList.size() >= attendeeList.size()){
						for(int i = 0; i < attendeeList.size(); i++){
							Employee attendee = attendeeList.get(i);
							if(employeeList.contains(attendee)){
								JRadioButton rdbtn = (JRadioButton) employeeBox.getComponent(employeeList.indexOf(attendee));
								rdbtn.setSelected(true);
							}
						}
					}
				}
				
			}
		});
	}

	public void setTFlag(Flag timePVisible) {
		// TODO Auto-generated method stub
		this.timePVisible = timePVisible;
	}
	
}
