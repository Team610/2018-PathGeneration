import java.awt.BasicStroke;
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
	
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(1));
		for(int i=1; i<27; i++) {
			g2d.drawLine(feetToPixelsX(i), feetToPixelsY(0), feetToPixelsX(i), feetToPixelsY(54));
		}
		
		for(int i=1; i<54; i++) {
			g2d.drawLine(feetToPixelsX(0), feetToPixelsY(i), feetToPixelsX(27), feetToPixelsY(i));
		}
		
		g2d.setColor(Color.GREEN);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(feetToPixelsX(0), feetToPixelsY(27), feetToPixelsX(27), feetToPixelsY(27));

		
		g2d.setColor(Color.BLUE);
		g2d.drawRect(feetToPixelsX(8), feetToPixelsY(21.98), feetToPixelsX(11)-feetToPixelsX(0), feetToPixelsY(10.042)-feetToPixelsY(0)); //platform
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(feetToPixelsX(6), feetToPixelsY(25), feetToPixelsX(15)-feetToPixelsX(0), feetToPixelsY(4)-feetToPixelsY(0)); //scale + plates
		
		g2d.fillRect(feetToPixelsX(7.1), feetToPixelsY(37.67), feetToPixelsX(12.8)-feetToPixelsX(0), feetToPixelsY(4.667)-feetToPixelsY(0)); //switch close side
		
		//null zone markers
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(feetToPixelsX(0), feetToPixelsY(24), feetToPixelsX(8), feetToPixelsY(24));
		g2d.drawLine(feetToPixelsX(19), feetToPixelsY(24), feetToPixelsX(27), feetToPixelsY(24));
		g2d.drawLine(feetToPixelsX(0), feetToPixelsY(30), feetToPixelsX(8), feetToPixelsY(30));
		g2d.drawLine(feetToPixelsX(19), feetToPixelsY(30), feetToPixelsX(27), feetToPixelsY(30));
		
		//auto line
		g2d.setColor(Color.BLACK);
		g2d.drawLine(feetToPixelsX(0), feetToPixelsY(44), feetToPixelsX(27), feetToPixelsY(44));
		
		//power cube zone markers
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(feetToPixelsX(11.625), feetToPixelsY(37.67+4.667), feetToPixelsX(3.75)-feetToPixelsX(0), feetToPixelsY(3.5)-feetToPixelsY(0));
		
		//power cubes along switch
		for(int i=0; i<6; i++) {
			//g2d.fillRect(feetToPixelsX(7.1), feetToPixelsY(37.67-1.083), feetToPixelsX(1.083)-feetToPixelsX(0), feetToPixelsY(1.083)-feetToPixelsY(0));
			g2d.fillRect(feetToPixelsX(7.1+(1.26*i)+(1.0833*i)), feetToPixelsY(37.67-1.083), feetToPixelsX(1.083)-feetToPixelsX(0), feetToPixelsY(1.083)-feetToPixelsY(0));
		}
		//portals
		g2d.setStroke(new BasicStroke(7));
		g2d.setColor(Color.BLACK);
		g2d.drawLine(feetToPixelsX(0), feetToPixelsY(51), feetToPixelsX(2.5), feetToPixelsY(54));
		g2d.drawLine(feetToPixelsX(24.5), feetToPixelsY(54), feetToPixelsX(27), feetToPixelsY(51));

		
		
		//way points
		g2d.setColor(Color.MAGENTA);
		for(Waypoint i:targets) {
			int x = feetToPixelsX(27-i.y);
			int y = feetToPixelsY(54-i.x);
			g2d.fillOval(x-20, y-20, 40, 40);
		}
		
		//trajectory points
		for(int i=0; i<left.length(); i++) {
			g2d.setColor(Color.BLUE);
			int xL = feetToPixelsX(27-left.segments[i].y);
			int yL = feetToPixelsY(54-left.segments[i].x);
			g2d.fillOval(xL-5, yL-5, 10, 10);
			
			g2d.setColor(Color.RED);
			int xR = feetToPixelsX(27-right.segments[i].y);
			int yR = feetToPixelsY(54-right.segments[i].x);
			g2d.fillOval(xR-5, yR-5, 10, 10);
		}
		
		//outside box
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(10));
		g2d.drawRect(feetToPixelsX(0), feetToPixelsY(0), feetToPixelsX(27)-48, feetToPixelsY(54)-15); //48,15,984,1965
	}
	
	public void run(Trajectory left, Trajectory right, Waypoint[] points) throws InterruptedException {
		this.left = left; this.right = right;
		targets = points;
		repaint();
		
	}
	
	
	public int feetToPixelsX(double feet) {
		return (int)(48+feet*36.34); 
	}
	
	public int feetToPixelsY(double feet) {
		return (int)(15+feet*36.34);
	}
	
}
