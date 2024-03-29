package uptc.com.entities;

public class Client extends Thread{
	
	private int id;
	public static int ID_BASE = 0;
	private int time;
	private boolean isEating;

	public Client(int time) {
		this.id = ID_BASE++;
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Cliente numero" + id + ", con tiempo " + time;
	}

	@Override
	public void run() {
		super.run();
		try {
			isEating = true;
			while (isEating) {
				System.out.println("comiendo...");
				Thread.sleep(time);
				break;
			}
			isEating = false;	
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	
	public boolean isEating() {
		return this.isEating;
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}	
}