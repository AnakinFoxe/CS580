package scheduler.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.SpringLayout;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;

import java.awt.CardLayout;

import javax.swing.JLabel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList list;
	private JScrollPane scrollPane;
	private JLabel roomLabel;
	private JButton finishBtn;
	private JPanel controller;
	private CardLayout cardlayout;
	
	public RoomPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		list = new JList();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		springLayout.putConstraint(SpringLayout.NORTH, list, 109, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, list, 109, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, list, 182, SpringLayout.WEST, this);
		list.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"room A", "room B", "room C", "room D", "room E", "room F"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		scrollPane = new JScrollPane(list);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 68, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 41, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 130, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 147, SpringLayout.WEST, this);
		add(scrollPane);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		roomLabel = new JLabel("Choose a Room");
		springLayout.putConstraint(SpringLayout.NORTH, roomLabel, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, roomLabel, 199, SpringLayout.WEST, this);
		add(roomLabel);
		
		finishBtn = new JButton("finish");
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (controller == null){
            		getData();
            	}
            	cardlayout.show(controller,"home");
			}

		});
		springLayout.putConstraint(SpringLayout.NORTH, finishBtn, -58, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, finishBtn, -136, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, finishBtn, -26, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, finishBtn, -26, SpringLayout.EAST, this);
		add(finishBtn);
	}
	
	protected void getData() {
		// TODO Auto-generated method stub
		controller = (JPanel) this.getParent();
		cardlayout  = (CardLayout) controller.getLayout();
	}

}
