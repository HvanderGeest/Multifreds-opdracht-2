package nl.saxion.simulatie.entities;

public class ProductOwner extends Person {

	public ProductOwner(int number) {
		super(number);
	}
	
	@Override
	public void run() {
		while (true) {
			
			try {
				project.doWork.drainPermits(); //changes where made, product owner checks if there needs to be a meeting
					project.mutexEndUserWaitingForInvite.acquire();
					project.mutexDevsWatingForMeeting.acquire();
					if(project.getEndUserWithAproblem() > 0){
						if(project.getDevsWaitingForMeeting() > 0){
							System.out.println("endUser meeting is happening");
							project.meetingHappening =true;
							project.invitation.release(project.getEndUserWithAproblem());
							
							project.endUserWaitingForMeeting.acquire(project.getEndUserWithAproblem());
							//all endusers have arived
							System.out.println("all end users have arrived");
							project.invationForMeetingRoom.release(project.getEndUserWithAproblem());
							//one dev requested
							project.softwareDeveloperRequestedForMeetingRoom.release(1);
							project.devReadyForMeeting.release(project.getDevsWaitingForMeeting());
					
				
							//wait for when everybody is in the meeting room.
							project.inMeetingRoom.acquire(project.getEndUserWithAproblem() + 1);
							System.out.println("All participants are in the meeting room");
							//start the meeting
							System.out.println("enduser meeting started");
							meeting();
							System.out.println("enduser meeting ended");
							//the plus one is for the software developer attending the meeting
							project.backToLiving.release((project.getEndUserWithAproblem()+1));
							
							project.leftMeetingRoom.acquire((project.getEndUserWithAproblem()+1));
							project.setDevsWaitingForMeeting(0);
							project.setEndUserWithAproblem(0);
							project.meetingHappening = false;
							System.out.println("enduser meeting over and everybody left the room");
							project.mutexEndUserWaitingForInvite.release();
							project.mutexDevsWatingForMeeting.release();
							
							
						} else {
							project.mutexEndUserWaitingForInvite.release();
							project.mutexDevsWatingForMeeting.release();
						}
						
					} else if(project.getDevsWaitingForMeeting() > 3) {
						//dev meeting
						System.out.println("dev meeting is happening");
						project.meetingHappening = true;
						project.mutexEndUserWaitingForInvite.release();
						project.softwareDeveloperRequestedForMeetingRoom.release(3);
						project.devReadyForMeeting.release(project.getDevsWaitingForMeeting());
						project.inMeetingRoom.acquire(3);
						System.out.println("All devs are in the meeting room");
						//start meeting
						System.out.println("dev meeting started");
						meeting();
						System.out.println("dev meeting ended");
						//release the 3 developers
						project.backToLiving.release(3);
						project.leftMeetingRoom.acquire(3);
						//everybody left the meeting
						project.setDevsWaitingForMeeting(0);
						project.meetingHappening = false;
						System.out.println("dev meeting over and everybody left the room");
						project.mutexDevsWatingForMeeting.release();
						
						
						
					} else {
						project.mutexEndUserWaitingForInvite.release();
						project.mutexDevsWatingForMeeting.release();
					}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
	
	public String toString() {
		return "The Product Owner : ";
	}
	
	public void meeting(){
		try {
			Thread.sleep((int)(Math.random() * 2000));
		} catch (InterruptedException e) {}			
	}
	
	@Override
	public void justLive(){
		try {
			Thread.sleep((int)(Math.random() * 100));
		} catch (InterruptedException e) {}			
	}
	
}
