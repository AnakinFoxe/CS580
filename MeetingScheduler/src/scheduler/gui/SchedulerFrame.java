package scheduler.gui;
import java.awt.Color;

import javax.swing.JFrame;



public class SchedulerFrame extends JFrame {
	/**
	 * 
	 */
	int width = 1000;
	int height = 700;
	private static final long serialVersionUID = 1L;
	
	SchedulerPanel currentPanel;
	public SchedulerFrame() {
		currentPanel =  new SchedulerPanel();
		
		setUpFrame();
	}
	private void setUpFrame() {
		// TODO Auto-generated method stub
		this.setContentPane(currentPanel);
		
	}

	public void start(){
		this.setSize(width, height);
		this.setVisible(true);
	}
}
