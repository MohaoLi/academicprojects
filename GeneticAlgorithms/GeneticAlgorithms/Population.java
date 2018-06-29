package edu.neu.coe.info6205.GeneticAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population {
	ArrayList<Tour> tours;

	public Population() {
		tours = new ArrayList<>();
	}

	public void selectPopulation() {
		Collections.sort(tours, new Comparator<Tour>() {
			public int compare(Tour t2, Tour t1) {
				return new Double(t1.fitness).compareTo(t2.fitness);
			}
		});
	}

	public Tour getFittestTour() {
		selectPopulation();
		return tours.get(0);
	}
}
