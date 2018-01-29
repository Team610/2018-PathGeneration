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
	public PathGen() {
	}
	
	public void generateTrajectory(Waypoint[] points) {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.01, RobotConstants.maxVelocity, RobotConstants.maxAcceleration, 160);
		Trajectory trajectory = Pathfinder.generate(points, config);														//= 10ms
	
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
		for(int i=0; i<leftTrajectory.length(); i++) {
			segment = leftTrajectory.get(i);
			
			double position = round(segment.position,5); double velocity = round(segment.velocity,5); double time = segment.dt; double acceleration = segment.acceleration;
			
			
			writer.write("{" + position/(RobotConstants.wheelCirc) + ", " + velocity*60/(RobotConstants.wheelCirc) + ", " + time + "},");
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
		for(int i=0; i<rightTrajectory.length(); i++) {
			segment = rightTrajectory.get(i);
			double position = round(segment.position,5); double velocity = round(segment.velocity,5); double time = segment.dt; double acceleration = segment.acceleration;
			
			writer.write("{" + position/(RobotConstants.wheelCirc) + ", " + velocity*60/(RobotConstants.wheelCirc) +   ", " + time + "},");
		}
		writer.write("};");
		writer.close();
	}
	
	public void writeLeftTraj(String pathName) throws IOException {
		Segment segment;
		File myFile = new File(GenerationConstants.generationOutput + pathName +"Left.java");
		FileWriter writer = new FileWriter(myFile);
		writer.write(GenerationConstants.generationOutput);
		writer.write("public class " + pathName + "Left { ");
		writer.write(GenerationConstants.variableName);
		
		double l = 0;
		for(int i=0; i<leftTrajectory.length(); i++) {
			segment = leftTrajectory.get(i);
			
			double position = round(segment.position,5); double velocity = round(segment.velocity,5); double time = segment.dt; double acceleration = segment.acceleration;
			
			
			writer.write("{" + position/(RobotConstants.wheelCirc) + ", " + velocity*60/(RobotConstants.wheelCirc) + ", " + time + "},");
			l++;
		}
		System.out.println(l);
		writer.write("};}");
		writer.close();
	}
	
	public void writeRightTraj(String pathName) throws IOException {
		Segment segment;
		File myFileTwo = new File(GenerationConstants.generationOutput + pathName + "Right.java");
		FileWriter writer = new FileWriter(myFileTwo);
		writer.write(GenerationConstants.generationOutput);
		writer.write("public class " + pathName + "Right { ");
		writer.write(GenerationConstants.variableName);
		
		for(int i=0; i<rightTrajectory.length(); i++) {
			segment = rightTrajectory.get(i);
			double position = round(segment.position,5); double velocity = round(segment.velocity,5); double time = segment.dt; double acceleration = segment.acceleration;
			
			writer.write("{" + position/(RobotConstants.wheelCirc) + ", " + velocity*60/(RobotConstants.wheelCirc) +   ", " + time + "},");
		}
		writer.write("};}");
		writer.close();
	}
	
	public double round(double num, int decimalPlaces) {
		int newNum = (int)(num*Math.pow(10, decimalPlaces));
		return newNum/Math.pow(10, decimalPlaces);
	}
	

}
