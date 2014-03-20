package scheduler.gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.SpringLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;

import scheduler.controller.Controller;
import scheduler.gui.JCalendar.BasicDate;
import scheduler.model.DateModel;
import scheduler.model.Employee;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;
import scheduler.model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomePanel extends JPanel {
	/**
	 * 
	 */

	// some comments 
	private static final long serialVersionUID = -5218352851092235305L;
	private JButton btnCreateMeeting;
	private JButton btnUpdateProfile;
	private JButton btnLogout;
	private JLabel lblUsername;
	private static JCalendar calenderPanel;
	private CardLayout cardlayout;
	private JPanel controller;
	private EmployeeModel employee;
	private String name;
	private JLabel lblName;
	private JLabel lblMiddleName;
	private JLabel lblLastName;
	private JLabel lblPosition;
	private JLabel lblEmail;
	private Flag flag;
	private Flag isVisible;
	protected List<Date> meetings;
	protected DateModel selectedDate;
	private Flag meetingDetVisible;
	protected List<Date> needToAccept;


	public HomePanel() {
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		btnCreateMeeting = new JButton("Create Meeting");
		btnCreateMeeting.setPreferredSize(new Dimension(121, 30));
		btnCreateMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				flag.setFlag(true);
				isVisible.setFlag(false);
            	cardlayout.show(controller,"meeting");
			}
		});
		add(btnCreateMeeting);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setPreferredSize(new Dimension(121, 30));
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
				isVisible.setFlag(false);
            	cardlayout.show(controller,"profile");
			}
		});
		add(btnUpdateProfile);
		
//		btnLogout = new JButton("Logout");
//		btnLogout.setPreferredSize(new Dimension(121, 30));
//		btnLogout.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if (controller == null){
//            		getData();
//            	}
//				employee = new EmployeeModel();
//				flag.setFlag(true);
//				isVisible.setFlag(false);
//				Controller.disconnect();
//            	cardlayout.show(controller,"login");
//			}
//		});
//		add(btnLogout);
		
		lblUsername = new JLabel("Hello:");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblUsername);
		
		lblName = new JLabel();
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblName);
		lblMiddleName = new JLabel();
		lblMiddleName.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblMiddleName);
		lblLastName = new JLabel();
		lblLastName.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblLastName);
		
		lblPosition = new JLabel();
		lblPosition.setFont(new Font("Arial", Font.PLAIN, 14));
		add(lblPosition);
		
		lblEmail = new JLabel();
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		add(lblEmail);
		
		calenderPanel = new JCalendar();
		
		calenderPanel.addMouseListener(new MouseListener() {
			private Date beginClickDate;
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("got here");
				if (beginClickDate != null) {
		            Date mouseOverDate = calenderPanel.getDate(arg0.getX(),arg0.getY());
		            Color color = calenderPanel.getDateHighlight(mouseOverDate);
		            
		            if (mouseOverDate != null && beginClickDate.equals(mouseOverDate) && color != null) {
		            	Date select = null;  
		        		select = calenderPanel.getCursorDate();
		                if(select != null){
		                	selectedDate.set(select);
		                    if (controller == null){
		                		getData();
		                	}
		                    isVisible.setFlag(false);
		                    meetingDetVisible.setFlag(true);
		                    cardlayout.show(controller,"meetingDetails");
		                }
		            }
				}
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				beginClickDate = calenderPanel.getCursorDate();
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		springLayout.putConstraint(SpringLayout.NORTH, calenderPanel, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, calenderPanel, -588, SpringLayout.EAST, this);
		
		calenderPanel.setBackground(new Color(153, 204, 255));
		//calenderPanel.setDate(new Date());
		add(calenderPanel);
		calenderPanel.setLayout(new SpringLayout());
		
		
		springLayout.putConstraint(SpringLayout.SOUTH, btnCreateMeeting, -80, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnCreateMeeting, 50, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdateProfile, 10, SpringLayout.SOUTH, btnCreateMeeting);
		springLayout.putConstraint(SpringLayout.WEST, btnUpdateProfile, 0, SpringLayout.WEST, btnCreateMeeting);
		springLayout.putConstraint(SpringLayout.EAST, btnUpdateProfile, 0, SpringLayout.EAST, btnCreateMeeting);
		
//		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 0, SpringLayout.NORTH, btnUpdateProfile);
//		springLayout.putConstraint(SpringLayout.WEST, btnLogout, 15, SpringLayout.EAST, btnUpdateProfile);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 65, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 65, SpringLayout.WEST, this);
		
		springLayout.putConstraint(SpringLayout.WEST, lblName, 6, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, 0, SpringLayout.SOUTH, lblUsername);
		
		springLayout.putConstraint(SpringLayout.WEST, lblMiddleName, 6, SpringLayout.EAST, lblName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblMiddleName, 0, SpringLayout.SOUTH, lblName);
				
		springLayout.putConstraint(SpringLayout.WEST, lblLastName, 6, SpringLayout.EAST, lblMiddleName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblLastName, 0, SpringLayout.SOUTH, lblName);
		
		springLayout.putConstraint(SpringLayout.WEST, lblPosition, 0, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.NORTH, lblPosition, 6, SpringLayout.SOUTH, lblName);
		
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 6, SpringLayout.SOUTH, lblPosition);
		springLayout.putConstraint(SpringLayout.SOUTH, calenderPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, calenderPanel, 0, SpringLayout.EAST, this);
	}

	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	
	public void setModel(final EmployeeModel employeeModel) {
		  this.employee = employeeModel;
	      employee.addPropertyChangeListener(new PropertyChangeListener() {
	    	  
			public void propertyChange(PropertyChangeEvent evt) {
				
				if(EmployeeModel.modelName.equals(evt.getPropertyName())){
					name = employee.getEmployee().getFirstName();
					lblName.setText(name);
					lblMiddleName.setText(employee.getEmployee().getMiddleName());
					lblLastName.setText(employee.getEmployee().getLastName().toUpperCase());
					lblPosition.setText(employee.getEmployee().getPosition());
					lblEmail.setText(employee.getEmployee().getEmail());
					
				}
				// TODO Auto-generated method stub
				
			}
	      });
	   }

	public void setModel(Flag flag1, int index) {
		// TODO Auto-generated method stub
		if(index == 0){
			flag = flag1;
		}
		else if(index == 1)
		{
			isVisible = flag1;
			isVisible.addPropertyChangeListener(new PropertyChangeListener() {

				public void propertyChange(PropertyChangeEvent arg0) {
					// TODO Auto-generated method stub
					// refresh calender
					if(isVisible.getFlag()){
						Employee user = employee.getEmployee();
						meetings = Controller.getMeetingDate(user);
						List<Meeting> meetings2Accept = Controller.checkAcceptance(user.getUsrId());
						needToAccept = new ArrayList<Date>();
						
						for(int i=0; i < meetings2Accept.size(); i++){
							Date date = meetings2Accept.get(i).getStartTime();
							needToAccept.add(date);
							//System.out.println(dtFormat.format(date));
						}
						
						if(selectedDate != null){
							if(!meetings.contains(selectedDate.getDate())){
								calenderPanel.setDateHighlight(selectedDate.getDate(), null);
							}
						}
						if(meetings != null){
							Color color = Color.ORANGE;
							for(int i = 0; i < meetings.size(); i++){
								int range = meetings.get(i).compareTo(calenderPanel.getCalendarView());

								if( range >= 0 && range <=  30){
									calenderPanel.setDateHighlight(meetings.get(i), color);
								}

							}
							color = Color.RED;
							for(int i = 0; i < needToAccept.size(); i++){
								int range = needToAccept.get(i).compareTo(calenderPanel.getCalendarView());

								if( range >= 0 && range <=  30){
									calenderPanel.setDateHighlight(needToAccept.get(i), color);
								}

							}
							
						}
						/*if(needToAccept.size() > 0){
							String[] option = {"ok"};
							int notify = JOptionPane.showOptionDialog(controller,
									"You have a new meeting marked red"+
											"please confirm if you are going",
											"Confirm meeting",
											JOptionPane.OK_OPTION,
											JOptionPane.INFORMATION_MESSAGE,
											null,
											option,
											option);
						}*/
					}
				}
			});
		}
	}
	
	public void setModel(DateModel dateModel){
		this.selectedDate = dateModel;
	}

	public void setMeetDetFlag(Flag meetingDetVisible) {
		// TODO Auto-generated method stub
		this.meetingDetVisible = meetingDetVisible;
	}
}

