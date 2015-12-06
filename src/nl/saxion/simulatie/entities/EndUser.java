package nl.saxion.simulatie.entities;

import nl.saxion.simulatie.Project;

public class EndUser extends Person {
	
	private static final int MAX_TRAVEL_TIME =5000;
	
	public EndUser(int number) {
		super(number);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		while(true) {
			justLive();
			try {
				System.out.println(this + "has found a BUG! PANIC!");
				project.endUserProblemNotice.acquire();
				project.invitation.acquire();
				travel();
				project.enduserReadyForMeeting.acquire();
				inMeeting(this);
				
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
			Thread.sleep((int)(Math.random() * 400000));
		} catch (InterruptedException e) {}			
	}

}
