package core;

import math.Vector;

public interface GameObject {

	// Getters and setters for positions and speeds etc.
	public Vector position();
	public Vector bounds();
	public Vector speed();
	public Vector scale();
	public float rotation();
	
	public void setPosition( Vector newPosition );
	
	public void setSpeed( Vector newSpeed );
	
	public void setBounds( Vector newBounds );
	
	public void setScale( Vector newScale );
	
	public void setRotation( float newRotation );
}
