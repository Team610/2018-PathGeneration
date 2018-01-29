import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.Segment;

public class DrawPath extends JPanel {
	double xLeft, yLeft, xRight, yRight;
	Waypoint[] targets;
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		g2d.fillOval(500-(int) yLeft, 500-(int)xLeft, 10, 10);
		g2d.setColor(Color.RED);
		g2d.fillOval(500-(int)yRight, 500-(int)xRight, 10, 10);
		g2d.setColor(Color.GREEN);
		for(Waypoint i:targets) {
			int x = metersToPixels(i.x)+500; //+500 is for centering the points on the 1000 x 1000 display
			int y = metersToPixels(i.y)+500;
			g2d.fillOval(1000-y, 1000-x, 15, 15); //1000 - makes the bottom corner the center of the screen 
		}
		
		g2d.setColor(Color.BLACK);
		
		for(int i = 0; i<1000/50; i++) { //gridlines for reference
			g2d.drawLine(i*50, 0, i*50, 1000); 
			g2d.drawLine(0, i*50, 1000, i*50);
		}

	}
	public void run(double xPosLeft,double yPosLeft, double xPosRight, double yPosRight, Waypoint[] points) throws InterruptedException {
		Thread.sleep(5);
		xLeft = metersToPixels(xPosLeft); yLeft = metersToPixels(yPosLeft);
		xRight = metersToPixels(xPosRight); yRight = metersToPixels(yPosRight);
		targets = points;
		repaint();
		
	}
	
	
	public int metersToPixels(double meters) {
		return (int)(meters*50); //50 is a scaling factor just to make the points more visible
	}
	
	
	
}
