package edu.neu.coe.info6205.GeneticAlgorithms.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import edu.neu.coe.info6205.GeneticAlgorithms.City;
import edu.neu.coe.info6205.GeneticAlgorithms.Tour;

public class TestCrossover {

	@Test
	public void test() {
		Tour tour1 = new Tour();
		City city1 = new City(1, 2);
		City city2 = new City(2, 2);
		City city3 = new City(3, 2);
		City city4 = new City(4, 2);
		City city5 = new City(5, 2);
		tour1.cities.add(city1);
		tour1.cities.add(city2);
		tour1.cities.add(city3);
		tour1.cities.add(city4);
		tour1.cities.add(city5);
		
		Tour tour2 = new Tour();
		City city21 = new City(1, 4);
		City city22 = new City(3, 2);
		City city23 = new City(3, 6);
		City city24 = new City(7, 2);
		City city25 = new City(5, 9);
		tour2.cities.add(city21);
		tour2.cities.add(city22);
		tour2.cities.add(city23);
		tour2.cities.add(city24);
		tour2.cities.add(city25);
		
		crossover(tour1, tour2);

		assertEquals(city2, tour2.cities.get(1));
		assertEquals(city3, tour2.cities.get(2));
		assertEquals(city22, tour1.cities.get(1));
		assertEquals(city23, tour1.cities.get(2));
	}

	private void crossover(Tour t1, Tour t2) {
		ArrayList<City> cityList = getGeneCity(t1, t2);
		Random random = new Random();

		int crossOverPoint = 3;
		while (crossOverPoint == 0) {
			crossOverPoint = random.nextInt(12);
		}

		int startpoint = 1;

		while (startpoint >= crossOverPoint) {
			startpoint = random.nextInt(12);
		}
		// System.out.println("startpoint" + startpoint + "" + "crossOverPoint" + "" +
		// crossOverPoint);
		for (; startpoint < crossOverPoint; startpoint++) {
			City temp = t1.cities.get(startpoint);
			City temp2 = t2.cities.get(startpoint);
			t2.cities.set(startpoint, temp);
			t1.cities.set(startpoint, temp2);
		}
		delRepeat(t1, cityList);
		delRepeat(t2, cityList);

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
}
