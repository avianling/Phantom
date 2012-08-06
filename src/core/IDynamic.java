package core;

public interface IDynamic {
	
	/**
	 * step is a function which is called every game loop.
	 * Objects which should inherit dynamic are those which
	 * are mobile and have interactive behavior in the game world.
	 */
	public void step();
	
	/**
	 * applyVelocity updates the position of this object based
	 * on its velocity.
	 * V. important
	 */
	public void applyVelocity();

	public void setSpeed( float dX, float dY );

	// get the x position of the object
	public float X();
	public float Y();
	public float dX();
	public float dY();
	
}

