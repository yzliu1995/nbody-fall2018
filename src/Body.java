/**
 * Create a body from parameters
 * @param xp initial x position
 * @param yp initial y position
 * @param xv initial x velocity
 * @param yv initial y velocity
 * @param mass of object
 * @param filename of image for object animation
 */

public class Body{
	
	private double myXPos, myYPos, myXVel, myYVel, myMass;
	private String myFileName;
	
	
	public Body(double xp, double yp, double xv,
			double yv, double mass, String filename) {
		this.myXPos = xp;
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myFileName = filename;
	}
	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public Body(Body b){
		this.myXPos = b.myXPos;
		this.myYPos = b.myYPos;
		this.myXVel = b.myXVel;
		this.myYVel = b.myYVel;
		this.myMass = b.myMass;
		this.myFileName = b.myFileName;	
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel(){
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(Body b) {
		return Math.sqrt((this.myXPos - b.myXPos)*(this.myXPos - b.myXPos)+ (this.myYPos - b.myYPos)*(this.myYPos - b.myYPos));
	}
	
	public double calcForceExertedBy(Body b) {
		return 6.67*1e-11*this.myMass*b.myMass/calcDistance(b)/calcDistance(b);
	}
	
	public double calcForceExertedByX(Body p) {
		return (- this.myXPos + p.myXPos)/calcDistance(p)*calcForceExertedBy(p);
	}
	
	public double calcForceExertedByY(Body p) {
		return (- this.myYPos + p.myYPos)/calcDistance(p)*calcForceExertedBy(p);
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		double NetForce = 0;
		for(Body b:bodies) {
			if(!b.equals(this)) {
				NetForce = NetForce + calcForceExertedByX(b);
			}
		}
		return NetForce;	
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double NetForce = 0;
		for(Body b:bodies) {
			if(!b.equals(this)) {
				NetForce = NetForce + calcForceExertedByY(b);
			}
		}
		return NetForce;	
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		 this.myXVel = myXVel + deltaT * xforce / myMass;
		 this.myYVel = myYVel + deltaT * yforce / myMass;
		 this.myXPos = myXPos + deltaT * this.myXVel;
		 this.myYPos = myYPos + deltaT * this.myYVel;
	}
	
}