import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		Waypoint[] points;
		Trajectory left, right;
		
    	points = new Waypoint[] {
    			new Waypoint(0,inchesToMeters(162), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(100), inchesToMeters(220), Pathfinder.d2r(0)),   
    	};
    	
    	//switch left: p1(0,162,0) p2(106,108,0) //
    	    	//right turn: p1(0,0,0) p2(100,100,90)
    	
    	//switch right: p1(0,162,0) p2(106,216,0)
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("path name: ");
    	String pathName = sc.nextLine();
    	
    	PathGen curPath = new PathGen();
    	curPath.generateTrajectory(points);
    	left = curPath.getLeftTraj();
    	right = curPath.getRightTraj();
    	
    	DrawPath routine = new DrawPath();
    	JFrame frame = new JFrame("RobotPath");
    	frame.setSize(435,903); //accounts for menu bar at the top + field
    	frame.add(routine);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
    	routine.run(left, right, points);
    	
    	curPath.writeTraj(pathName);
    	
	}
	
	public static double inchesToMeters(double inches) {
		return inches*1.0/39.37;
	}

}
