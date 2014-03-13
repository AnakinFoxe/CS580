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
import scheduler.model.Employee;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;

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

	public MeetingPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		lblSelectAttendees = new JLabel("Select Attendees");
		add(lblSelectAttendees);

		btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
					getData();
				}
				getAttendees();
				cardlayout.show(controller,"time");
			}

		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -44, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, -65, SpringLayout.EAST, this);
		add(btnContinue);
		employeeBox = Box.createVerticalBox();
		jscrlBox = new JScrollPane(employeeBox);
		springLayout.putConstraint(SpringLayout.NORTH, jscrlBox, 24, SpringLayout.SOUTH, lblSelectAttendees);
		springLayout.putConstraint(SpringLayout.WEST, jscrlBox, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlBox, 0, SpringLayout.SOUTH, btnContinue);
		springLayout.putConstraint(SpringLayout.EAST, jscrlBox, 211, SpringLayout.WEST, this);
		jscrlBox.setPreferredSize(new Dimension(140,90));
		add(jscrlBox);

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
					employeeList = Controller.genEmployeeList();
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
	}
}
