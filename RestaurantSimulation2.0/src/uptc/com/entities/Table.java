package uptc.com.entities;

import java.util.ArrayList;

public class Table extends Thread {

	private int idTable;
	public static int ID_BASE = 1;
	private String tableName;
	private int waitTime;
	private boolean freeTable;
	private int clientsEating;
	private ArrayList<Client> listClients;

	public Table(int id, String nombre, int clientsEating) {
		this.idTable = ID_BASE++;
		this.tableName = nombre;
		this.freeTable = false;
		this.clientsEating = clientsEating;
		this.listClients = new ArrayList<Client>();
	}

	@Override
	public void run() {
		super.run();
		try {
			freeTable = true;
			for (int i = 0; i < clientsEating; i++) {
				Calification consumption = new Calification();
				Waiter waiter = new Waiter(1, 2);
				this.addToTable();
				this.startEat();
				listClients.removeAll(listClients);
				consumption.setPlate(Restaurant.getInstance().getPlates().get((int) (Math.random() *(4 -  0) + 0)));
				Thread.sleep(waitTime);
				consumption.setScore((int) (Math.random() * (5 - 0) + 1) + 0);
				System.out.println("Carta " + tableName +  
						"Plato : " + consumption.getPlate().getProductName() + 
						"Calificó con: " + consumption.getScore());
				freeTable = false;
				Restaurant.getInstance().addCalification(consumption);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addToTable() {
		for (int i = 0; i < (int)(Math.random()*3 + 1); i++) {
			listClients.add(new Client((int)Math.random()*10));
		}
		System.out.println("Mesa # "+idTable+" Cantidad de clientes en la mesa: "+ listClients.size());
	}

	public void startEat() {
		for (int i = 0; i < listClients.size(); i++) {
			listClients.get(i).start();
			System.out.println(listClients.get(i).toString());
		}
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
		return freeTable;
	}
	public void setOccuppied(boolean isOccuppied) {
		this.freeTable = isOccuppied;
	}
	public int getTotalOfConsumersOnTable() {
		return clientsEating;
	}
	public void setTotalOfConsumersOnTable(int totalOfConsumersOnTable) {
		this.clientsEating = totalOfConsumersOnTable;
	}

	public ArrayList<Client> getListClients() {
		return listClients;
	}

	public void setListClients(ArrayList<Client> listClients) {
		this.listClients = listClients;
	}	
}