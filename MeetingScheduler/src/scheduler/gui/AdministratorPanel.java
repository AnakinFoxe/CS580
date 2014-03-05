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
		springLayout.putConstraint(SpringLayout.NORTH, btnAddEmp, 231, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, roomScrollPane, 6, SpringLayout.SOUTH, lblRooms);
		springLayout.putConstraint(SpringLayout.SOUTH, roomScrollPane, -6, SpringLayout.NORTH, btnAddRoom);
		springLayout.putConstraint(SpringLayout.WEST, btnAddRoom, 106, SpringLayout.EAST, btnAddEmp);
		springLayout.putConstraint(SpringLayout.EAST, btnAddRoom, 0, SpringLayout.EAST, btnModifyRoom);
		springLayout.putConstraint(SpringLayout.NORTH, emScrollPane, 6, SpringLayout.SOUTH, lblEmployees);
		springLayout.putConstraint(SpringLayout.SOUTH, emScrollPane, -6, SpringLayout.NORTH, btnAddEmp);
		springLayout.putConstraint(SpringLayout.EAST, btnAddEmp, 0, SpringLayout.EAST, btnModifyEmp);
		springLayout.putConstraint(SpringLayout.WEST, roomScrollPane, 76, SpringLayout.EAST, emScrollPane);
		springLayout.putConstraint(SpringLayout.EAST, roomScrollPane, -65, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, emScrollPane, 166, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRooms, 168, SpringLayout.EAST, lblEmployees);
		springLayout.putConstraint(SpringLayout.WEST, lblEmployees, 23, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteRoom, 106, SpringLayout.EAST, btnDeleteEmp);
		springLayout.putConstraint(SpringLayout.WEST, btnModifyRoom, 106, SpringLayout.EAST, btnModifyEmp);
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 0, SpringLayout.NORTH, lblEmployees);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteEmp, 23, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, btnModifyEmp, 23, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, btnAddEmp, 23, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblEmployees, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteEmp, 0, SpringLayout.NORTH, btnDeleteRoom);
		springLayout.putConstraint(SpringLayout.NORTH, btnModifyEmp, 0, SpringLayout.NORTH, btnModifyRoom);
		springLayout.putConstraint(SpringLayout.SOUTH, btnModifyRoom, -65, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteRoom, 15, SpringLayout.SOUTH, btnModifyRoom);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddRoom, -16, SpringLayout.NORTH, btnModifyRoom);
		springLayout.putConstraint(SpringLayout.WEST, emScrollPane, 23, SpringLayout.WEST, this);
		setLayout(springLayout);
		
		
	
		
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
}
