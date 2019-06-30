package uptc.com.entities;

import java.util.ArrayList;

public class Table extends Thread {

    private int idTable;
    private String tableName;
    private int waitTime;
    private boolean isOccuppied;
    private int totalOfConsumersOnTable;
    private ArrayList<Client> listClients;

    public Table(int id, String nombre, int totalOfConsumersOnTable) {
        this.idTable = id;
        this.tableName = nombre;
        isOccuppied = false;
        this.totalOfConsumersOnTable = totalOfConsumersOnTable;
        listClients = new ArrayList<Client>();
    }
   
	@Override
    public void run() {
        super.run();
        try {
            isOccuppied = true;
            for (int i = 0; i < totalOfConsumersOnTable; i++) {
            	Calification consumption = new Calification();
            	Waiter waiter = new Waiter(1, 2);
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
	
	public void addToTable() {
		for (int i = 0; i < (int)(Math.random()*4); i++) {
			listClients.add(new Client((int)Math.random()*10));
		}
		System.out.println(listClients.size());
	}
	
	public void remove() {
		for (int i = 0; i < listClients.size(); i++) { 
			if (listClients.get(i).isEating() == false) {
				listClients.remove(i);
			}
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

	public ArrayList<Client> getListClients() {
		return listClients;
	}

	public void setListClients(ArrayList<Client> listClients) {
		this.listClients = listClients;
	}	
	
	
}