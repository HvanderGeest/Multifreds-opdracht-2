package nl.saxion.simulatie.entities;

import nl.saxion.simulatie.Project;

public class EndUser extends Person {
	
	private static final int MAX_TRAVEL_TIME =750;
	
	public EndUser(int number) {
		super(number);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		while(true) {
			justLive();
			try {
				
				//send notice
				project.addUserWithProblem();
				project.invitation.acquire();
				System.out.println(this + "is invited for a meeting");
				
				
				travel();
				project.endUserWaitingForMeeting.release();
				System.out.println(this+"has arrived and is waiting for invitation for the start of the meeting");
				project.invationForMeetingRoom.acquire();
				System.out.println(this + "Going to te meeting room");
				project.inMeetingRoom.release();
				project.backToLiving.acquire();
				System.out.println(this+ "about to leave the room and go back to just living");
				project.leftMeetingRoom.release();
			
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void travel(){
		try {
			System.out.println(this + "On the road again.. ");
			Thread.sleep((int)(Math.random())* MAX_TRAVEL_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String toString() {
		return "Enduser #" + number+ ": ";
	}
	
	@Override
	public void justLive(){
		try {
			Thread.sleep((int)(Math.random() * 6000));
		} catch (InterruptedException e) {}			
	}

}
