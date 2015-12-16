package nl.saxion.simulatie.entities;

public class ProductOwner extends Person {

	public ProductOwner(int number) {
		super(number);
	}
	
	@Override
	public void run() {
		while (true) {
			
			try {
				System.out.println("kan ik al?");
				project.doWork.acquire(); //changes where made, product owner checks if there needs to be a meeting
				project.doWork.drainPermits(); //the statements below only need to be checked once.
					if(project.getEndUserWithAproblem() > 0){
						if(project.getDevsWaitingForMeeting() > 0){
							project.meetingHappening =true;
							project.mutexEndUserWaitingForInvite.acquire();
							final int endUsersWithAProblem = project.getEndUserWithAproblem();
							project.setEndUserWithAproblem(0);
							project.mutexEndUserWaitingForInvite.release();
						
							System.out.println("endUser meeting is happening");
							project.invitation.release(endUsersWithAProblem);
							
							//invitations send
							project.endUserWaitingForMeeting.acquire(endUsersWithAProblem);
							//all endusers have arived
							System.out.println("all end users have arrived");
							project.invitationForMeetingRoom.release(endUsersWithAProblem);
							project.softwareDeveloperRequestedForMeetingRoom.release(1); //one dev requested
							project.mutexDevsWatingForMeeting.acquire();
							project.devReadyForMeeting.release(project.getDevsWaitingForMeeting());
							project.setDevsWaitingForMeeting(0);
							project.mutexDevsWatingForMeeting.release();
							//on dev joined the meeting room, the rest went back to work.
							
							//wait for when everybody is in the meeting room.
							project.inMeetingRoom.acquire(endUsersWithAProblem + 1);
						
							System.out.println("All participants are in the meeting room");
							//start the meeting
							System.out.println("enduser meeting started");
							meeting();
							System.out.println("enduser meeting ended");
							//the plus one is for the software developer attending the meeting
							project.backToLiving.release((endUsersWithAProblem+1));
							project.leftMeetingRoom.acquire((endUsersWithAProblem+1));
							//all people left the meeting room
							
							project.meetingHappening = false;
							System.out.println("enduser meeting over and everybody left the room");
							
							
							
						}
						
					} else if(project.getDevsWaitingForMeeting() > 3) {
						//dev meeting
						System.out.println("dev meeting is happening");
						project.meetingHappening = true;
						project.softwareDeveloperRequestedForMeetingRoom.release(3); //3 software developers requested
						project.mutexDevsWatingForMeeting.acquire();
						project.devReadyForMeeting.release(project.getDevsWaitingForMeeting());
						project.setDevsWaitingForMeeting(0);
						project.mutexDevsWatingForMeeting.release();
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
	/**
	 * simulates the time it takes to hold a meeting.
	 */
	public void meeting(){
		try {
			Thread.sleep((int)(Math.random() * 2000));
		} catch (InterruptedException e) {}			
	}
	
	
}
