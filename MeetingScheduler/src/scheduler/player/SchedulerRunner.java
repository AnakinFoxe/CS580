package scheduler.player;

import scheduler.gui.SchedulerFrame;

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
