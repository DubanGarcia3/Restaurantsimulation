package uptc.com.entities;

import java.util.ArrayList;

public class Table extends Thread {

	private int idTable;
	public static int ID_BASE = 1;
	private String tableName;
	private int waitTime;
	private boolean freeTable;
	private ArrayList<Client> listClients;
	private Waiter waiter;

	public Table(String nombre) {
		this.idTable = ID_BASE++;
		this.tableName = nombre;
		this.freeTable = false;
		this.listClients = new ArrayList<Client>();
	}

	@Override
	public void run() {
		super.run();
		try {
			freeTable = true;
			for (int i = 0; i < 30; i++) {
				Calification calification = new Calification();
				this.addToTable();
				this.startEat();
				listClients.removeAll(listClients);
				calification.setPlate(Restaurant.getInstance().getPlates().get((int) (Math.random() *(4 -  0) + 0)));
				Waiter waiter1 = new Waiter(1, 0);
				Waiter waiter2 = new Waiter(2, 0);
				if (waiter1.isService()) {
					waiter2.setTimeToServe(this.getTime());
					this.setWaiter(waiter2);
					waiter2.run();
					
				}else if (waiter2.isService()) {
					waiter1.setTimeToServe(this.getTime());
					this.setWaiter(waiter1);
					waiter1.run();
				}else {
//					wait();
				}
				while (!waiter1.isService() || !waiter2.isService()) {
//					resume();
				}
				Thread.sleep(this.getTime()+100);
				calification.setScore((int) (Math.random() * (5 - 0) + 1) + 0);
				System.out.println("Carta " + tableName +  
						"Plato : " + calification.getPlate().getProductName() + 
						"Calificó con: " + calification.getScore());
				freeTable = false;
				Restaurant.getInstance().addCalification(calification);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addToTable() {
		for (int i = 0; i < (int)(Math.random()*3 + 1); i++) {
			listClients.add(new Client(Integer.parseInt(Restaurant.getInstance().getListTimes().get((int) (Math.random() * 2000) + 1))));
		}
		System.out.println("Mesa # "+idTable+" Cantidad de clientes en la mesa: "+ listClients.size());
	}
	
	public int getTime() {
		double aux = 0;
		for (int i = 0; i < listClients.size(); i++) {
			aux += listClients.get(i).getTime(); 
		}
		return (int) aux;
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
	
	public ArrayList<Client> getListClients() {
		return listClients;
	}

	public void setListClients(ArrayList<Client> listClients) {
		this.listClients = listClients;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}	
	
}