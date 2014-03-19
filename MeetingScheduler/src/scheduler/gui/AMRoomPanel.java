package scheduler.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;

import scheduler.controller.Controller;
import scheduler.model.Room;
import scheduler.model.RoomListModel;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AMRoomPanel extends JPanel {
	private RoomListModel roomListModel;
	private JTextField txfRoomName;
	private JTextField txfRoomCap;
	private CardLayout cardlayout;
	private JPanel controller;
	
	public AMRoomPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Room Name");
		lblNewLabel.setBounds(50, 74, 100, 16);
		add(lblNewLabel);
		
		JLabel lblRoomCapacity = new JLabel("Room Capacity");
		lblRoomCapacity.setBounds(50, 136, 100, 16);
		add(lblRoomCapacity);
		
		txfRoomName = new JTextField();
		txfRoomName.setBounds(234, 68, 127, 28);
		add(txfRoomName);
		txfRoomName.setColumns(10);
		
		txfRoomCap = new JTextField();
		txfRoomCap.setColumns(10);
		txfRoomCap.setBounds(234, 124, 127, 28);
		add(txfRoomCap);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller == null){
	           		getData();
	            }
				Room room = new Room();

				room.setName(txfRoomName.getText());
				room.setCapacity(Integer.parseInt(txfRoomCap.getText()));
				
				List <Room> oldRoomList = new ArrayList<Room>(roomListModel.getList());
				oldRoomList.add(room);
				
				roomListModel.setRoomList(oldRoomList);
				Controller.insertRoom(room);
				
				clearFields();
            	cardlayout.show(controller,"adminHome");
			}
		});
		btnOk.setBounds(55, 232, 117, 29);
		add(btnOk);
		
		JButton btnCancel = new JButton("BACK");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (controller == null){
	           		getData();
	            }
				
				clearFields();
            	cardlayout.show(controller,"adminHome");
			}
		});
		btnCancel.setBounds(244, 232, 117, 29);
		add(btnCancel);
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
	
	protected void clearFields(){
		txfRoomName.setText(null);
		txfRoomCap.setText(null);	

	}
	
	public void setModel( RoomListModel roomList) {
		  this.roomListModel = roomList;
	}
}
