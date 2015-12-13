package nl.saxion.simulatie.entities;

import nl.saxion.simulatie.Project;

public abstract class Person extends Thread {
	protected Project project = Project.getInstance();
	protected int number;
	
	public Person(int number) {
		this.number = number;
	}
	/**
	 * simulates the time a person needs to live without a meeting
	 */
	public void justLive(){
		try {
			Thread.sleep((int)(Math.random()*750));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Person " + number;
	}
	
	

}
