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
				System.out.println(this+ " has found a bugg");
				project.invitation.acquire();
				System.out.println(this + "is invited for a meeting");
				
				
				travel();
				project.endUserWaitingForMeeting.release();
				project.invationForMeetingRoom.acquire();
				project.endUserInMeetingRoom.release();
				project.backToLiving.acquire();
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
