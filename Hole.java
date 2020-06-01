
/**
 * A Class to represent a massive, electrically charged particle
 * @author Ian Bailey
 * @version 1.0
 */
public final class Hole extends Particle{

	protected boolean electron = false;

	/**
	 * The Default Constructor. Sets everything to zero except the mass and charge of the proton.
	 *
	 */
	public Hole(){
		super();
		this.setType(false);
	}

	
	/**
	 *  Constructor that sets mass, charge, position and velocity
	 *  @param mIn mass of the proton (ignored)
	 *  @param qIn charge of the proton (ignored)
	 *  @param positionIn initial position of proton
	 *  @param velocityIn initial velocity of proton 
	 */
	public Hole(double positionIn, double timeIn)
	{
		super(positionIn, timeIn);
		this.setType(false);

	}

	/**
	 *  Copy Constructor 
	 *  @param particleIn proton whose properties are to be copied to the new proton
	 */
	public Hole(Hole particleIn)
	{
		super(particleIn.position, particleIn.time);
		this.setType(false);
	}

	/**
	 *  Method to set the properties of the proton equal to those of another proton
	 *  @param protonIn proton whose properties are to be copied to 'this' proton
	 */
	public void set(Hole partIn)
	{
		setPosition(partIn.position);
	}
	
	/**
	 *  Method to set the properties of the proton equal to those of another proton
	 *  @param protonIn proton whose properties are to be copied to 'this' proton
	 */
	public void set(Electron partIn)
	{
		setPosition(partIn.position);
	}
}


