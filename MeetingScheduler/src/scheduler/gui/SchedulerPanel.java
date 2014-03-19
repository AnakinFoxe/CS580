package scheduler.gui;

import java.awt.CardLayout;
import java.util.Date;

import javax.swing.JPanel;

import scheduler.model.DateModel;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;

public class SchedulerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080511466439268522L;
	private CardLayout cardLayout;
	private LoginPanel loginPanel;
	private HomePanel homePanel;
	private MeetingPanel meetingPanel;
	private TimePanel timePanel;
	private RoomPanel roomPanel;
	private MeetingDetailsPanel detailsPanel;
	private AdministratorPanel adminPanel;
	private ProfilePanel profilePanel;
	private EmployeeModel employeeModel;
	private Flag flag1;
	private Flag homeVisible;
	private EmployeeListModel attendeeList;
	private DateModel date;
	private DateModel calenderDateModel;
	private JCalendar calendar;
	private RoomModel room;
	private Flag timePVisible;
	private Flag roomPVisible;
	private Flag fromMeetingDet;
	
	public SchedulerPanel(){
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		homeVisible = new Flag(false);
		
		timePVisible = new Flag(false);
		roomPVisible = new Flag(false);
		fromMeetingDet = new Flag(false);
		employeeModel = new EmployeeModel();
		loginPanel = new LoginPanel();
		loginPanel.setModel(homeVisible);
		
		calendar = new JCalendar();
		//calendar.setModel(homeVisible);
		
		calenderDateModel = new DateModel();
		homePanel = new HomePanel();
		homePanel.setModel(homeVisible, 1);
		homePanel.setModel(calenderDateModel);
		
		loginPanel.setModel(employeeModel);
		
		flag1 = new Flag(false);
		homePanel.setModel(employeeModel);
		homePanel.setModel(flag1, 0);
		
		attendeeList = new EmployeeListModel();
		meetingPanel = new MeetingPanel();
		meetingPanel.setModel(flag1);
		meetingPanel.setModel(attendeeList);
		meetingPanel.setModel(employeeModel);
		meetingPanel.setTFlag(timePVisible);
		
		date = new DateModel();
		timePanel = new TimePanel();
		timePanel.setModel(attendeeList);
		timePanel.setModel(date);
		timePanel.setModel(employeeModel);
		timePanel.setFlag(timePVisible);
		timePanel.setRFlag(roomPVisible);
		timePanel.setDFlag(fromMeetingDet);
		
		room = new RoomModel();
		roomPanel = new RoomPanel();
		roomPanel.setModel(date);
		roomPanel.setModel(attendeeList);
		roomPanel.setModel(employeeModel);
		roomPanel.setModel(homeVisible);
		roomPanel.setFlag(roomPVisible);
		roomPanel.setDFlag(fromMeetingDet);
		
		detailsPanel = new MeetingDetailsPanel();
		detailsPanel.setModel(calenderDateModel);
		detailsPanel.setModel(employeeModel);
		detailsPanel.setModel(attendeeList);
		detailsPanel.setModel(flag1);
		detailsPanel.setTimeModel(date);
		detailsPanel.setModel(room);
		detailsPanel.setFlag(fromMeetingDet);
		
		adminPanel = new AdministratorPanel();
		
		profilePanel = new ProfilePanel();
		profilePanel.setModel(employeeModel);
		
		this.add(loginPanel, "login");
		this.add(homePanel, "home");
		this.add(meetingPanel, "meeting");
		this.add(timePanel, "time");
		this.add(roomPanel, "room");
		this.add(detailsPanel, "meetingDetails");
		this.add(adminPanel, "adminHome");
		this.add(profilePanel, "profile");
	}
}
