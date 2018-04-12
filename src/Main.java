import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		Waypoint[][] points = new Waypoint[3][];
		Trajectory left, right;
		boolean isReversedR = false, isReversedL = false;
		
		boolean display = true; //Change if you don't want it to show something
		boolean save = true; //change if you want to save 
		
    	points[0] = new Waypoint[] {
    			
    			//backwardsScale first cube
    			/*
    			new Waypoint(inchesToFeet(0),inchesToFeet(280), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(150),inchesToFeet(280), Pathfinder.d2r(0)), //110,320,90
    			new Waypoint(inchesToFeet(280),inchesToFeet(245), Pathfinder.d2r(0)), //-20
    			*/
    			//pickup second cube
    			/*
    			new Waypoint(inchesToFeet(280),inchesToFeet(245), Pathfinder.d2r(180)),
    			new Waypoint(inchesToFeet(220),inchesToFeet(235), Pathfinder.d2r(180)),
				*/
    			
    			//shoot second cube
    			/*
    			new Waypoint(inchesToFeet(220),inchesToFeet(235), Pathfinder.d2r(0)), //110,320,90
    			new Waypoint(inchesToFeet(290),inchesToFeet(305), Pathfinder.d2r(100)),
    			*/
    			
    			
    			
    			
    			
    			//backwards Cross auto first cube
    			/*
    			new Waypoint(inchesToFeet(0),inchesToFeet(280), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(150),inchesToFeet(280), Pathfinder.d2r(0)), //110,320,90
    			new Waypoint(inchesToFeet(240),inchesToFeet(200), Pathfinder.d2r(90)), //-20
    			new Waypoint(inchesToFeet(240),inchesToFeet(120), Pathfinder.d2r(90)), //-20
    			new Waypoint(inchesToFeet(280),inchesToFeet(68), Pathfinder.d2r(0)), //-20
    			*/
    			
    			//pickup second cube
    			/*
    			new Waypoint(inchesToFeet(280),inchesToFeet(68), Pathfinder.d2r(180)),
    			new Waypoint(inchesToFeet(220),inchesToFeet(88), Pathfinder.d2r(180)),
    			*/
    			
    			//shoot second cube
    			/*
    			new Waypoint(inchesToFeet(220),inchesToFeet(88), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(290),inchesToFeet(30), Pathfinder.d2r(80)),
    			*/
    			
    			
    			
    			//new middle left switch
    			/*
    			new Waypoint(inchesToFeet(0),inchesToFeet(162), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(115),inchesToFeet(220), Pathfinder.d2r(0)),
    			*/
    			
    			//backup center left switch (TRMLSwitch)
    			/*
    			new Waypoint(inchesToFeet(115),inchesToFeet(220), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(40),inchesToFeet(162), Pathfinder.d2r(0)),
    			*/
    			
    			//intake second cube (MSwitchIntake) generic for both sides
    			/*
    			new Waypoint(inchesToFeet(40),inchesToFeet(162), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(80),inchesToFeet(162), Pathfinder.d2r(0)),
    			*/
    			
    			//backup after intake center (MReverse) generic for both sides
    			/*
    			new Waypoint(inchesToFeet(80),inchesToFeet(162), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(30),inchesToFeet(162), Pathfinder.d2r(0)),
    			*/
    			
    			//left scale from center after reverse (TMLScale)
    			/*
    			new Waypoint(inchesToFeet(30),inchesToFeet(162), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(100),inchesToFeet(240), Pathfinder.d2r(60)),
    			new Waypoint(inchesToFeet(200),inchesToFeet(300), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(300),inchesToFeet(270), Pathfinder.d2r(290)),
    			*/
    			
    			//right scale from center after reverse (TMRScale)
    			/*
    			new Waypoint(inchesToFeet(30),inchesToFeet(162), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(100),inchesToFeet(84), Pathfinder.d2r(120)),
    			new Waypoint(inchesToFeet(200),inchesToFeet(30), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(300),inchesToFeet(50), Pathfinder.d2r(250)),
    			*/
    			
    			
    			//new middle right switch
    			/*
    			new Waypoint(inchesToFeet(0),inchesToFeet(162), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(110),inchesToFeet(100), Pathfinder.d2r(0)),
    			*/
    			
    			//backup center right switch (TRMRSwitch)
    			/*
    			new Waypoint(inchesToFeet(110),inchesToFeet(100), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(40),inchesToFeet(162), Pathfinder.d2r(0)),
    			*/
    			new Waypoint(inchesToFeet(0),inchesToFeet(150), Pathfinder.d2r(0)),
    			new Waypoint(inchesToFeet(150),inchesToFeet(150), Pathfinder.d2r(0)),
    			
    	};
    	
  
    	
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("path name: ");
    	String pathName = sc.nextLine();
    	System.out.println("R for reverse, F for forward");
    	String reverse = sc.nextLine();
    	if(reverse.charAt(0) == 'R')
    		isReversedL = true;
    	if(reverse.charAt(1) == 'R')
    		isReversedR = true;
    	
    	PathGen curPath = new PathGen();
    	curPath.generateTrajectory(points[0], isReversedL, isReversedR);
    	left = curPath.getLeftTraj();
    	right = curPath.getRightTraj();
    	
    	if(display) {
    		DrawPath routine = new DrawPath();
    		JFrame frame = new JFrame("RobotPath");
    		frame.setSize(1080, 2060);
    		frame.add(routine);
    		frame.setVisible(true);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    		routine.run(left, right, points[0]);
    	}
    	if(save)
    		curPath.writeTraj(pathName);
    	
	}
	
	public static double inchesToFeet(double inches) {
		return inches/12.0;
	}

}