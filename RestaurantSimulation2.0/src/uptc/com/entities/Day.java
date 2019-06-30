package uptc.com.entities;

public class Day {
    private int id;
    private int jobHours;
    private int clients;

    public Day(int id, int jobHours, int clients) {
        this.id = id;
        this.clients = jobHours;
        this.clients = clients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getJobHours() {
		return jobHours;
	}

	public void setJobHours(int jobHours) {
		this.jobHours = jobHours;
	}

	public int getClients() {
		return clients;
	}

	public void setClients(int clients) {
		this.clients = clients;
	}
}
