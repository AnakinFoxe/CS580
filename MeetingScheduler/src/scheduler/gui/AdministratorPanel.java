package scheduler.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import scheduler.controller.Controller;
import scheduler.model.DateModel;
import scheduler.model.Employee;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Room;
import scheduler.model.RoomListModel;


public class AdministratorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3966600203016804041L;
	
	private JLabel lblEmployees;
	private JLabel lblRooms;
	private JButton btnAddEmp;
	private JButton btnModifyEmp;
	private JButton btnAddRoom;
	private JButton btnModifyRoom;
	private JButton btnDeleteRoom;
	private JButton btnDeleteEmp;
	private CardLayout cardlayout;
	private JPanel controller;
	
	private Box employeeBox;
	private JScrollPane jscrlempBox;
	private List<Employee> employeeList;
	private EmployeeListModel employeeListModel;
	private ButtonGroup empGroup;
	private EmployeeModel employeeModel;
	
	private Box roomBox;
	private JScrollPane jscrlroomBox;
	private List<Room> roomList;
	private RoomListModel roomListModel;
	private ButtonGroup roomGroup;
		
	public AdministratorPanel() {
		lblEmployees = new JLabel("Employees");
		lblRooms = new JLabel("Rooms");
		btnAddEmp = new JButton("Add Employee");
		btnModifyEmp = new JButton("Modify Employee");
		btnAddRoom = new JButton("Add Room");
		btnModifyRoom = new JButton("Modify Room");
		btnDeleteRoom = new JButton("Delete Room");
		btnDeleteEmp = new JButton("Delete Employee");
		
		employeeBox = Box.createVerticalBox();
		jscrlempBox = new JScrollPane(employeeBox);
		add(jscrlempBox);
		jscrlempBox.setPreferredSize(new Dimension(140,90));
		
		roomBox = Box.createVerticalBox();
		jscrlroomBox = new JScrollPane(roomBox);
		add(jscrlroomBox);
		jscrlempBox.setPreferredSize(new Dimension(140,90));
		
		add(lblEmployees);
		add(lblRooms);
		add(btnAddRoom);
		add(btnModifyRoom);
		add(btnDeleteRoom);
		add(btnAddEmp);
		add(btnModifyEmp);
		add(btnDeleteEmp);
				
		btnDeleteEmp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}       	
            	JRadioButton rdbtn;
            	for(int i = 0; i < employeeBox.getComponentCount(); i++){
            		rdbtn = (JRadioButton) employeeBox.getComponent(i);
            		if(rdbtn.isSelected()){
            			employeeModel.setEmployee(employeeList.get(i));
            		}
            	}
            	cardlayout.show(controller,"delempPanel");
            }
            
		});		
		
		btnAddEmp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}       
            	cardlayout.show(controller,"empPanel");
            }       
            
		});
		btnModifyEmp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}   
            	
            	cardlayout.show(controller,"empPanel");
            }
            
		});
		
		btnAddRoom.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}       	
            	cardlayout.show(controller,"roomPanel");
            }
            
		});
		
		btnDeleteRoom.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller, "delroomPanel");
            }
		});	
		
		btnModifyRoom.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if (controller == null){
            		getData();
            	}       	
            	cardlayout.show(controller, "roomPanel");
            }
            
		});

		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
	
		springLayout.putConstraint(SpringLayout.WEST, lblEmployees, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblEmployees, 20, SpringLayout.NORTH, this);
		
		springLayout.putConstraint(SpringLayout.WEST, jscrlempBox, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, jscrlempBox, 5, SpringLayout.SOUTH, lblEmployees);
		springLayout.putConstraint(SpringLayout.EAST, jscrlempBox, -20, SpringLayout.WEST, lblRooms);
		
		springLayout.putConstraint(SpringLayout.WEST, btnAddEmp, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddEmp, 0, SpringLayout.EAST, btnModifyEmp);
		springLayout.putConstraint(SpringLayout.NORTH, btnAddEmp, 5, SpringLayout.SOUTH, jscrlempBox);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModifyEmp, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnModifyEmp, 5, SpringLayout.SOUTH, btnAddEmp);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteEmp, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteEmp, 5, SpringLayout.SOUTH, btnModifyEmp);
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteEmp, 0, SpringLayout.EAST, btnModifyEmp);
		
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblRooms, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 0, SpringLayout.NORTH, lblEmployees);
	
		springLayout.putConstraint(SpringLayout.WEST, jscrlroomBox, 0, SpringLayout.WEST, lblRooms);
		springLayout.putConstraint(SpringLayout.NORTH, jscrlroomBox, 5, SpringLayout.SOUTH, lblEmployees);
		springLayout.putConstraint(SpringLayout.EAST, jscrlroomBox, -75, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, jscrlroomBox, 0, SpringLayout.SOUTH, jscrlempBox);
		
		springLayout.putConstraint(SpringLayout.WEST, btnAddRoom, 0, SpringLayout.WEST, lblRooms);
		springLayout.putConstraint(SpringLayout.NORTH, btnAddRoom, 5, SpringLayout.SOUTH, jscrlroomBox);
		springLayout.putConstraint(SpringLayout.EAST, btnAddRoom, 0, SpringLayout.EAST, btnModifyRoom);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModifyRoom, 0, SpringLayout.WEST, lblRooms);
		springLayout.putConstraint(SpringLayout.NORTH, btnModifyRoom, 5, SpringLayout.SOUTH, btnAddRoom);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteRoom, 0, SpringLayout.WEST, lblRooms);
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteRoom, 5, SpringLayout.SOUTH, btnModifyRoom);
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteRoom, 0, SpringLayout.EAST, btnModifyRoom);
		
		springLayout.putConstraint(SpringLayout.SOUTH, this, 5, SpringLayout.SOUTH, btnDeleteRoom);
		springLayout.putConstraint(SpringLayout.SOUTH, this, 5, SpringLayout.SOUTH, btnDeleteEmp);
		
		setLayout(springLayout);
	}
	
	public void setModel(RoomListModel roomList1){
		this.roomListModel = roomList1;
		System.out.println("Seeting room list model");
		roomListModel.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(RoomListModel.modelName.equals(arg0.getPropertyName())){
					//add method for date
					roomBox.removeAll();
					roomGroup = new ButtonGroup();
					jscrlroomBox.setFocusable(true);
					roomList = roomListModel.getList();//Controller.genRoomList();
					
					for(int i = 0; i < roomList.size(); i++){
						JRadioButton rdbtn = new JRadioButton(roomList.get(i).getName().toString()); 
						roomBox.add(rdbtn);
						roomGroup.add(rdbtn);
					}
				}
			}
		});
		roomBox.revalidate();
		roomBox.repaint();
		roomBox.setVisible(true);
		//jscrlroomBox.repaint();
	}
	
	public void setModel(EmployeeListModel employeeList1){
		this.employeeListModel=employeeList1;//EmployeeListModel.ListModel = empList1;
		
		employeeListModel.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(EmployeeListModel.modelName.equals(arg0.getPropertyName())){
					//add method for date
					
					employeeBox.removeAll();
					empGroup = new ButtonGroup();
					jscrlempBox.setFocusable(true);
					employeeList = employeeListModel.getList();//Controller.genRoomList();
					
					for(int i = 0; i < employeeList.size(); i++){
						JRadioButton rdbtn = new JRadioButton(employeeList.get(i).getFirstName().concat(" ").concat(employeeList.get(i).getLastName())); 
						employeeBox.add(rdbtn);
						empGroup.add(rdbtn);
					}
				}
			}
		});
		roomBox.revalidate();
		roomBox.repaint();
		roomBox.setVisible(true);
		//jscrlroomBox.repaint();
	}
	
	public void setModel(EmployeeModel model){
		this.employeeModel = model;
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
}
