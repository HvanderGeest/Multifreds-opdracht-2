package nl.saxion.simulatie.entities;

public class Person extends Thread {
	
	public void justLive(){
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
