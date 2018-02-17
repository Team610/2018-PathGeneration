import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class DrawPath extends JPanel {
	private Trajectory left, right;
	//private double xLeft, yLeft, xRight, yRight;
	Waypoint[] targets;
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Image field = Toolkit.getDefaultToolkit().getImage("field.jpg");
		g2d.drawImage(field, 0, 0, this);
		
		/*
		g2d.setColor(Color.blue);
		g2d.fillOval(500-(int) yLeft, 500-(int)xLeft, 10, 10);
		g2d.setColor(Color.RED);
		g2d.fillOval(500-(int)yRight, 500-(int)xRight, 10, 10);
		*/
		for (int i=0; i<left.length(); i++) {
			g2d.setColor(Color.blue);
			g2d.fillOval(435-metersToPixels(left.segments[i].y), 854 - metersToPixels(left.segments[i].x), 10, 10); //height is actually 864 but need 10 pixel offset
			g2d.setColor(Color.red);
			g2d.fillOval(435-metersToPixels(right.segments[i].y), 854 - metersToPixels(right.segments[i].x), 10, 10);
		}
		
		g2d.setColor(Color.GREEN);
		for(Waypoint i:targets) {
			//x and y are swapped because of the pathfinder library, normally x is forward and y is side to side
			int x = metersToPixels(i.y); //+ is for centering on 435 pixels
			int y = metersToPixels(i.x);
			g2d.fillOval(435-x, 854-y, 15, 15); //854 - makes the bottom corner the 0,0 
		}
		/*
		g2d.setColor(Color.BLACK);
		for(int i = 0; i<1000/50; i++) { //gridlines for reference
			g2d.drawLine(i*50, 0, i*50, 1000); 
			g2d.drawLine(0, i*50, 1000, i*50);
		}
		*/

	}
	public void run(Trajectory left, Trajectory right, Waypoint[] points) throws InterruptedException {
		this.left = left; this.right = right;
		targets = points;
		repaint();
		
	}
	
	
	public int metersToPixels(double meters) {
		return (int)(meters*39.37*1.3); //39.37 inches in a mseter, then 1.3 pixels per inch scale factor
	}
	
}
