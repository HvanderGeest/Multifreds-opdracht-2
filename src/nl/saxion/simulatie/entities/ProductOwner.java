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
					if(project.endUserWithAproblem > 0){
						if(project.devsWaitingForMeeting > 0){
							project.meetingHappening =true;
							project.invitation.release(project.endUserWithAproblem);
							
							project.endUserWaitingForMeeting.acquire(project.endUserWithAproblem);
							//all endusers have arived
							project.invationForMeetingRoom.release(project.endUserWithAproblem);
							//one dev requested
							project.softwareDeveloperRequestedForMeetingRoom.release(1);
							project.devReadyForMeeting.release(project.devsWaitingForMeeting);
					
				
							//wait for when everybody is in the meeting room.
							project.endUserInMeetingRoom.acquire(project.endUserWithAproblem);
							project.softwareDeveloperInMeetingRoom.acquire();
						
							//start the meeting
							System.out.println("starting enduser meeting");
							meeting();
							System.out.println("ending enduser meeting");
							//the plus one is for the software developer attending the meeting
							project.backToLiving.release((project.endUserWithAproblem+1));
							project.leftMeetingRoom.acquire((project.endUserWithAproblem+1));
							
							project.devsWaitingForMeeting = 0;
							project.endUserWithAproblem = 0;
							project.meetingHappening = false;
							System.out.println("enduser meeting over and everybody left the room");
							project.mutexEndUserWaitingForInvite.release();
							project.mutexDevsWatingForMeeting.release();
							
							
						} else {
							project.mutexEndUserWaitingForInvite.release();
							project.mutexDevsWatingForMeeting.release();
						}
						
					} else if(project.devsWaitingForMeeting > 3) {
						//dev meeting
						project.meetingHappening = true;
						project.mutexEndUserWaitingForInvite.release();
						project.softwareDeveloperRequestedForMeetingRoom.release(3);
						project.devReadyForMeeting.release(project.devsWaitingForMeeting);
						project.softwareDeveloperInMeetingRoom.acquire(3);
						//start meeting
						System.out.println("dev meeting started");
						meeting();
						System.out.println("dev meeting ended");
						//release the 3 developers
						project.backToLiving.release(3);
						project.leftMeetingRoom.acquire(3);
						//everybody left the meeting
						project.devsWaitingForMeeting = 0;
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
