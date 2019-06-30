package uptc.com.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import persistence.FileManager;


public class Restaurant {

	public static final int MIN_CLIENTS_DAY = 200;
	public static final int MAX_CLIENTS_DAY = 300;
	public static final int MIN_HOURS_DAY = 10;
	public static final int MAX_HOURS_DAY = 12;

	private List<Day> daysToSimulate;
	private List<Product> listOfPlates;
	private List<Calification> calificationList = new ArrayList<>();
	private List<String> listData = new ArrayList<String>();

	private static Restaurant restaurant = new Restaurant();

	public static Restaurant getInstance() {
		return restaurant;
	}

	private Restaurant() {
	}

	public void start(int hoursToSimulate) {
		addPlates();
		generateDays(hoursToSimulate);
		System.out.println("Acabó");
		startSimulation();
	}

	private void generateFinalReport() {
		System.out.println();
		int totalPlate1 = 0, totalPlate2 = 0, totalPlate3 = 0, totalPlate4 = 0;
		double sumOfRatingsPlate1 = 0, sumOfRatingsPlate2 = 0, sumOfRatingPlate3 = 0, sumOfRatingPlate4 = 0;
		int totalNumberOfRatingsPlate1 = 0, totalNumberOfRatingsPlate2 = 0, totalNumberOfRatingsPlate3 = 0,
				totalNumberOfRatingsPlate4 = 0;
		for (Calification consumption : calificationList) {
			try {
			switch (consumption.getProduct().getIdProduct()) {
			case 0:
				totalPlate1++;
				if (consumption.getScore() != -1) {
					sumOfRatingsPlate1 += consumption.getScore();
					totalNumberOfRatingsPlate1++;
				}
				break;
			case 1:
				totalPlate2++;
				if (consumption.getScore() != -1) {
					sumOfRatingsPlate2 += consumption.getScore();
					totalNumberOfRatingsPlate2++;
				}
				break;
			case 2:
				totalPlate3++;
				if (consumption.getScore() != -1) {
					sumOfRatingPlate3 += consumption.getScore();
					totalNumberOfRatingsPlate3++;
				}
				break;
			case 3:
				totalPlate4++;
				if (consumption.getScore() != -1) {
					sumOfRatingPlate4 += consumption.getScore();
					totalNumberOfRatingsPlate4++;
				}
				break;
			}
		} catch (Exception e) {
		}

		}
		System.out.println("Bandeja Paisa: " + totalPlate1 + "\t Calificación: " + sumOfRatingsPlate1 / totalNumberOfRatingsPlate1);
		System.out.println("Arroz con Pollo: " + totalPlate4 + "\t Calificación:  " + sumOfRatingPlate4 / totalNumberOfRatingsPlate4);
		System.out.println("Paella a la Valenciana: " + totalPlate3 + "\t Calificación:  " + sumOfRatingPlate3 / totalNumberOfRatingsPlate3);
		System.out.println("Cuchuco de Trigo con Espinazo : " + totalPlate2 + "\t Calificación: " + sumOfRatingsPlate2 / totalNumberOfRatingsPlate2);
	}
	
	public void addConsumation(Calification consumption) {
		calificationList.add(consumption);
	}

	private void addPlates() {
		listOfPlates = new ArrayList<>();
		listOfPlates.add(new Product(0, "Bandeja Paisa", Math.random()));
		listOfPlates.add(new Product(2, "Paella a la Valenciana", Math.random()));
		listOfPlates.add(new Product(3, "Arroz con Pollo", Math.random()));
		listOfPlates.add(new Product(1, "Cuchuco de Trigo con Espinazo", Math.random()));
	}

	private void startSimulation() {
		ManagerRestaurant table1 = null;
		ManagerRestaurant table2 = null;
		int timeForConsumer = 0;
		System.out.println("Dias-->" + daysToSimulate.size());
		System.out.println();

		for (Day day : daysToSimulate) {
			System.out.println("Inicio Dia " + day.getId() + "");
			System.out.println("horas" + day.getJobHours());
			System.out.println("clientes" + day.getClients());
			table1 = new ManagerRestaurant(0, "Mesa 1 ", day.getClients() / 2);
			table2 = new ManagerRestaurant(0, "Mesa 2 ", (int) Math.ceil(day.getClients() / 2));

			timeForConsumer = (day.getClients() / 2) / day.getJobHours();
			table1.setWaitTime((timeForConsumer * 10));
			table2.setWaitTime((timeForConsumer * 10));
			table1.start();
			table2.start();
			while (table1.isAlive() || table2.isAlive()) {
			}
			System.out.println("Fin dia" + day.getId() );
		}
		generateFinalReport();
	}

	private void generateDays(int hoursToSimulate) {
		daysToSimulate = new ArrayList<>();
		int auxHours = 0;
		int auxDay = 1;
		List<Double> hours = generateUniformDistribution(11, 2, 7, 24, 100, MIN_HOURS_DAY, MAX_HOURS_DAY);
		List<Double> totalOfConsumers = generateUniformDistribution(11, 2, 7, 32, 100, MIN_CLIENTS_DAY, MAX_CLIENTS_DAY);
		while (auxHours < hoursToSimulate) {
			daysToSimulate.add(new Day(auxDay, (int) (Math.round(hours.get(auxDay))), (int) (Math.round(totalOfConsumers.get(auxDay)))));
			auxHours += (int) (Math.round(hours.get(auxDay)));
			auxDay++;
		}
	}

//	private ArrayList<Double> generateUniformDistribution(double x0, int k, int c, int g, int quantity, int min, int max) {		
//		ArrayList<Double> pseudoNumbers = model.LinearCongruency.generateNumbers(x0, k, c, g, quantity);
//		ArrayList<Double> hours = model.UniformDistribution.generateDistribution(pseudoNumbers, min, max);
//		while (!(model.Pruebas.pruebaMedias(0.95, pseudoNumbers) && model.Pruebas.pruebaVarianza(pseudoNumbers) && model.Pruebas.pruebaKS(pseudoNumbers))) {
//			pseudoNumbers = model.LinearCongruency.generateNumbers(x0, k, c, g, quantity);
//			hours = model.UniformDistribution.generateDistribution(pseudoNumbers, min, max);
//		}
//		return hours;
//	}

	public void manageFile() {
		try {
			List<String> file = FileManager.readFileHour();
			for (int i = 0; i < file.size(); i++) {
				listData.add(createPlayer(FileManager.splitLine(file.get(i), ",")));
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public String createPlayer(String []in) {
		return (in[0]);
	}
	
	private ArrayList<Double> generateUniformDistribution(double x0, int k, int c, int g, int quantity, int min, int max) {		
		ArrayList<Double> hours = new ArrayList<Double>();
		for (int i = 0; i < listData.size(); i++) {
			hours.add(Double.parseDouble(listData.get(i)));
		}
		return hours;
	}

	public List<Day> getDays() {
		return daysToSimulate;
	}

	public void setDays(List<Day> days) {
		this.daysToSimulate = days;
	}

	public List<Product> getPlates() {
		return listOfPlates;
	}

	public void setPlates(List<Product> plates) {
		this.listOfPlates = plates;
	}

	public List<Calification> getConsumptions() {
		return calificationList;
	}

	public void setConsumptions(List<Calification> consumptions) {
		this.calificationList = consumptions;
	}
}