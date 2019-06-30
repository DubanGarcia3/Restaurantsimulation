package uptc.com.entities;

public class Waiter {
	
	int id;
	int timeToServe;
	
	public Waiter(int id, int timeToServe) {
		this.id = id;
		this.timeToServe = timeToServe;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTimeToServe() {
		return timeToServe;
	}
	public void setTimeToServe(int timeToServe) {
		this.timeToServe = timeToServe;
	}

	
}
