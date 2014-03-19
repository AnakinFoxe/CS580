package scheduler.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import scheduler.controller.Controller;
import scheduler.model.Room;

public class RoomDialog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
						room.setId(Integer.parseInt(textField.getText().toString()));
						room.setName(textField_1.getText().toString());
						room.setCapacity(Integer.parseInt(textField.getText().toString()));
						Controller Controller = new Controller();
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
			JLabel lblNewLabel = new JLabel("Room ID");
			lblNewLabel.setBounds(45, 40, 97, 16);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblRoomCapacity = new JLabel("Room Name");
			lblRoomCapacity.setBounds(45, 87, 97, 16);
			getContentPane().add(lblRoomCapacity);
		}
		{
			textField = new JTextField();
			textField.setBounds(246, 34, 134, 29);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(246, 74, 134, 29);
			getContentPane().add(textField_1);
		}
		
		JLabel lblRoomName = new JLabel("Room Capacity");
		lblRoomName.setBounds(45, 136, 97, 16);
		getContentPane().add(lblRoomName);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(246, 130, 134, 29);
		getContentPane().add(textField_2);
	}
}
