package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;

public class RobotFitnessFunction extends FitnessFunction{
	public RobotFitnessFunction() {
		
	}
	
	public void overwrite(IChromosome chr) throws FileNotFoundException {
		Gene a = chr.getGene(0);
		Gene b = chr.getGene(1);
		Gene c = chr.getGene(2);
		Gene d = chr.getGene(3);
		
		a.toString();
		b.toString();
		c.toString();
		d.toString();
			
		StringBuffer sb = new StringBuffer();
		sb.append(a + " " + b + " " + c + " " + d);

		File file = new File("C:/Users/alex/genetic-robocode/src/project2/Disk File");
		PrintWriter pw = new PrintWriter(file);
		file.getParentFile().mkdirs();
		pw.println(sb);
		pw.close();
	}
	
	// Fitness function goes here, to DO
	public static double battle() {

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
		return b.getScore();

	}

	@Override
	protected double evaluate(IChromosome arg0) {
		// TODO Auto-generated method stub
		try {
			overwrite(arg0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double fitness = battle();// here goes the variable that fixes fitness value
		System.out.println("Fitness is : " + fitness);
		return fitness;
	}
}
