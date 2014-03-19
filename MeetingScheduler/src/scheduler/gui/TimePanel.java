package scheduler.gui;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;

import scheduler.controller.Controller;
import scheduler.model.DateModel;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Color;

public class TimePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3029601078739531195L;
	private CardLayout cardlayout;
	private JPanel controller;
	private JButton btnContinue;
	private EmployeeListModel attendeeList;
	private List<Date> times;
	private Box timeBox;
	private JScrollPane jscrlBox;
	private ButtonGroup group; 
	private DateModel selectedTime;
	private JLabel lblPleaseSelectOne;
	private EmployeeModel user;
	
	public TimePanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		JLabel lblNewLabel = new JLabel("Select a Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 27, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 186, SpringLayout.WEST, this);
		add(lblNewLabel);
		
		lblPleaseSelectOne = new JLabel("Please select one of the available time slots:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPleaseSelectOne, 64, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblPleaseSelectOne, 10, SpringLayout.WEST, this);
		add(lblPleaseSelectOne);
		
		btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				lblPleaseSelectOne.setForeground(Color.BLACK);
				boolean timeSelected = getTime();
				if(timeSelected){
					cardlayout.show(controller,"room");
				}
				else
				{
					lblPleaseSelectOne.setForeground(Color.RED);
				}
			}

		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -23, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, -35, SpringLayout.EAST, this);
		add(btnContinue);
		
		timeBox = Box.createVerticalBox();
		jscrlBox = new JScrollPane(timeBox);
		
		springLayout.putConstraint(SpringLayout.NORTH, jscrlBox, 6, SpringLayout.SOUTH, lblPleaseSelectOne);
		springLayout.putConstraint(SpringLayout.WEST, jscrlBox, 10, SpringLayout.WEST, lblPleaseSelectOne);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlBox, 0, SpringLayout.SOUTH, btnContinue);
		springLayout.putConstraint(SpringLayout.EAST, jscrlBox, 56, SpringLayout.EAST, lblPleaseSelectOne);
		add(jscrlBox);
	}
	
	protected boolean getTime() {
		boolean selected = false;
		for(int i = 0; i < timeBox.getComponentCount(); i++){
			JRadioButton rdbtn = (JRadioButton) timeBox.getComponent(i);
			if(rdbtn.isSelected()){
				selectedTime.set(times.get(i));
				selected = true;
			}
		}
		return selected;
		
	}

	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}

	public void setModel(EmployeeListModel employeeList) {
		// TODO Auto-generated method stub
		this.attendeeList = employeeList;
		attendeeList.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if(EmployeeListModel.modelName.equals(evt.getPropertyName())){
					times = Controller.genAvailableTime(null, attendeeList.getList());
					timeBox.removeAll();
					group = new ButtonGroup();
					for (int i = 0; i < times.size(); i++) {
						String dateString = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(times.get(i));
						JRadioButton rdbtn = new JRadioButton(dateString);
						timeBox.add(rdbtn);
						group.add(rdbtn);
					}
				}
			}
		});
	}

	public void setModel(DateModel date) {
		// TODO Auto-generated method stub
		selectedTime = date;
	}
	
	public void setModel(EmployeeModel employee) {
		// TODO Auto-generated method stub
		this.user = employee;
	}
	
}
