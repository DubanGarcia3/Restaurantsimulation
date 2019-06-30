package uptc.com.entities;

public class ManagerRestaurant extends Thread {

    private int idTable;
    private String tableName;
    private int waitTime;
    private boolean isOccuppied;
    private int totalOfConsumersOnTable;

    public ManagerRestaurant(int id, String nombre, int totalOfConsumersOnTable) {
        this.idTable = id;
        this.tableName = nombre;
        isOccuppied = false;
        this.totalOfConsumersOnTable = totalOfConsumersOnTable;
    }
   
	
	@Override
    public void run() {
        super.run();
        try {
            isOccuppied = true;
            for (int i = 0; i < totalOfConsumersOnTable; i++) {
                Comsumo consumption = new Comsumo();
                consumption.setProduct(Restaurant.getInstance().getPlates().get((int) (Math.random() *(4 -  0) + 0)));
                Thread.sleep(waitTime);
                if (consumption.getProduct().getRatingProbability() > Math.random()) {
                    consumption.setScore(-1);
                    System.out.println("Carta:" + tableName  + 
                    		"Plato : " + consumption.getProduct().getProductName() +
                    		"No hizo calificación");
                } else {
                    consumption.setScore((int) (Math.random() * (5 - 0) + 1) + 0);
                    System.out.println("Carta " + tableName +  
                    		"Plato : " + consumption.getProduct().getProductName() + 
                    		"Calificó con: " + consumption.getScore());
                }
                isOccuppied = false;
                Restaurant.getInstance().addConsumation(consumption);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	public int getIdTable() {
		return idTable;
	}
	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public boolean isOccuppied() {
		return isOccuppied;
	}
	public void setOccuppied(boolean isOccuppied) {
		this.isOccuppied = isOccuppied;
	}
	public int getTotalOfConsumersOnTable() {
		return totalOfConsumersOnTable;
	}
	public void setTotalOfConsumersOnTable(int totalOfConsumersOnTable) {
		this.totalOfConsumersOnTable = totalOfConsumersOnTable;
	}
	
	
}
