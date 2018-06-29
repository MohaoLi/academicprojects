package edu.neu.coe.info6205.GeneticAlgorithms;

public class City {
	int x;
	int y;
	
	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double distanceBetweenTwoCities(City city) {
		double distance = Math.sqrt(Math.pow(this.x - city.x, 2) + Math.pow(this.y - city.y, 2));
		return distance;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
}