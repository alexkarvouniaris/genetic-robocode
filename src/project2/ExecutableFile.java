package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;
import project2.BattleObserver;

public class ExecutableFile {

	public static void main(String[] args) throws InvalidConfigurationException {
		// Make a configuration object passing the fitness function
		Configuration conf = new DefaultConfiguration();
		FitnessFunction myFunc = new RobotFitnessFunction();
		conf.setFitnessFunction(myFunc);
		
		// Create a sample chromosome
		Gene[] sampleGenes = new Gene[4];
		sampleGenes[0] = new DoubleGene(conf, 10.0, 999.0);
		sampleGenes[1] = new DoubleGene(conf, 0.1, 0.9);
		sampleGenes[2] = new DoubleGene(conf, 0.1, 9.0);
		sampleGenes[3] = new DoubleGene(conf, 0.1, 9.0);

		Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);

		conf.setSampleChromosome(sampleChromosome);
		
		//Create a population of 5
		conf.setPopulationSize(5);
		
		Genotype population = Genotype.randomInitialGenotype(conf);
		
		// Evolute the Population
		population.evolve(2);
		
		// pick the chromose with the highest fitness
		IChromosome bestSolutionSoFar = population.getFittestChromosome();
		System.out.println("Winning Chromosome: " + bestSolutionSoFar);

		// Call robocode engine and run the best robot battle
		runWinnerBattle(bestSolutionSoFar);
	}

	private static void runWinnerBattle(IChromosome bestSolutionSoFar) {
		try {
			RobotFitnessFunction.overwrite(bestSolutionSoFar);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		RobotFitnessFunction.battle(1);
	}
}
