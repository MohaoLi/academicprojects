package edu.neu.coe.info6205.GeneticAlgorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class DriverGA {

	public static void main(String[] args) {
		// Data
		ArrayList<City> cityList = new ArrayList<>();
//		Random random = new Random();
//		for (int i = 0; i < 50; i++) {
//			int x = random.nextInt(300);
//			int y = random.nextInt(300);
//			City city = new City(x, y);
//			City departure = new City(90, 150);
//			City destination = new City(20, 150);
//			while (city.equals(departure) || city.equals(destination)) {
//				x = random.nextInt(300);
//				y = random.nextInt(300);
//				city = new City(x, y);
//			}
//			cityList.add(city);
//		}
		City city0 = new City(102, 253);
		cityList.add(city0);
		City city1 = new City(198, 112);
		cityList.add(city1);
		City city2 = new City(19, 78);
		cityList.add(city2);
		City city3 = new City(202, 122);
		cityList.add(city3);
		City city4 = new City(134, 49);
		cityList.add(city4);
		City city5 = new City(60, 113);
		cityList.add(city5);
		City city6 = new City(178, 202);
		cityList.add(city6);
		City city7 = new City(140, 71);
		cityList.add(city7);
		City city8 = new City(274, 182);
		cityList.add(city8);
		City city9 = new City(96, 232);
		cityList.add(city9);
		City city10 = new City(106, 244);
		cityList.add(city10);
		City city11 = new City(58, 286);
		cityList.add(city11);
		City city12 = new City(76, 35);
		cityList.add(city12);
		City city13 = new City(206, 110);
		cityList.add(city13);
		City city14 = new City(281, 195);
		cityList.add(city14);
		City city15 = new City(53, 43);
		cityList.add(city15);
		City city16 = new City(41, 91);
		cityList.add(city16);
		City city17 = new City(185, 80);
		cityList.add(city17);
		City city18 = new City(48, 240);
		cityList.add(city18);
		City city19 = new City(244, 277);
		cityList.add(city19);
		City city20 = new City(231, 245);
		cityList.add(city20);
		City city21 = new City(139, 259);
		cityList.add(city21);
		City city22 = new City(77, 75);
		cityList.add(city22);
		City city23 = new City(189, 260);
		cityList.add(city23);
		City city24 = new City(231, 128);
		cityList.add(city24);
		City city25 = new City(104, 65);
		cityList.add(city25);
		City city26 = new City(209, 247);
		cityList.add(city26);
		City city27 = new City(28, 284);
		cityList.add(city27);
		City city28 = new City(245, 180);
		cityList.add(city28);
		City city29 = new City(148, 53);
		cityList.add(city29);
		City city30 = new City(5, 133);
		cityList.add(city30);
		City city31 = new City(105, 49);
		cityList.add(city31);
		City city32 = new City(158, 111);
		cityList.add(city32);
		City city33 = new City(189, 218);
		cityList.add(city33);
		City city34 = new City(83, 106);
		cityList.add(city34);
		City city35 = new City(19, 42);
		cityList.add(city35);
		City city36 = new City(188, 82);
		cityList.add(city36);
		City city37 = new City(160, 198);
		cityList.add(city37);
		City city38 = new City(290, 6);
		cityList.add(city38);
		City city39 = new City(10, 265);
		cityList.add(city39);
		City city40 = new City(66, 161);
		cityList.add(city40);
		City city41 = new City(194, 128);
		cityList.add(city41);
		City city42 = new City(47, 166);
		cityList.add(city42);
		City city43 = new City(120, 173);
		cityList.add(city43);
		City city44 = new City(100, 116);
		cityList.add(city44);
		City city45 = new City(161, 107);
		cityList.add(city45);
		City city46 = new City(19, 29);
		cityList.add(city46);
		City city47 = new City(13, 65);
		cityList.add(city47);
		City city48 = new City(52, 115);
		cityList.add(city48);
		City city49 = new City(254, 63);
		cityList.add(city49);

		DriverGA driverGA = new DriverGA();
		driverGA.begin(1000, 600, 1, 0.03, cityList);

	}

	private void begin(int populationSize, int cullSize, double crossoverRate, double mutateRate,
			ArrayList<City> cityList) {
		Population population = new Population();
		DefaultCategoryDataset lineChartData = new DefaultCategoryDataset();
		ChartPanel chartPanel = new ChartPanel(null);
		JFreeChart chart = ChartFactory.createLineChart("Fitness", "Generation", "Fitness", lineChartData);
		chart.setBackgroundPaint(Color.WHITE);
		chart.getTitle().setPaint(Color.BLACK);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.YELLOW);
		ChartFrame frame = new ChartFrame("Line Chart Of Fitness", chart);
		frame.setVisible(true);
		frame.setSize(450, 350);
		int generation = 0;
		for (int i = 0; i < populationSize; i++) {
			Tour tour = new Tour(cityList);
			population.tours.add(tour);
		}

		population.selectPopulation();
		System.out.println(population.getFittestTour().fitness);

		while (true) {
			generation++;
			for (int i = cullSize; i < populationSize - 1; i++) {
				Random random = new Random();
				int j = random.nextInt(cullSize - 1);
				int x = random.nextInt(cullSize - 1);
				while (j == x) {
					x = random.nextInt(cullSize - 1);
				}
				Tour parent1 = population.tours.get(j);

				Tour parent2 = population.tours.get(x);

				Tour offspring1 = new Tour(parent1);
				Tour offspring2 = new Tour(parent2);

				if (random.nextInt(100) < crossoverRate * 100) {
					crossover(offspring1, offspring2);
				}

				if (random.nextInt(100) < mutateRate * 100) {
					mutation(offspring1);
				}

				population.tours.set(i, offspring1);
				if (random.nextInt(100) < mutateRate * 100) {
					mutation(offspring2);
				}

				population.tours.set(i + 1, offspring2);
			}

			population.selectPopulation();
			System.out.println(population.getFittestTour().fitness);
			lineChartData.setValue(population.getFittestTour().fitness, "Fitness", Integer.toString(generation));
			chartPanel.setChart(chart);

			if (generation > 200) {
				population.getFittestTour().cities.forEach(System.out::print);
				System.out.println("");
				System.out.println("Generation:" + generation + " Distance:" + " "
						+ population.getFittestTour().distance + " Fintness:" + population.getFittestTour().fitness);
				break;
			}
		}
	}

	private ArrayList<City> getGeneCity(Tour t1, Tour t2) {
		ArrayList<City> cityList = new ArrayList<City>();
		for (int i = 1; i < t1.cities.size() - 1; i++) {
			City temp1 = t1.cities.get(i);
			if (!cityList.contains(temp1)) {
				cityList.add(temp1);
			}
		}
		for (int j = 1; j < t2.cities.size() - 1; j++) {
			City temp2 = t2.cities.get(j);
			if (!cityList.contains(temp2)) {
				cityList.add(temp2);
			}
		}
		return cityList;
	}

	private void crossover(Tour t1, Tour t2) {
		ArrayList<City> cityList = getGeneCity(t1, t2);

		Random random = new Random();

		int crossOverPoint = random.nextInt(12);
		while (crossOverPoint == 0) {
			crossOverPoint = random.nextInt(12);
		}

		int startpoint = random.nextInt(11) + 1;

		while (startpoint >= crossOverPoint) {
			startpoint = random.nextInt(12);
		}

		for (; startpoint < crossOverPoint; startpoint++) {
			City temp = t1.cities.get(startpoint);
			City temp2 = t2.cities.get(startpoint);
			t2.cities.set(startpoint, temp);
			t1.cities.set(startpoint, temp2);
		}
		delRepeat(t1, cityList);
		delRepeat(t2, cityList);
	}

	private void delRepeat(Tour t, ArrayList<City> cityList) {
		ArrayList<City> cities = new ArrayList<City>();
		Random random = new Random();
		for (int i = 0; i < t.cities.size(); i++) {
			City temp = t.cities.get(i);
			if (!cities.contains(temp)) {
				cities.add(temp);
			} else {
				int j = random.nextInt(cityList.size());

				City c = cityList.get(j);
				while (cities.contains(c)) {
					j = random.nextInt(cityList.size());
					c = cityList.get(j);
				}
				cities.add(c);
			}
		}
		t.setCities(cities);
	}

	private Tour mutation(Tour tour) {
		Tour offSpring = new Tour(tour);
		int n = offSpring.cities.size();
		Random random = new Random();

		int c1 = random.nextInt(n - 1) + 1;
		int c2 = random.nextInt(n - 1) + 1;

		while (c1 == c2) {
			c2 = random.nextInt(n - 1) + 1;
		}

		City temp = offSpring.cities.get(c1);
		offSpring.cities.set(c1, offSpring.cities.get(c2));
		offSpring.cities.set(c2, temp);
		offSpring.fitness();
		return offSpring;
	}

}
