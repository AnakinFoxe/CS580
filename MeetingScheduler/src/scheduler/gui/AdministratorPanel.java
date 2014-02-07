package scheduler.gui;

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
	private JButton btnAdd;
	private JButton btnModify;
	private JButton btnDelete;
	private JList emList;
	private JList roomList;
	private GroupLayout groupLayout;
	
	public AdministratorPanel() {
		lblEmployees = new JLabel("Employees");
		lblRooms = new JLabel("Rooms");
		emScrollPane = new JScrollPane();
		roomScrollPane = new JScrollPane();
		btnAdd = new JButton("Add");
		btnModify = new JButton("Modify");
		btnDelete = new JButton("Delete");
		emList = new JList();
		roomList = new JList();
		groupLayout = new GroupLayout(this);
		
		SetUpPanel();
	}
	private void SetUpPanel(){

		emScrollPane.setViewportView(emList);
		roomScrollPane.setRowHeaderView(roomList);
		this.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblEmployees, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addGap(91)
								.addComponent(lblRooms))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(emScrollPane, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(roomScrollPane, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnModify)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))))
						.addGap(41))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(11)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblEmployees, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(2)
										.addComponent(lblRooms, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
								.addGap(6)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(emScrollPane)
									.addComponent(roomScrollPane, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(67)
								.addComponent(btnAdd)
								.addGap(6)
								.addComponent(btnModify)
								.addGap(6)
								.addComponent(btnDelete)))
						.addContainerGap(26, Short.MAX_VALUE))
			);
	}
}
