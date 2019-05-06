package project2;

public class Chromosome {
	/*
	 * Here we difine the chromosome for the genetic algorith
	 * A chromosome with 4 genes
	 */
	double minDist, speedProb, speedRange, minSpeed;
	double fitness = 0;
	
	public Chromosome(double a, double b, double c, double d) {
		this.minDist = a;
		this.speedProb = b;
		this.speedRange = c;
		this.minSpeed = d;
	}
	
	public double get_minDist() {return minDist;}
	public double get_speedProb() {return speedProb;}
	public double get_speedRange() {return speedRange;}
	public double get_minSpeed() {return minSpeed;}
	public void put_minDist(double e) {this.minDist = e;}
	public void put_speedProb(double e) {this.speedProb = e;}
	public void put_speedRange(double e) {this.speedRange = e;}
	public void put_minSpeed(double e) {this.minSpeed = e;}
	public double get_fitness() {return fitness;}
	public void put_fitness(double e) {this.fitness = e;}
}
