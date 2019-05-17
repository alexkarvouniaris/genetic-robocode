package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;

public class RobotFitnessFunction extends FitnessFunction {

	public static void overwrite(IChromosome chr) throws FileNotFoundException {

		//Get the Gene Values
		Gene a = chr.getGene(0);
		Gene b = chr.getGene(1);
		Gene c = chr.getGene(2);
		Gene d = chr.getGene(3);

		//Print the double values to the DiskFile.txt
		StringBuffer sb = new StringBuffer();
		sb.append(a.toString().replace("DoubleGene(10.0,999.0)=", "") + " "
				+ b.toString().replace("DoubleGene(0.1,0.9)=", "") + " "
				+ c.toString().replace("DoubleGene(0.1,9.0)=", "") + " "
				+ d.toString().replace("DoubleGene(0.1,9.0)=", ""));

		//Gets the current user path
		Path currentRelativePath = Paths.get("");
		String currentPath = currentRelativePath.toAbsolutePath().toString();

		File file = new File(currentPath + "/src/project2/DiskFile.txt");

		PrintWriter pw = new PrintWriter(file);
		file.getParentFile().mkdirs();
		pw.println(sb);
		pw.close();
	}

	public static double battle(int fin) {

		//Setting the battle specification
		
		//The default robocode path
		RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/robocode"));
		
		//If fin = 1 , it is the final batlle
		if (fin == 0) {
			engine.setVisible(false);
		} else if (fin == 1) {
			engine.setVisible(true);
		}

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
		RobotSetup[] robotSetups = new RobotSetup[2]; // Initializing the robots
		existingRobots[0] = Robots[0];
		robotSetups[0] = new RobotSetup(200.0, 200.0, 0.0);

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
		
		return b.getScore();
	}

	@Override
	protected double evaluate(IChromosome arg0) {
		try {
			overwrite(arg0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		double fitness = battle(0);// here goes the variable that fixes fitness value
		System.out.println("Fitness is : " + fitness);
		return fitness;
	}
}
