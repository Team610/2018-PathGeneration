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
		boolean isReversedR = false, isReversedL = false;
		
    	points = new Waypoint[] {
    			//backwards left scale 1 cube
    			/*
    			new Waypoint(inchesToMeters(0),inchesToMeters(290), Pathfinder.d2r(0)),
    			//new Waypoint(inchesToMeters(80),inchesToMeters(290), Pathfinder.d2r(0)),    
    			new Waypoint(inchesToMeters(262),inchesToMeters(188), Pathfinder.d2r(360-40)),
    			*/
    			
    			
    			//second cube left scale
    			/*
    			new Waypoint(inchesToMeters(262),inchesToMeters(250), Pathfinder.d2r(140)),
    			new Waypoint(inchesToMeters(200),inchesToMeters(240), Pathfinder.d2r(0)),
    			*/
    			
    			//shoot the second cube
    			/*
    			new Waypoint(inchesToMeters(200),inchesToMeters(240), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(260),inchesToMeters(250), Pathfinder.d2r(10)),
    			*/
    			
    			//drive forwards 2 cube right side
    			/*
    			new Waypoint(inchesToMeters(0),inchesToMeters(290), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(150),inchesToMeters(290), Pathfinder.d2r(0)),
    			*/
    			
    			//backwards right scale 1 cube part one
    			/*
    			new Waypoint(inchesToMeters(0),inchesToMeters(270), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(180),inchesToMeters(270), Pathfinder.d2r(0)),    
    			new Waypoint(inchesToMeters(200),inchesToMeters(240), Pathfinder.d2r(70)), //270
    			 */
    			
    			//backwards right scale 1 cube part two
    			/*
    			new Waypoint(inchesToMeters(200),inchesToMeters(240), Pathfinder.d2r(70)),
    			new Waypoint(inchesToMeters(200),inchesToMeters(140), Pathfinder.d2r(270)), //270 //100 y coordinate when last point
    			*/
    			
    			//backwards right scale 1 cube part two
    			/*
    			new Waypoint(inchesToMeters(200),inchesToMeters(140), Pathfinder.d2r(270)),
    			new Waypoint(inchesToMeters(280),inchesToMeters(40), Pathfinder.d2r(270)),
    			*/
    			
    			//pickup second cube right scale
    			/*
    			new Waypoint(inchesToMeters(324),inchesToMeters(0), Pathfinder.d2r(270)),
    			new Waypoint(inchesToMeters(200),inchesToMeters(100), Pathfinder.d2r(0)),
    			*/
    			
    			//backwards switch pt1
    			/*
    			new Waypoint(inchesToMeters(0),inchesToMeters(290), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(220),inchesToMeters(290), Pathfinder.d2r(0)),
    			*/
    			
    			//drive straight left scale
    			/*
    			new Waypoint(inchesToMeters(0),inchesToMeters(290), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(100),inchesToMeters(310), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(300),inchesToMeters(310), Pathfinder.d2r(0)),
    			*/
    			
    			//left scale 90 turn
    			/*
    			new Waypoint(inchesToMeters(0),inchesToMeters(0), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(25),inchesToMeters(0), Pathfinder.d2r(0)),
    			*/
    			
    			//drive straight auto
    			
    			new Waypoint(inchesToMeters(0),inchesToMeters(250), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(110),inchesToMeters(250), Pathfinder.d2r(0)), //110,320,90
    			

    	};
    	
    	//backup turn: p1(285, 275, 310) p2(220, 300, Z90)
    	
    	//switch right (center): p1(0,162,0) p2(110,100,0)
    	
    	//switch left (center): p1(0,162,0) p2(115,220,0) used to be 230
    	
    	//straight scale left: p1(0,290,0) p2(100,310,0) p3(285, 275, 310)
    	
    	//straight switch left: p1(0,290,0) p2(100,320,0) p3(200,320,0) p4(265,310,300)
    	
    	//Left start right Scale: p1(0,290,0) p2(180,300,0) p3(200,260,360-100) p4(200-34, 64, 360-100) p5(220-34, 34, 0) p6(255-34, 45, 70)
    	
    	
    	
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
    	curPath.generateTrajectory(points, isReversedL, isReversedR);
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