package scheduler.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3029601078739531195L;
	private CardLayout cardlayout;
	private JPanel controller;
	private JButton btnContinue;
	public TimePanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Select a Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 27, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 186, SpringLayout.WEST, this);
		add(lblNewLabel);
		
		JLabel lblPleaseSelectOne = new JLabel("Please select one of the available time slots:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPleaseSelectOne, 64, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblPleaseSelectOne, 10, SpringLayout.WEST, this);
		add(lblPleaseSelectOne);
		
		JRadioButton rdbtnPm = new JRadioButton("1/27/2014, 10:00 AM");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnPm, 6, SpringLayout.SOUTH, lblPleaseSelectOne);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPm, 44, SpringLayout.WEST, this);
		add(rdbtnPm);
		
		JRadioButton rdbtnPm_2 = new JRadioButton("1/27/2014, 12:00 PM");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPm_2, 0, SpringLayout.WEST, rdbtnPm);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnPm_2, 0, SpringLayout.EAST, rdbtnPm);
		add(rdbtnPm_2);
		
		JRadioButton rdbtnPm_3 = new JRadioButton("1/27/2014, 03:00 PM");
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnPm_2, -6, SpringLayout.NORTH, rdbtnPm_3);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPm_3, 0, SpringLayout.WEST, rdbtnPm);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnPm_3, 0, SpringLayout.EAST, rdbtnPm);
		add(rdbtnPm_3);
		
		JRadioButton rdbtnPm_4 = new JRadioButton("1/27/2014, 05:00 PM");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnPm_4, 173, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnPm_3, -6, SpringLayout.NORTH, rdbtnPm_4);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPm_4, 0, SpringLayout.WEST, rdbtnPm);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnPm_4, 0, SpringLayout.EAST, rdbtnPm);
		add(rdbtnPm_4);
		
		JRadioButton rdbtnPm_1 = new JRadioButton("1/27/2014, 07:00 PM");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnPm_1, 6, SpringLayout.SOUTH, rdbtnPm_4);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPm_1, 0, SpringLayout.WEST, rdbtnPm);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnPm_1, 0, SpringLayout.EAST, rdbtnPm);
		add(rdbtnPm_1);
		
		btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"room");
			}

		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnContinue, -23, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnContinue, -35, SpringLayout.EAST, this);
		add(btnContinue);
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}
}
