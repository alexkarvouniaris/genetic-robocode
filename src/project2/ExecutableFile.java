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
	// Make a configuration object passing the fitness function, to DO
	Configuration conf = new DefaultConfiguration();
	FitnessFunction myFunc = new RobotFitnessFunction();
	
	conf.setFitnessFunction( myFunc );
	// Create the Population of N chromosomes
	Gene[] sampleGenes = new Gene[4];
	sampleGenes[0] = new DoubleGene(conf, 10.0, 999.0);
	sampleGenes[1] = new DoubleGene(conf, 0.1, 0.9);
	sampleGenes[2] = new DoubleGene(conf, 0.1, 9.0);
	sampleGenes[3] = new DoubleGene(conf, 0.1, 9.0);
	
	
	Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
	
	conf.setSampleChromosome( sampleChromosome );
	
	conf.setPopulationSize( 5 );

	// Evolute the Population
	Genotype population = Genotype.randomInitialGenotype( conf );
	// check If we are on target
	population.evolve();
	// pick the chromose with the highest fitness
	IChromosome bestSolutionSoFar = population.getFittestChromosome();
	System.out.println("Winning Chromosome: " + bestSolutionSoFar);	

	// Call robocode engine and run a specific battle

	}
}
	

