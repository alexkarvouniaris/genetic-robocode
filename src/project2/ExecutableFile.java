package project2;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;

public class ExecutableFile extends FitnessFunction{
	
	//Fitness function goes here, to DO
	@Override
	protected double evaluate(IChromosome arg0) {
		// TODO Auto-generated method stub
		double fitness = 0;//here goes the variable that fixes fitness value
		return fitness;
	}
	//Make a configuration object passing the fitness function, to DO

	//Make a function that fills a disk file with a set of the 4 genes, to DO
	public static void main(String[] args) {
	//This is for testing purposes
	}

	//Create the Population of N chromosomes
	
	//Evolute the Population
	
	//check If we are on target 
	
	//pick the chromose with the highest fitness
	
	
	//Make a function that parses the 4 genes (double values ) to the disk file
	//Make a function fills a disk file with a set of the 4 genes, to DO
	
	
	//Call robocode engine and run a specific battle
	public static void Battle() {

		// setting the battle specification
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/robocode"));
		engine.setVisible(true);

		int RowsLength = 800;
		int ColLength = 600;
		int VerticalOffset = ColLength % 64;// because the size of each position is 64
		BattlefieldSpecification battlefield = new BattlefieldSpecification(RowsLength, ColLength);
		int numberOfRounds = 1;
		long inactivityTime = 10000000;
		double gunCoolingRate = 1.0;
		int sentryBorderSize = 50;
		boolean hideEnemyNames = true;

		
		RobotSpecification[] Robots = engine.getLocalRepository("fnl.SuperRamFire,fnl.SuperTracker");// the two
																										// robots
		// that
		// we will use

		RobotSpecification[] existingRobots = new RobotSpecification[2];

		RobotSetup[] robotSetups = new RobotSetup[2]; // initialazing the robots

		existingRobots[0] = Robots[0];
		robotSetups[0] = new RobotSetup(200.0, 200.0, 0.0); // set the robot
		// take the robot 1 which is the robot that the second team will programm
		existingRobots[1] = Robots[1];

		robotSetups[1] = new RobotSetup(500.0, 500.0, 250.0);

		// Create the battle with the specifications
		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds, inactivityTime,
				gunCoolingRate, sentryBorderSize, hideEnemyNames, existingRobots, robotSetups);
		
		// start the battle
		engine.runBattle(battleSpec, true); // waits till the battle finishes
		

		// Cleanup our RobocodeEngine
		engine.close();

	}
}
