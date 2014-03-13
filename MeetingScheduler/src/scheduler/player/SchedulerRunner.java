package scheduler.player;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import scheduler.gui.SchedulerFrame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


class SchedulerRunner {

	public static void main(String[] args) {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		
		// TODO Auto-generated method stub
		SchedulerFrame schedulerFrame = new SchedulerFrame();
		
		schedulerFrame.start();
		
		
	}

}
