package scheduler.gui;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


public class AdministratorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3966600203016804041L;
	
	private JLabel lblEmployees;
	private JLabel lblRooms;
	private JScrollPane emScrollPane;
	private JScrollPane roomScrollPane;
	private JButton btnAddEmp;
	private JButton btnModifyEmp;
	private JButton btnAddRoom;
	private JButton btnModifyRoom;
	private JButton btnDeleteRoom;
	private JButton btnDeleteEmp;
	private JList emList;
	private JList roomList;
	private CardLayout cardlayout;
	private JPanel controller;
	
	public AdministratorPanel() {
		lblEmployees = new JLabel("Employees");
		lblRooms = new JLabel("Rooms");
		emScrollPane = new JScrollPane();
		roomScrollPane = new JScrollPane();
		btnAddEmp = new JButton("Add Employee");
		btnModifyEmp = new JButton("Modify Employee");
		btnAddRoom = new JButton("Add Room");
		btnModifyRoom = new JButton("Modify Room");
		btnDeleteRoom = new JButton("Delete Room");
		btnDeleteEmp = new JButton("Delete Employee");
		emList = new JList();
		roomList = new JList();
		
		
		emScrollPane.setViewportView(emList);
		roomScrollPane.setViewportView(roomList);
		
		add(lblEmployees);
		add(lblRooms);
		add(emScrollPane);
		add(roomScrollPane);
		add(btnAddRoom);
		add(btnModifyRoom);
		add(btnDeleteRoom);
		add(btnAddEmp);
		add(btnModifyEmp);
		add(btnDeleteEmp);
		
		setBackground(Color.LIGHT_GRAY);
		SpringLayout springLayout = new SpringLayout();
	
		springLayout.putConstraint(SpringLayout.WEST, lblEmployees, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblEmployees, 20, SpringLayout.NORTH, this);
		
		springLayout.putConstraint(SpringLayout.WEST, emScrollPane, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, emScrollPane, 5, SpringLayout.SOUTH, lblEmployees);
		springLayout.putConstraint(SpringLayout.EAST, emScrollPane, -20, SpringLayout.WEST, lblRooms);
		
		springLayout.putConstraint(SpringLayout.WEST, btnAddEmp, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddEmp, 0, SpringLayout.EAST, btnModifyEmp);
		springLayout.putConstraint(SpringLayout.NORTH, btnAddEmp, 5, SpringLayout.SOUTH, emScrollPane);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModifyEmp, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnModifyEmp, 5, SpringLayout.SOUTH, btnAddEmp);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteEmp, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteEmp, 5, SpringLayout.SOUTH, btnModifyEmp);
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteEmp, 0, SpringLayout.EAST, btnModifyEmp);
		
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblRooms, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 0, SpringLayout.NORTH, lblEmployees);
	
		springLayout.putConstraint(SpringLayout.WEST, roomScrollPane, 0, SpringLayout.WEST, lblRooms);
		springLayout.putConstraint(SpringLayout.NORTH, roomScrollPane, 5, SpringLayout.SOUTH, lblEmployees);
		springLayout.putConstraint(SpringLayout.EAST, roomScrollPane, -75, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, roomScrollPane, 0, SpringLayout.SOUTH, emScrollPane);
		
		springLayout.putConstraint(SpringLayout.WEST, btnAddRoom, 0, SpringLayout.WEST, lblRooms);
		springLayout.putConstraint(SpringLayout.NORTH, btnAddRoom, 5, SpringLayout.SOUTH, roomScrollPane);
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
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
}
