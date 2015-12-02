package nl.saxion.simulatie.entities;

import nl.saxion.simulatie.Project;

public class EndUser extends Person {
	
	private static final int MAX_TRAVEL_TIME =500;
	
	@Override
	public void run() {
		justLive();
		try {
			project.problemNotice.acquire();
			project.invation.acquire();
			travel();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
	
	
	public void travel(){
		try {
			Thread.sleep((int)(Math.random())* MAX_TRAVEL_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
