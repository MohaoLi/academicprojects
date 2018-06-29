package edu.neu.coe.info6205.GeneticAlgorithms;

import java.util.ArrayList;
import java.util.Random;

public class Tour {
	public ArrayList<City> cities;
	public double distance;
	public double fitness;

	public Tour() {
		this.cities = new ArrayList<>();
	}
	
	public Tour(ArrayList<City> cityList) {
		this.cities = new ArrayList<>();
		completeTour(cityList);
		tourDistance();
		fitness();
	}
	
	public Tour(Tour t) {
		this.cities = new ArrayList<>();
		for(int i = 0; i < t.cities.size(); i ++) {
			this.cities.add(t.cities.get(i));
		}
		this.distance = t.distance;
		this.fitness = t.fitness;
	}
	

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
        tourDistance();
        fitness();
    }

	public double tourDistance() {
		distance = 0.0;
		int n = cities.size();
		for (int i = 0; i < n - 2; i++) {
			distance = distance + cities.get(i).distanceBetweenTwoCities(cities.get(i + 1));
		}
		return distance;
	}

	public double fitness() {
		fitness = 0.0;
		fitness = 1 / tourDistance() * 100;
		return fitness;
	}

	private void completeTour(ArrayList<City> cityList) {
		City departure = new City(90, 150);
		City destination = new City(90, 150);
		cities.add(departure);
		cities.add(destination);
		int i = cityList.size();
		while (this.cities.size() < i + 2) {
			Random random = new Random();
			int j = random.nextInt(i);
			if (!this.cities.contains(cityList.get(j))) {
				this.cities.add(1, cityList.get(j));
			}
		}
	}

}
