package nl.saxion.simulatie.entities;

import nl.saxion.simulatie.Project;

public class Person extends Thread {
	protected Project project = Project.getInstance();
	public void justLive(){
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
