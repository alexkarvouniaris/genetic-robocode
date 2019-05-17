package project2;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import project2.PhysicsRobocodeException;
import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

/*Here goes the updated SuperTracker's code that
 *reads the set of the 4 genes(configuration objects) from the disk file
 */
public class SuperTrackerVol2 extends AdvancedRobot {

	int moveDirection = 1;// which way to move
	double DistanceLimConsEnemyClose;// Distance limit to consider enemy is close
	double ProbabToChangeSpeed;// probability to change speed
	double RangeOfPossibRobotSpeeds; // Range of possible speeds
	double MinimumRobotSpeed;// minimum speed of the robot
	Random randomGenerator;// All the random numbers should be the same each time for deterministic
							// reasons;

	public void run() {
		randomGenerator = new Random(0);
		try {
			readVars();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		setAdjustRadarForRobotTurn(true);// keep the radar still while we turn
		setBodyColor(new Color(128, 128, 50));
		setGunColor(new Color(50, 50, 20));
		setRadarColor(new Color(200, 200, 70));
		setScanColor(Color.white);
		setBulletColor(Color.blue);
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		turnRadarRightRadians(Double.POSITIVE_INFINITY);// keep turning radar right
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		double absBearing = e.getBearingRadians() + getHeadingRadians();// enemies absolute bearing
		double latVel = e.getVelocity() * Math.sin(e.getHeadingRadians() - absBearing);// enemies later velocity
		double gunTurnAmt;// amount to turn our gun
		setTurnRadarLeftRadians(getRadarTurnRemainingRadians());// lock on the radar
		if (randomGenerator.nextDouble() > ProbabToChangeSpeed) {
			setMaxVelocity((RangeOfPossibRobotSpeeds * randomGenerator.nextDouble()) + MinimumRobotSpeed);
		}
		if (e.getDistance() > DistanceLimConsEnemyClose) {// if distance is greater than DistanceLimConsEnemyClose
			gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel / 22);
			setTurnGunRightRadians(gunTurnAmt); // turn our gun
			setTurnRightRadians(
					robocode.util.Utils.normalRelativeAngle(absBearing - getHeadingRadians() + latVel / getVelocity()));
			setAhead((e.getDistance() - 140) * moveDirection);// move forward
			setFire(3);// fire
		} else {// if we are close enough...
			gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel / 15);
			setTurnGunRightRadians(gunTurnAmt);// turn our gun
			setTurnLeft(-90 - e.getBearing()); // turn perpendicular to the enemy
			setAhead((e.getDistance() - 140) * moveDirection);// move forward
			setFire(3);// fire
		}
	}

	public void onHitWall(HitWallEvent e) {
		moveDirection = -moveDirection;//reverse direction upon hitting a wall
	}

	/*
	 * public void onWin(WinEvent e) { for (int i = 0; i < 50; i++) { turnRight(30);
	 * turnLeft(30); } }
	 */

	public void readVars() throws FileNotFoundException {

		// gets the current user path
		Path currentRelativePath = Paths.get("");
		String currentPath = currentRelativePath.toAbsolutePath().toString();

		try {
			File file = new File(currentPath + "/src/project2/DiskFile.txt");
			Scanner sc = new Scanner(file);
			sc.useDelimiter(" ");

			double a = Double.parseDouble(sc.next());
			if (a < 0 || a > 1000) {
				throw new PhysicsRobocodeException("Wrong input in the Distance argument");
			}
			setDistanceLimConsEnemyClose(a);

			double b = Double.parseDouble(sc.next());
			if (b < 0 || b > 1) {
				throw new PhysicsRobocodeException("Wrong input in the Probability argument");
			}
			setProbabToChangeSpeed(b);

			double c = Double.parseDouble(sc.next());
			if ((c < 0) || (c > 8)) {
				throw new PhysicsRobocodeException("Wrong input in the Posibility of Speeds argument");
			}
			setRangeOfPossibRobotSpeeds(c);

			double d = Double.parseDouble(sc.next());
			if (d < 0 || d > 8) {
				throw new PhysicsRobocodeException("Wrong input in the 4 argument");
			}
			setMinimumRobotSpeed(d);

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("file");
		}
		// catch exception
	}

	public void setDistanceLimConsEnemyClose(double distanceLimConsEnemyClose) {
		DistanceLimConsEnemyClose = distanceLimConsEnemyClose;
	}

	public void setProbabToChangeSpeed(double probabToChangeSpeed) {
		ProbabToChangeSpeed = probabToChangeSpeed;
	}

	public void setRangeOfPossibRobotSpeeds(double rangeOfPossibRobotSpeeds) {
		RangeOfPossibRobotSpeeds = rangeOfPossibRobotSpeeds;
	}

	public void setMinimumRobotSpeed(double minimumRobotSpeed) {
		MinimumRobotSpeed = minimumRobotSpeed;
	}
}