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
		boolean isReversed = false;
		
    	points = new Waypoint[] {
    			new Waypoint(inchesToMeters(0),inchesToMeters(290), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(16*12-2*12),inchesToMeters(290+2*12), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(16*12+8*12-2*12), inchesToMeters(290), Pathfinder.d2r(290)),
    			new Waypoint(inchesToMeters(16*12+8*12-2*12), inchesToMeters(290-2*12), Pathfinder.d2r(200)),
    			new Waypoint(inchesToMeters(16*12+8*12-5*12-2*12),inchesToMeters(290-2*12-2*12), Pathfinder.d2r(170)),
    			new Waypoint(inchesToMeters(16*12+8*12-5*12-2*12-2*12),inchesToMeters(290-2*12-2*12), Pathfinder.d2r(150)),
//    			new Waypoint(inchesToMeters(275), inchesToMeters(300), Pathfinder.d2r(270)),
//    			new Waypoint(inchesToMeters(255), inchesToMeters(265), Pathfinder.d2r(220)),
//    			new Waypoint(inchesToMeters(210), inchesToMeters(270), Pathfinder.d2r(160)),
    			

    	};
    	
    	/*
    	 *     			new Waypoint(inchesToMeters(0),inchesToMeters(290), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(100),inchesToMeters(320), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(165),inchesToMeters(300), Pathfinder.d2r(360-45)),
    			new Waypoint(inchesToMeters(215), inchesToMeters(250), Pathfinder.d2r(360-100)),
    			new Waypoint(inchesToMeters(215), inchesToMeters(100), Pathfinder.d2r(360-100)),
    			new Waypoint(inchesToMeters(255), inchesToMeters(30), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(270), inchesToMeters(40), Pathfinder.d2r(55)),
    	 */
    	
    	/*
    	 *     			new Waypoint(inchesToMeters(0),inchesToMeters(290), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(100), inchesToMeters(310), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(180), inchesToMeters(310), Pathfinder.d2r(0)),
    			new Waypoint(inchesToMeters(285),inchesToMeters(295), Pathfinder.d2r(310)),
    	 */
    	//backup turn: p1(285, 275, 310) p2(220, 300, Z90)
    	
    	//switch right (center): p1(0,162,0) p2(110,100,0)
    	
    	//switch left (center): p1(0,162,0) p2(115,230,0)
    	
    	//straight scale left: p1(0,290,0) p2(100,310,0) p3(285, 275, 310)
    	
    	//straight switch left: p1(0,290,0) p2(100,320,0) p3(200,320,0) p4(265,310,300)
    	
    	//Left start righ Scale: p1(0,290,0) p2(180,300,0) p3(200,260,360-100) p4(200-34, 64, 360-100) p5(220-34, 34, 0) p6(255-34, 45, 70)
    	
    	
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("path name: ");
    	String pathName = sc.nextLine();
    	System.out.println("R for reverse, F for forward");
    	String reverse = sc.nextLine();
    	if(reverse.charAt(0) == 'R')
    		isReversed = true;
    	
    	PathGen curPath = new PathGen();
    	curPath.generateTrajectory(points, isReversed);
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
