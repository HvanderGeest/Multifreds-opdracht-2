package nl.saxion.simulatie.entities;

public class SoftwareDeveloper extends Person {

	public SoftwareDeveloper(int number) {
		super(number);
	}
	
	
	
	@Override
	public void run() {
		while(true) {
			justLive();
			try {
				if(!project.meetingHappening){
					project.addWaitingDeveloper();
					project.devReadyForMeeting.acquire();
					
					if(project.softwareDeveloperRequestedForMeetingRoom.tryAcquire()){
						System.out.println(this+"going to the meeting room for the meeting");
						project.inMeetingRoom.release();
						project.backToLiving.acquire();
						System.out.println(this+"about to leave the room and go back to just living");
						project.leftMeetingRoom.release();
					}	
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String toString() {
		return "Software Dev #" + number+": ";
	}

}
