package scheduler.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SpringLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Canvas;

public class HomePanel extends JPanel {
	private Button createMeetingBtn;
	public HomePanel() {
		setBackground(new Color(0, 0, 204));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		createMeetingBtn = new Button("Create Meeting");
		springLayout.putConstraint(SpringLayout.NORTH, createMeetingBtn, 33, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, createMeetingBtn, 48, SpringLayout.WEST, this);
		createMeetingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(createMeetingBtn);
		
		JLabel lblUsername = new JLabel("username:");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -213, SpringLayout.EAST, this);
		add(lblUsername);
		
		JLabel lblJoe = new JLabel("Joe");
		springLayout.putConstraint(SpringLayout.WEST, lblJoe, 6, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.SOUTH, lblJoe, 0, SpringLayout.SOUTH, lblUsername);
		add(lblJoe);
	}
}
