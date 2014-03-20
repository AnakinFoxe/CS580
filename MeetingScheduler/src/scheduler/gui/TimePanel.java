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
import scheduler.model.Flag;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
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
	private Flag tPanelVisible;
	private Flag roomPVisible;
	private Flag fromMeetingDet;
	
	public TimePanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		this.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Step 2: Select a Time");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 24));		
		add(lblNewLabel);
		
		lblPleaseSelectOne = new JLabel("Please select one of the available time slots:");
		lblPleaseSelectOne.setFont(new Font("Arial", Font.PLAIN, 14));	
		add(lblPleaseSelectOne);
		
		btnContinue = new JButton("Continue");
		btnContinue.setPreferredSize(new Dimension(121, 30));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				lblPleaseSelectOne.setForeground(Color.BLACK);
				boolean timeSelected = getTime();
				if(timeSelected){
					tPanelVisible.setFlag(false);
					roomPVisible.setFlag(true);
					cardlayout.show(controller,"room");
				}
				else
				{
					lblPleaseSelectOne.setForeground(Color.RED);
				}
			}

		});
		add(btnContinue);
		
		timeBox = Box.createVerticalBox();
		jscrlBox = new JScrollPane(timeBox);
		add(jscrlBox);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 20, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblPleaseSelectOne, 10, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblPleaseSelectOne, 20, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -40, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, -50, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, jscrlBox, 30, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, jscrlBox, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlBox, 0, SpringLayout.SOUTH, btnContinue);
		springLayout.putConstraint(SpringLayout.EAST, jscrlBox, 300, SpringLayout.WEST, this);
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
	}

	public void setModel(DateModel date) {
		// TODO Auto-generated method stub
		selectedTime = date;
	}
	
	public void setModel(EmployeeModel employee) {
		// TODO Auto-generated method stub
		this.user = employee;
	}

	public void setFlag(Flag timePVisible) {
		// TODO Auto-generated method stub
		this.tPanelVisible = timePVisible;
		tPanelVisible.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evnt) {
				// TODO Auto-generated method stub
				Boolean isVisible = tPanelVisible.getFlag();
				if(isVisible){
					times = Controller.genAvailableTime(user.getEmployee(), attendeeList.getList());
					timeBox.removeAll();
					group = new ButtonGroup();
					for (int i = 0; i < times.size(); i++) {
						Date current = times.get(i);
						String dateString = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(current);
						JRadioButton rdbtn = new JRadioButton(dateString);
						if(selectedTime != null && fromMeetingDet.getFlag()){
							if(current.equals(selectedTime)){
								rdbtn.setSelected(true);
							}
						}
						
						timeBox.add(rdbtn);
						group.add(rdbtn);
					}
				}
			}
		});
	}

	public void setRFlag(Flag roomPVisible) {
		// TODO Auto-generated method stub
		this.roomPVisible = roomPVisible;
	}

	public void setDFlag(Flag fromMeetingDet) {
		// TODO Auto-generated method stub
		this.fromMeetingDet = fromMeetingDet;
	}
	
	

	
}
