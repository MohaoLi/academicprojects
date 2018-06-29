package edu.neu.coe.info6205.GeneticAlgorithms.Test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import edu.neu.coe.info6205.GeneticAlgorithms.City;
import edu.neu.coe.info6205.GeneticAlgorithms.Tour;

public class TestMutation {
	
	@Test
	public void test() {
		Tour tour = new Tour();
		City city1 = new City(1, 2);
		City city2 = new City(2, 2);
		City city3 = new City(3, 2);
		City city4 = new City(4, 2);
		City city5 = new City(5, 2);
		tour.cities.add(city1);
		tour.cities.add(city2);
		tour.cities.add(city3);
		tour.cities.add(city4);
		tour.cities.add(city5);
		tour = mutation(tour);
		
		assertEquals(city3, tour.cities.get(3));
		assertEquals(city4, tour.cities.get(2));
	}
	
	private Tour mutation(Tour tour) {
		Tour offSpring = new Tour(tour);
		int n = offSpring.cities.size();
		Random random = new Random();

		int c1 = 2;
		int c2 = 3;

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
