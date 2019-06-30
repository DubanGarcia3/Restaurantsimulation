package uptc.com.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import persistence.FileManager;

public class Restaurant {

	private List<Day> daysToSimulate;
	private List<Plate> listOfPlates;
	private List<Calification> califications = new ArrayList<>();
	private List<String> listData = new ArrayList<String>();
	private List<String> listDataClients = new ArrayList<String>();

	private static Restaurant restaurant = new Restaurant();

	public static Restaurant getInstance() {
		return restaurant;
	}

	private Restaurant() {
	}

	public void start(int hoursToSimulate) {
		manageFile();
		addPlates();
		generateDays(hoursToSimulate);
		startSimulation();
		
	}

	private void generateFinalReport() {
		System.out.println();
		int totalPlate1 = 0, totalPlate2 = 0, totalPlate3 = 0, totalPlate4 = 0;
		double sumOfRatingsPlate1 = 0, sumOfRatingsPlate2 = 0, sumOfRatingPlate3 = 0, sumOfRatingPlate4 = 0;
		int totalNumberOfRatingsPlate1 = 0, totalNumberOfRatingsPlate2 = 0, totalNumberOfRatingsPlate3 = 0,
				totalNumberOfRatingsPlate4 = 0;
		for (Calification calification : califications) {
			try {
			switch (calification.getPlate().getIdProduct()) {
			case 0:
				totalPlate1++;
				if (calification.getScore() != -1) {
					sumOfRatingsPlate1 += calification.getScore();
					totalNumberOfRatingsPlate1++;
				}
				break;
			case 1:
				totalPlate2++;
				if (calification.getScore() != -1) {
					sumOfRatingsPlate2 += calification.getScore();
					totalNumberOfRatingsPlate2++;
				}
				break;
			case 2:
				totalPlate3++;
				if (calification.getScore() != -1) {
					sumOfRatingPlate3 += calification.getScore();
					totalNumberOfRatingsPlate3++;
				}
				break;
			case 3:
				totalPlate4++;
				if (calification.getScore() != -1) {
					sumOfRatingPlate4 += calification.getScore();
					totalNumberOfRatingsPlate4++;
				}
				break;
			}
		} catch (Exception e) {
		}

		}
		System.out.println("Bandeja Paisa: " + totalPlate1 + "\t Calificaci�n: " + sumOfRatingsPlate1 / totalNumberOfRatingsPlate1);
		System.out.println("Arroz con Pollo: " + totalPlate4 + "\t Calificaci�n:  " + sumOfRatingPlate4 / totalNumberOfRatingsPlate4);
		System.out.println("Paella a la Valenciana: " + totalPlate3 + "\t Calificaci�n:  " + sumOfRatingPlate3 / totalNumberOfRatingsPlate3);
		System.out.println("Cuchuco de Trigo con Espinazo : " + totalPlate2 + "\t Calificaci�n: " + sumOfRatingsPlate2 / totalNumberOfRatingsPlate2);
	}
	
	public void addCalification(Calification calification) {
		califications.add(calification);
	}

	private void addPlates() {
		listOfPlates = new ArrayList<>();
		listOfPlates.add(new Plate(0, "Bandeja Paisa", Math.random()));
		listOfPlates.add(new Plate(2, "Paella a la Valenciana", Math.random()));
		listOfPlates.add(new Plate(3, "Arroz con Pollo", Math.random()));
		listOfPlates.add(new Plate(1, "Cuchuco de Trigo con Espinazo", Math.random()));
	}

	private void startSimulation() {
		System.out.println("Dias simulados: " + daysToSimulate.size());
		System.out.println();

		for (Day day : daysToSimulate) {
			System.out.println("Dia #: " + day.getId() + "");
			System.out.println("Horas trabajadas" + day.getWorkingHours());
			System.out.println("Clientes en el dia: " + day.getTotalConsumers());
			Table table1 = new Table("Mesa 1 ", day.getTotalConsumers() / 2);
			Table table2 = new Table("Mesa 2 ", (int) Math.ceil(day.getTotalConsumers() / 2));
			Table table3 = new Table("Mesa 3 ", (int) Math.ceil(day.getTotalConsumers() / 2));
			Table table4 = new Table("Mesa 4 ", (int) Math.ceil(day.getTotalConsumers() / 2));
			Table table5 = new Table("Mesa 5 ", (int) Math.ceil(day.getTotalConsumers() / 2));

			table1.setWaitTime((100));
			table2.setWaitTime((100));
			table3.setWaitTime((100));
			table4.setWaitTime((100));
			table5.setWaitTime((100));
			table1.start();
			table2.start();
			table3.start();
			table4.start();
			table5.start();
			while (table1.isAlive() || table2.isAlive() || table5.isAlive() || table4.isAlive() || table3.isAlive()) {
			}
		}
		generateFinalReport();
	}

	private void generateDays(int hoursToSimulate) {
		daysToSimulate = new ArrayList<>();
		int auxHours = 0;
		int auxDay = 1;
		List<Double> hours = generateHours();
		List<Double> totalOfConsumers = generateClients();
		while (auxHours < hoursToSimulate) {
			daysToSimulate.add(new Day(auxDay, (int) (Math.round(hours.get(auxDay))), (int) (Math.round(totalOfConsumers.get(auxDay)))));
			auxHours += (int) (Math.round(hours.get(auxDay)));
			auxDay++;
		}
	}
	
	public void manageFile() {
		try {
			List<String> file = FileManager.readFileHour();
			for (int i = 0; i < file.size(); i++) {
				listData.add(createHour(FileManager.splitLine(file.get(i), ",")));
			}
			
			List<String> fileTwo = FileManager.readFileClients();
			for (int i = 0; i < fileTwo.size(); i++) {
				listDataClients.add(createClient(FileManager.splitLine(fileTwo.get(i), ",")));
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public String createHour(String []in) {
		return (in[0]);
	}
	
	private ArrayList<Double> generateHours() {		
		ArrayList<Double> hours = new ArrayList<Double>();
		for (int i = 0; i < listData.size(); i++) {
			hours.add(Double.parseDouble(listData.get(i)));
		}
		return hours;
	}
	

	public String createClient(String []in) {
		return (in[0]);
	}
	
	private ArrayList<Double> generateClients() {		
		ArrayList<Double> clients = new ArrayList<Double>();
		for (int i = 0; i < listDataClients.size(); i++) {
			clients.add(Double.parseDouble(listDataClients.get(i)));
		}
		return clients;
	}

	public List<Day> getDays() {
		return daysToSimulate;
	}

	public void setDays(List<Day> days) {
		this.daysToSimulate = days;
	}

	public List<Plate> getPlates() {
		return listOfPlates;
	}

	public void setPlates(List<Plate> plates) {
		this.listOfPlates = plates;
	}

	public List<Calification> getConsumptions() {
		return califications;
	}

	public void setConsumptions(List<Calification> califications) {
		this.califications = califications;
	}
}