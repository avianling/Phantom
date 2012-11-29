package core;

import math.Vector;

public interface GameObject {

	// Getters and setters for positions and speeds etc.
	public Vector position();
	public Vector bounds();
	public Vector speed();
	public Vector offset();
	public Vector scale();
	public float rotation();
	public float angularSpeed();
	
	public void setPosition( Vector newPosition );
	
	public void setSpeed( Vector newSpeed );
	
	public void setBounds( Vector newBounds );
	
	public void setScale( Vector newScale );
	
	public void setOffset( Vector newOffset );
	
	public void setRotation( float newRotation );
	
	public void setAngularSpeed( float newAngularSpeed );
	
	/**
	 * This method should prepare the object for deletion.
	 * i.e. clear any and all references to the object, including those in the world.
	 * Should also clear any outgoing references.
	 * This is a required method - failure to provide it will result in undesired game behaviour.
	 */
	public void delete();
}
