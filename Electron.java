
/**
 * A Class to represent a massive, electrically charged particle
 * @author Ian Bailey
 * @version 1.0
 */
public final class Electron extends Particle{

	protected boolean electron = true;

	/**
	 * The Default Constructor. Sets everything to zero except the mass and charge of the proton.
	 *
	 */
	public Electron(){
		super();
	}

	
	/**
	 *  Constructor that sets mass, charge, position and velocity
	 *  @param mIn mass of the proton (ignored)
	 *  @param qIn charge of the proton (ignored)
	 *  @param positionIn initial position of proton
	 *  @param velocityIn initial velocity of proton 
	 */
	public Electron(double positionIn, double timeIn)
	{
		super(positionIn, timeIn);
	}
	/**
	 *  Copy Constructor 
	 *  @param particleIn proton whose properties are to be copied to the new proton
	 */
	public Electron(Electron particleIn)
	{
		super(particleIn.position, particleIn.time);
	}

	/**
	 *  Method to set the properties of the proton equal to those of another proton
	 *  @param posIn proton whose properties are to be copied to 'this' proton
	 */
	public void set(Electron posIn)
	{
		setPosition(posIn.position);
	}
	
	/**
	 *  Method to set the properties of the proton equal to those of another proton
	 *  @param protonIn proton whose properties are to be copied to 'this' proton
	 */
	public void set(Hole partIn)
	{
		setPosition(partIn.position);
	}
}


