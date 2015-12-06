package nl.saxion.simulatie.entities;

import nl.saxion.simulatie.Project;

public class Person extends Thread {
	protected Project project = Project.getInstance();
	protected int number;
	
	public Person(int number) {
		this.number = number;
	}
	
	public void justLive(){
		try {
			Thread.sleep((int)(Math.random()*10000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "Person " + number;
	}
	
	public void inMeeting(Person p) {
		try {
			System.out.println(p+"In Meeting");
			Thread.sleep(10000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
