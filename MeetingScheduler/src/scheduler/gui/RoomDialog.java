package scheduler.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import scheduler.controller.Controller;
import scheduler.model.Room;
import scheduler.model.RoomListModel;

public class RoomDialog extends JDialog {

	private JTextField txfName;
	private JTextField txfCapacity;
	private RoomListModel roomListModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RoomDialog dialog = new RoomDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RoomDialog() {
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 239, 500, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
						
						Room room = new Room();

						room.setName(txfName.getText());
						room.setCapacity(Integer.parseInt(txfCapacity.getText()));
						
						List <Room> oldRoomList = new ArrayList<Room>(roomListModel.getList());
						oldRoomList.add(room);
						
						roomListModel.setRoomList(oldRoomList);
						Controller.insertRoom(room);

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		{
			JLabel lblRoomCapacity = new JLabel("Room Name");
			lblRoomCapacity.setBounds(45, 87, 97, 16);
			getContentPane().add(lblRoomCapacity);
		}

		{
			txfName = new JTextField();
			txfName.setColumns(10);
			txfName.setBounds(246, 74, 134, 29);
			getContentPane().add(txfName);
		}
		
		JLabel lblRoomName = new JLabel("Room Capacity");
		lblRoomName.setBounds(45, 136, 97, 16);
		getContentPane().add(lblRoomName);
		
		txfCapacity = new JTextField();
		txfCapacity.setColumns(10);
		txfCapacity.setBounds(246, 130, 134, 29);
		getContentPane().add(txfCapacity);
	}
	public void setModel(RoomListModel roomList1){
		this.roomListModel = roomList1;
			
	
	}
}
