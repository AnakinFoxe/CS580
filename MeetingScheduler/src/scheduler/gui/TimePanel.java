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
import scheduler.model.EmployeeListModel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractAction;
import javax.swing.Action;

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
	private Date selectedTime;
	
	public TimePanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		group = new ButtonGroup();
		JLabel lblNewLabel = new JLabel("Select a Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 27, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 186, SpringLayout.WEST, this);
		add(lblNewLabel);
		
		JLabel lblPleaseSelectOne = new JLabel("Please select one of the available time slots:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPleaseSelectOne, 64, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblPleaseSelectOne, 10, SpringLayout.WEST, this);
		add(lblPleaseSelectOne);
		
		JRadioButton rdbtnPm = new JRadioButton("1/27/2014, 10:00 AM");
		/*springLayout.putConstraint(SpringLayout.WEST, rdbtnPm_2, 0, SpringLayout.WEST, rdbtnPm);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnPm_2, 0, SpringLayout.EAST, rdbtnPm);
		add(rdbtnPm_2);*/
		
		JRadioButton rdbtnPm_3 = new JRadioButton("1/27/2014, 03:00 PM");
		/*springLayout.putConstraint(SpringLayout.NORTH, rdbtnPm_1, 6, SpringLayout.SOUTH, rdbtnPm_4);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPm_1, 0, SpringLayout.WEST, rdbtnPm);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnPm_1, 0, SpringLayout.EAST, rdbtnPm);
		add(rdbtnPm_1);*/
		
		btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				boolean timeSelected = getTime();
				if(timeSelected){
					cardlayout.show(controller,"room");
				}
			}

		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -23, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, -35, SpringLayout.EAST, this);
		add(btnContinue);
		JRadioButton rdbtnPm_4 = new JRadioButton("1/27/2014, 05:00 PM");
		
		timeBox = Box.createVerticalBox();
		/*group.add(rdbtnPm);
		group.add(rdbtnPm_3);
		group.add(rdbtnPm_4);
		timeBox.add(rdbtnPm);
		timeBox.add(rdbtnPm_3);
		timeBox.add(rdbtnPm_4)*/;
		//timeBox.add();
		jscrlBox = new JScrollPane(timeBox);
		
//		for (int i = 0; i < t.size(); i++) {
//			JRadioButton rdbtn = new JRadioButton(t.get(i));
//			timeBox.add(rdbtn);
//			//addTime(i);
//		}
		//timeBox.add(rdbtnmntmNewRadioItem);
		springLayout.putConstraint(SpringLayout.NORTH, jscrlBox, 6, SpringLayout.SOUTH, lblPleaseSelectOne);
		springLayout.putConstraint(SpringLayout.WEST, jscrlBox, 10, SpringLayout.WEST, lblPleaseSelectOne);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlBox, 0, SpringLayout.SOUTH, btnContinue);
		springLayout.putConstraint(SpringLayout.EAST, jscrlBox, 0, SpringLayout.EAST, lblPleaseSelectOne);
		add(jscrlBox);
	}
	
	protected boolean getTime() {
		boolean selected = false;
		for(int i = 0; i < timeBox.getComponentCount(); i++){
			JRadioButton rdbtn = (JRadioButton) timeBox.getComponent(i);
			if(rdbtn.isSelected()){
				selectedTime = times.get(i);
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
					//times = Controller.genAvailableTimes(attendeeList.getList());
					timeBox.removeAll();
					for (int i = 0; i < times.size(); i++) {
						JRadioButton rdbtn = new JRadioButton(times.get(i).toString());
						timeBox.add(rdbtn);
						group.add(rdbtn);
					}
				}
			}
		});
	}
	
	
}
