
/**
 * A Class to represent a massive particle
 *  It can have position, velocity and mass
 * @author Andy Niblett
 * @version 1.6
 */
public class Particle{
	// NB Private => only visible inside this class
	// Protected => visible inside this class AND any subclasses
	protected double position, startPos, time, startTime;
	protected boolean electron=true;
	
	/**
	 * The Default Constructor. Sets everything to zero.
	 *
	 */
	public Particle(){
		startTime=0;
		position = 0;
		time=0;
		startPos=0;
	}

	
	public Particle(double posIn, double timeIn){
		position = posIn;
		startPos = posIn;
		time = timeIn;
		startTime = timeIn;
	}

	/**
	 *  Copy Constructor 
	 *  @param particleIn particle whose properties are to be copied to the new particle
	 */
	public Particle(Particle particleIn)
	{
		this(particleIn.position, particleIn.time);
	}

	/**
	 * Return the position
	 *
	 * @return position
	 */
	public double getPosition()
	{
		return position;
	}

	/**
	 * Set the position
	 *
	 * @param pIn The new position
	 */
	public void setPosition(double pIn)
	{
		position = pIn;
	}

	public double getTime()
	{
		return time;
	}

	public void setTime(double timeIn)
	{
		time = timeIn;
	}
	
	public boolean getType()
	{
		return electron;
	}

	
	public void setType(boolean el)
	{
		electron = el;
	}
	
	public void increasePos(double d) {
		position += d;
	}
	
	public void decreasePos(double d) {
		position -= d;
	}
	
	
	public double distance() {
		return Math.abs(position-startPos);
	}
	
	public String toString()
	{
		return "Time: " + time + " Position: "+position + " Type: "+electron;
	}
}


