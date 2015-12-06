package nl.saxion.simulatie;

import java.util.concurrent.Semaphore;


import nl.saxion.simulatie.entities.EndUser;
import nl.saxion.simulatie.entities.ProductOwner;
import nl.saxion.simulatie.entities.SoftwareDeveloper;

public class Project {
	
	private static Project p;
	private static int NR_OF_ENDUSERS = 8;
	private static int NR_of_SOFTWAREDEVS = 5;
	
	private EndUser[] endusers;
	private SoftwareDeveloper[] devs;
	private ProductOwner po;
	
	public Semaphore endUserProblemNotice = new Semaphore(0, true);
	public Semaphore invitation = new Semaphore(1, true);
	public Semaphore enduserReadyForMeeting = new Semaphore(0, true);
	public Semaphore devReadyForMeeting = new Semaphore(0, true);
	
	
	public Project() {
		p = this;
		System.out.println("=== Starting Simulation ===");
		
		endusers = new EndUser[NR_OF_ENDUSERS];
		devs = new SoftwareDeveloper[NR_of_SOFTWAREDEVS];
		
		
		
		for(int i = 0; i< NR_of_SOFTWAREDEVS; i++){
			devs[i] = new SoftwareDeveloper (i);
			devs[i].start();
		}
		
		for(int e = 0; e < NR_OF_ENDUSERS; e++) {
			endusers[e] = new EndUser(e);
			endusers[e].start();
		}
		
		
		po = new ProductOwner(1);
		po.start();
		
	}
	
	
	public static void main(String[] args) {
		new Project();

	}
	
	public static Project getInstance() {
		return p;
	}

}
