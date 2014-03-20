package scheduler.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;

import scheduler.controller.Controller;
import scheduler.model.Flag;
import scheduler.model.Room;
import scheduler.model.RoomListModel;
import scheduler.model.RoomModel;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class AMRoomPanel extends JPanel {
	private RoomListModel roomListModel;
	private JTextField txfRoomName;
	private JTextField txfRoomCap;
	private CardLayout cardlayout;
	private JPanel controller;
	private RoomModel roomModel;
	private Flag isAdminVisible;
	private JLabel roomNameError;
	private JLabel roomCapError;
	
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
				boolean isFieldsSet = checkFields();
				if(isFieldsSet){
					room.setName(txfRoomName.getText());
					room.setCapacity(Integer.parseInt(txfRoomCap.getText()));
					
					//List <Room> oldRoomList = new ArrayList<Room>(roomListModel.getList());
					//oldRoomList.add(room);
					
					//roomListModel.setRoomList(oldRoomList);
					if(roomModel.getRoom() != null){
						Room newRoom = roomModel.getRoom();
						newRoom.setName(room.getName());
						newRoom.setCapacity(room.getCapacity());
						Controller.updateRoom(newRoom);
					}else{
						Controller.insertRoom(room);
					}
					clearFields();
					isAdminVisible.setFlag(true);
	            	cardlayout.show(controller,"adminHome");
				}
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
				isAdminVisible.setFlag(true);
            	cardlayout.show(controller,"adminHome");
			}
		});
		btnCancel.setBounds(244, 232, 117, 29);
		add(btnCancel);
		
		roomNameError = new JLabel("Must enter a room name");
		roomNameError.setForeground(Color.RED);
		roomNameError.setBounds(234, 48, 154, 14);
		add(roomNameError);
		roomNameError.setVisible(false);
		
		roomCapError = new JLabel("Must enter a capacity");
		roomCapError.setForeground(Color.RED);
		roomCapError.setBounds(234, 107, 127, 14);
		add(roomCapError);
		roomCapError.setVisible(false);
	}
	
	protected boolean checkFields() {
		String roomName = txfRoomName.getText().trim();
		String rooCap = txfRoomCap.getText().trim();
		boolean fieldSet = true;
		if(roomName.isEmpty()){
			fieldSet = false;
			roomNameError.setVisible(true);
		}else{
			roomNameError.setVisible(true);
		}
		
		if(rooCap.isEmpty()){
			fieldSet = false;
			roomCapError.setVisible(true);
		}else{
			roomCapError.setVisible(false);
		}
		return fieldSet;
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
	
	public void setModel(RoomModel model){
		this.roomModel = model;
		roomModel.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
				if(roomModel.getRoom()!=null){
					Room room = roomModel.getRoom();
					txfRoomName.setText(room.getName());
					txfRoomCap.setText(room.getCapacity().toString());
				}
			}
		});
	}
	
	public void setModel(Flag model){
		this.isAdminVisible = model;
	}
}
