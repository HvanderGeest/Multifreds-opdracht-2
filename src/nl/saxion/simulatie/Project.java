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

	public Semaphore invitation = new Semaphore(0, true);
	public Semaphore enduserInMeeting = new Semaphore(0, true);
	public Semaphore devReadyForMeeting = new Semaphore(0, true);
	public Semaphore doWork = new Semaphore(0);
	
	public Semaphore endUserWaitingForMeeting = new Semaphore(0, true);
	public Semaphore invationForMeetingRoom = new Semaphore(0, true);
	public Semaphore inMeetingRoom = new Semaphore(0, true);
	public Semaphore backToLiving = new Semaphore(0, true);
	public Semaphore softwareDeveloperRequestedForMeetingRoom = new Semaphore(0, true);
	public Semaphore leftMeetingRoom = new Semaphore(0, true);
	
	public boolean meetingHappening = false;
	private int devsWaitingForMeeting = 0;
	private int endUserWithAproblem = 0;
	public Semaphore mutexDevsWatingForMeeting = new Semaphore(1, true);
	public Semaphore mutexEndUserWaitingForInvite = new Semaphore(1, true);

	public Project() {
		p = this;
		System.out.println("=== Starting Simulation ===");

		endusers = new EndUser[NR_OF_ENDUSERS];
		devs = new SoftwareDeveloper[NR_of_SOFTWAREDEVS];

		for (int i = 0; i < NR_of_SOFTWAREDEVS; i++) {
			devs[i] = new SoftwareDeveloper(i);
			devs[i].start();
		}

		for (int e = 0; e < NR_OF_ENDUSERS; e++) {
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

	public void addWaitingDeveloper() {
		try {
			mutexDevsWatingForMeeting.acquire();
			devsWaitingForMeeting++;
			mutexDevsWatingForMeeting.release();
			doWork.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addUserWithProblem() {
		try {
			mutexEndUserWaitingForInvite.acquire();
			endUserWithAproblem++;
			mutexEndUserWaitingForInvite.release();
			doWork.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the devsWaitingForMeeting
	 */
	public int getDevsWaitingForMeeting() {
		return devsWaitingForMeeting;
	}

	/**
	 * @param devsWaitingForMeeting the devsWaitingForMeeting to set
	 */
	public void setDevsWaitingForMeeting(int devsWaitingForMeeting) {
		this.devsWaitingForMeeting = devsWaitingForMeeting;
	}

	/**
	 * @return the endUserWithAproblem
	 */
	public int getEndUserWithAproblem() {
		return endUserWithAproblem;
	}

	/**
	 * @param endUserWithAproblem the endUserWithAproblem to set
	 */
	public void setEndUserWithAproblem(int endUserWithAproblem) {
		this.endUserWithAproblem = endUserWithAproblem;
	}

}
