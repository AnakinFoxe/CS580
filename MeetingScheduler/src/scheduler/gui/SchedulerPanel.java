package scheduler.gui;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class SchedulerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080511466439268522L;
	private CardLayout cardLayout;
	private LoginPanel loginPanel;
	private HomePanel homePanel;
	
	public SchedulerPanel(){
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		loginPanel = new LoginPanel();
		homePanel = new HomePanel();
		
		this.add(loginPanel, "login");
		this.add(homePanel, "home");
	}
}
