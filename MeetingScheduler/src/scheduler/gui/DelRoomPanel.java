package scheduler.gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import scheduler.controller.Controller;
import scheduler.model.Flag;
import scheduler.model.Room;
import scheduler.model.RoomModel;

public class DelRoomPanel extends JPanel {
	
	private CardLayout cardlayout;
	private JPanel controller;
	private Flag adminVisible;
	private RoomModel roomModel;

	public DelRoomPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Are you sure you want to delete it?");
		lblNewLabel.setBounds(49, 30, 162, 33);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller == null){
            		getData();
				}
				Room room = roomModel.getRoom();
				Controller.deleteRoom(room);
				adminVisible.setFlag(true);
				cardlayout.show(controller, "adminHome");
			}
		});
		btnNewButton.setBounds(6, 101, 117, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller == null){
            		getData();
				}
				adminVisible.setFlag(true);
				cardlayout.show(controller, "adminHome");
			}
		});
		btnNewButton_1.setBounds(127, 101, 117, 29);
		add(btnNewButton_1);

	}
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	
	public void setModel(Flag model){
		this.adminVisible = model;
	}
	
	public void setModel(RoomModel model){
		this.roomModel = model;
	}
}
