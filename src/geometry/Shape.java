package geometry;

import math.Vector;

public interface Shape {
	
	/**
	 * Return the position of the shape.
	 * @return the position of the shape relative to the origin.
	 */
	public Vector getPosition();
	
	
	public void setPosition( Vector position );
	
	/**
	 * A method to calculate the axis aligned bounding box for this shape.
	 * @return
	 */
	public AARectangle getBoundingBox();
}
