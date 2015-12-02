package nl.saxion.simulatie;

import java.util.concurrent.Semaphore;

public class Project {
	
	private static Project p;
	
	public Semaphore problemNotice = new Semaphore(1, true);
	public Semaphore invation = new Semaphore(0, true);

	
	
	public Project() {
		p = this;
		System.out.println("go");
	}
	
	
	public static void main(String[] args) {
		new Project();

	}
	
	public static Project getInstance() {
		return p;
	}

}
