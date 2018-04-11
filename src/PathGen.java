import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import constants.GenerationConstants;
import constants.RobotConstants;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class PathGen {
	private Trajectory leftTrajectory, rightTrajectory;
	private boolean isReversedR = false, isReversedL = false;

	boolean localOutput = false;
	public PathGen() {
	}

	public void generateTrajectory(Waypoint[] points, boolean isReversedL, boolean isReversedR) {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
				Trajectory.Config.SAMPLES_HIGH, 0.01, RobotConstants.maxVelocity, RobotConstants.maxAcceleration,
				RobotConstants.maxJerk);
		Trajectory trajectory = Pathfinder.generate(points, config); // = 20ms
		this.isReversedL = isReversedL;
		this.isReversedR = isReversedR;
		TankModifier modifier = new TankModifier(trajectory).modify(RobotConstants.robotWidth);
		
		leftTrajectory = modifier.getLeftTrajectory();
		rightTrajectory = modifier.getRightTrajectory();
	}

	public Trajectory getLeftTraj() {
		return leftTrajectory;
	}

	public Trajectory getRightTraj() {
		return rightTrajectory;
	}

	public void writeTraj() throws IOException {
		writeLeftTraj();
		writeRightTraj();
	}

	public void writeTraj(String pathName) throws IOException {
		writeLeftTraj(pathName);
		writeRightTraj(pathName);
	}

	public void writeLeftTraj() throws IOException {
		Segment segment;
		File myFile = new File("pathL.txt");
		FileWriter writer = new FileWriter(myFile);
		double l = 0;
		for (int i = 0; i < leftTrajectory.length(); i++) {
			segment = leftTrajectory.get(i);

			double position = round(segment.position, 5);
			double velocity = round(segment.velocity, 5);
			double time = segment.dt;
			double acceleration = segment.acceleration;

			writer.write("{" + position / (RobotConstants.wheelCirc) + ", " + velocity * 60 / (RobotConstants.wheelCirc)
					+ ", " + time + "},");
			l++;
		}
		System.out.println(l);
		writer.write("};");
		writer.close();
	}

	public void writeRightTraj() throws IOException {
		Segment segment;
		File myFileTwo = new File("pathR.txt");
		FileWriter writer = new FileWriter(myFileTwo);
		System.out.println("RightSide");
		for (int i = 0; i < rightTrajectory.length(); i++) {
			segment = rightTrajectory.get(i);
			double position = round(segment.position, 5);
			double velocity = round(segment.velocity, 5);
			double time = segment.dt;
			double acceleration = segment.acceleration;

			writer.write("{" + position / (RobotConstants.wheelCirc) + ", " + velocity * 60 / (RobotConstants.wheelCirc)
					+ ", " + time + "},");
		}
		writer.write("};");
		writer.close();
	}

	public void writeLeftTraj(String pathName) throws IOException {
		Segment segment;
		File myFile;
		if(isReversedL && isReversedR)
			myFile = new File(GenerationConstants.generationOutput + pathName + "_R.java");
		else
			myFile = new File(GenerationConstants.generationOutput + pathName + "_L.java");
		FileWriter writer = new FileWriter(myFile);
		writer.write(GenerationConstants.packageLine);
		if(isReversedL && isReversedR)
			writer.write("public class " + pathName + "_R { ");
		else
			writer.write("public class " + pathName + "_L { ");
		writer.write(GenerationConstants.variableName);

		double l = 0;
		double maxVel = 0;
		for (int i = 0; i < leftTrajectory.length(); i++) {
			segment = leftTrajectory.get(i);

			double position = round(segment.position, 5);
			double velocity = round(segment.velocity, 5);
			if(velocity>maxVel)
				maxVel = velocity;
			double time = segment.dt;
			double acceleration = segment.acceleration;
			double heading = Pathfinder.r2d(segment.heading);

			if(isReversedL) {
				writer.write("{" + -position / (RobotConstants.wheelCirc) + ", " + -velocity * 60 / (RobotConstants.wheelCirc)
						+ ", " + heading + ", " + time + "},");
			}else {
				writer.write("{" + position / (RobotConstants.wheelCirc) + ", " + velocity * 60 / (RobotConstants.wheelCirc)
					+ ", " + heading + ", " + time + "},");
			}
			l++;
		}
		System.out.println(l);
		writer.write("};}");
		writer.write("//max Velocity in native units: " + (maxVel*60/(RobotConstants.wheelCirc)*512.0/600.0)); //1024 should become 512 when we switch to actual robot
		
		writer.close();
	}

	public void writeRightTraj(String pathName) throws IOException {
		Segment segment;
		File myFileTwo;
		if(isReversedL && isReversedR)
			myFileTwo = new File(GenerationConstants.generationOutput + pathName + "_L.java");
		else
			myFileTwo = new File(GenerationConstants.generationOutput + pathName + "_R.java");
		FileWriter writer = new FileWriter(myFileTwo);
		writer.write(GenerationConstants.packageLine);
		if(isReversedL && isReversedR)
			writer.write("public class " + pathName + "_L { ");
		else
			writer.write("public class " + pathName + "_R { ");
		writer.write(GenerationConstants.variableName);

		double maxVel = 0;
		for (int i = 0; i < rightTrajectory.length(); i++) {
			segment = rightTrajectory.get(i);
			double position = round(segment.position, 5);
			double velocity = round(segment.velocity, 5);
			if(velocity>maxVel)
				maxVel = velocity;
			double time = segment.dt;
			double acceleration = segment.acceleration;
			double heading = Pathfinder.r2d(segment.heading);

			if(isReversedR) {
				writer.write("{" + -position / (RobotConstants.wheelCirc) + ", " + -velocity * 60 / (RobotConstants.wheelCirc)
						+ ", " + heading + ", " + time + "},");
			}else {
				writer.write("{" + position / (RobotConstants.wheelCirc) + ", " + velocity * 60 / (RobotConstants.wheelCirc)
					+ ", " + heading + ", " + time + "},");
			}
		}
		writer.write("};}");
		writer.write("//max Velocity in native units: " + (maxVel*60/(RobotConstants.wheelCirc)*512.0/600.0)); //1024 should become 512 when we switch to actual robot
		writer.close();
	}

	public double round(double num, int decimalPlaces) {
		int newNum = (int) (num * Math.pow(10, decimalPlaces));
		return newNum / Math.pow(10, decimalPlaces);
	}

}
