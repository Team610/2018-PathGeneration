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
    			new Waypoint(0,0, Pathfinder.d2r(0)),
    			new Waypoint(1.26, 1.26, Pathfinder.d2r(90)),
    			new Waypoint(2.54, 2.54, Pathfinder.d2r(0)),
    			new Waypoint(2.54+1.26, 1.26, Pathfinder.d2r(-90)),
    			new Waypoint(2.54, 0, Pathfinder.d2r(180)),
    			new Waypoint(1.26, 1.26, Pathfinder.d2r(90)),
    			new Waypoint(0,2.54, Pathfinder.d2r(180)),
    			new Waypoint(-1.26, 1.26, Pathfinder.d2r(-90)),
    			new Waypoint(0,0, Pathfinder.d2r(0))
    	};
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("path name: ");
    	String pathName = sc.nextLine();
    	
    	PathGen curPath = new PathGen();
    	curPath.generateTrajectory(points);
    	left = curPath.getLeftTraj();
    	right = curPath.getRightTraj();
    	
    	DrawPath routine = new DrawPath();
    	JFrame frame = new JFrame("RobotPath");
    	frame.setSize(1000,1000); //50 pixels = 1m
    	frame.add(routine);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	for (int i = 0; i<left.length();i++) {
    		routine.run(left.get(i).x, left.get(i).y, right.get(i).x, right.get(i).y, points);
    	}
    	
    	curPath.writeTraj(pathName);
    	
	}

}
