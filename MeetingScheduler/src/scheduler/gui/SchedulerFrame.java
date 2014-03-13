package scheduler.gui;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	System.exit(0);
		    }
		});
		
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
