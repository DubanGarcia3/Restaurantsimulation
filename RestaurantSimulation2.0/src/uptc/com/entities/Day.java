package uptc.com.entities;

public class Day {
	private int id;
    private int workingHours;
    private int totalCustomers;

    public Day(int id, int workingHours, int totalCustomers) {
        this.id = id;
        this.workingHours = workingHours;
        this.totalCustomers = totalCustomers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getTotalConsumers() {
        return totalCustomers;
    }

    public void setTotalConsumers(int totalConsumers) {
        this.totalCustomers = totalConsumers;
    }
}
