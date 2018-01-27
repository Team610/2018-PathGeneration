

public class Segment {
	private double x,y,position,velocity,acceleration,heading,time;
	
	public Segment(double x, double y, double position, double velocity, double acceleration, double heading, double time) {
		this.x = x; this.y=y;this.position=position;this.velocity=velocity;this.acceleration=acceleration;this.heading=heading;this.time=time;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getPos() {
		return position;
	}

	public double getVelocity() {
		return velocity;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public double getHeading() {
		return heading;
	}

	public double getTime() {
		return time;
	}

}
