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
						project.softwareDeveloperInMeetingRoom.release();
						project.backToLiving.acquire();
						project.leftMeetingRoom.release();
						System.out.println(number+" backToLiving");
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
