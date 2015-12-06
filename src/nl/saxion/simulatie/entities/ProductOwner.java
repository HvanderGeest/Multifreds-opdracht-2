package nl.saxion.simulatie.entities;

public class ProductOwner extends Person {

	public ProductOwner(int number) {
		super(number);
	}
	
	@Override
	public void run() {
		while (true) {
			justLive();
			while(project.endUserProblemNotice.hasQueuedThreads()) {
				project.endUserProblemNotice.release();
				project.invitation.release();
			}
			
			if ( project.enduserReadyForMeeting.hasQueuedThreads() && project.devReadyForMeeting.hasQueuedThreads()) {
				System.out.println("About to start a Client meeting.");
				project.devReadyForMeeting.release();
				project.enduserReadyForMeeting.release();
				inMeeting(this);
				System.out.println("Meeting Done \n \n");
			} else if (project.devReadyForMeeting.getQueueLength() > 2) {
				System.out.println("About to start a Software Developer meeting.");
				project.devReadyForMeeting.release(3);
				inMeeting(this);
				System.out.println("Meeting Done \n \n");
			}
			
			
		}
	}
	
	public String toString() {
		return "The Product Owner : ";
	}
	
	@Override
	public void justLive(){
		try {
			Thread.sleep((int)(Math.random() * 100));
		} catch (InterruptedException e) {}			
	}
	
}
