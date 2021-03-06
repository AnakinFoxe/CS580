package scheduler.gui;

import java.awt.CardLayout;
import java.util.Date;

import javax.swing.JPanel;

import scheduler.model.DateModel;
import scheduler.model.EmployeeListModel;
import scheduler.model.EmployeeModel;
import scheduler.model.Flag;
import scheduler.model.RoomListModel;
import scheduler.model.MeetingModel;
import scheduler.model.RoomModel;



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

	private EmployeeListModel employeeList;
	private RoomListModel roomListModel;
	private AMRoomPanel adminRoomPanel;
	private AMEmpPanel adminEmpPanel;

	private RoomModel room;
	private Flag timePVisible;
	private Flag roomPVisible;
	private Flag fromMeetingDet;

	private DelEmpPanel delempPanel;
	private DelRoomPanel delroomPanel;
	private EmployeeModel employeeAdmin;

	private MeetingModel meetingModel;
	private Flag meetingDetVisible;
	private Flag adminFlag;
	private RoomModel adminRoom;


	
	public SchedulerPanel(){
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		homeVisible = new Flag(false);
		meetingDetVisible = new Flag(false);
		
		roomListModel = new RoomListModel();
		employeeList = new EmployeeListModel();

		meetingModel = new MeetingModel();

		timePVisible = new Flag(false);
		roomPVisible = new Flag(false);
		fromMeetingDet = new Flag(false);
		employeeModel = new EmployeeModel();
		loginPanel = new LoginPanel();
		loginPanel.setModel(homeVisible);
		loginPanel.setModel(roomListModel);
		loginPanel.setModel(employeeList);
		employeeAdmin = new EmployeeModel();
		//calendar.setModel(homeVisible);

		adminFlag = new Flag(false);

		
		
		calenderDateModel = new DateModel();
		homePanel = new HomePanel();
		homePanel.setModel(homeVisible, 1);
		homePanel.setModel(calenderDateModel);
		homePanel.setMeetDetFlag(meetingDetVisible);
		
		loginPanel.setModel(employeeModel);
		loginPanel.setAdminFlag(adminFlag);
		
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
		roomPanel.setModel(meetingModel);
		
		detailsPanel = new MeetingDetailsPanel();
		detailsPanel.setModel(calenderDateModel);
		detailsPanel.setModel(employeeModel);
		detailsPanel.setModel(attendeeList);
		detailsPanel.setModel(flag1);
		detailsPanel.setTimeModel(date);
		detailsPanel.setModel(room);
		detailsPanel.setFlag(fromMeetingDet);
		detailsPanel.setModel(meetingModel);
		detailsPanel.setHFlag(homeVisible);
		detailsPanel.setMeetFlag(meetingDetVisible);
		
		adminRoom = new RoomModel();
		adminPanel = new AdministratorPanel();
		adminPanel.setModel(employeeList);
		adminPanel.setModel(roomListModel);
		adminPanel.setModel(employeeAdmin);
		adminPanel.setModel(adminFlag);
		adminPanel.setModel(adminRoom);

		
		adminRoomPanel = new AMRoomPanel();
		adminRoomPanel.setModel(roomListModel);
		adminRoomPanel.setModel(adminRoom);
		adminRoomPanel.setModel(adminFlag);
		
		delroomPanel = new DelRoomPanel();
		delroomPanel.setModel(adminRoom);
		delroomPanel.setModel(adminFlag);
		
		adminEmpPanel = new AMEmpPanel();
		adminEmpPanel.setModel(employeeList);
		adminEmpPanel.setModel(adminFlag);
		adminEmpPanel.setModel(employeeAdmin);
		
		profilePanel = new ProfilePanel();
		profilePanel.setModel(employeeModel);
		
		delempPanel = new DelEmpPanel();
		delempPanel.setModel(employeeAdmin);
		delempPanel.setModel(adminFlag);
		
		this.add(loginPanel, "login");
		this.add(homePanel, "home");
		this.add(meetingPanel, "meeting");
		this.add(timePanel, "time");
		this.add(roomPanel, "room");
		this.add(detailsPanel, "meetingDetails");
		this.add(adminPanel, "adminHome");
		this.add(profilePanel, "profile");
		this.add(adminRoomPanel,"roomPanel");
		this.add(adminEmpPanel, "empPanel");
		this.add(delempPanel, "delempPanel");
		this.add(delroomPanel, "delroomPanel");
		
		

	}
}
