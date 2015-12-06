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
				project.devReadyForMeeting.acquire();
				inMeeting(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String toString() {
		return "Software Dev #" + number+": ";
	}

}
