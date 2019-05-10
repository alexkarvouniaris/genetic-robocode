package project2;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;
import project2.BattleObserver;

public class ExecutableFile extends FitnessFunction {

	// Fitness function goes here, to DO
	@Override
	protected double evaluate(IChromosome arg0) {
		// TODO Auto-generated method stub
		double fitness = 0;// here goes the variable that fixes fitness value
		return fitness;
	}
	// Make a configuration object passing the fitness function, to DO

	// Make a function that fills a disk file with a set of the 4 genes, to DO

	// This is for testing purposes

	// Create the Population of N chromosomes

	// Evolute the Population

	// check If we are on target

	// pick the chromose with the highest fitness

	// Make a function that parses the 4 genes (double values ) to the disk file
	// Make a function fills a disk file with a set of the 4 genes, to DO

	// Call robocode engine and run a specific battle

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			//System.out.println(i);
			//System.out.println("Programm running for the " + i + " time!!");
			battle();
		}
		// Make sure that the Java VM is shut down properly
		System.exit(0);
	}

	public static void battle() {

		// setting the battle specification
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/robocode"));
		engine.setVisible(false);

		int RowsLength = 800;
		int ColLength = 600;
		int VerticalOffset = ColLength % 64;// because the size of each position is 64
		BattlefieldSpecification battlefield = new BattlefieldSpecification(RowsLength, ColLength);
		int numberOfRounds = 5;
		long inactivityTime = 10000000;
		double gunCoolingRate = 1.0;
		int sentryBorderSize = 50;
		boolean hideEnemyNames = true;

		// the two robot
		RobotSpecification[] Robots = engine.getLocalRepository("project2.SuperRamFire,project2.SuperTrackerVol2");
		RobotSpecification[] existingRobots = new RobotSpecification[2];
		RobotSetup[] robotSetups = new RobotSetup[2]; // initialazing the robots
		existingRobots[0] = Robots[0];
		robotSetups[0] = new RobotSetup(200.0, 200.0, 0.0); // set the robot every time with the given parameters

		// take the robot 1 which is the robot that the second team will program
		existingRobots[1] = Robots[1];

		robotSetups[1] = new RobotSetup(500.0, 500.0, 250.0);

		// Create the battle with the specifications
		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds, inactivityTime,
				gunCoolingRate, sentryBorderSize, hideEnemyNames, existingRobots, robotSetups);

		// add our own battlelistener
		BattleObserver b = new BattleObserver();
		engine.addBattleListener(b);

		// start the battle
		engine.runBattle(battleSpec, true); // waits till the battle finishes

		System.out.println("Score is : " + b.getScore());
		// Cleanup our RobocodeEngine
		// engine.close();

	}
}
