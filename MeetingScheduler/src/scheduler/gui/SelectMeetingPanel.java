package scheduler.gui;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;

public class SelectMeetingPanel extends JPanel {
	private Box meetingBox;
	private static final long serialVersionUID = -6953014032294439929L;
	private JScrollPane scrollPane;
	
	public SelectMeetingPanel() {
		setLayout(null);
		
		meetingBox = Box.createVerticalBox();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 94, 84, -92);
		scrollPane.add(meetingBox);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		meetingBox.add(rdbtnNewRadioButton);
		add(scrollPane);
	}

	/**
	 * 
	 */
	

	
}
