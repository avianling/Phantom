package graphics;

import math.Vector;

/**
 * IView
 * @author Alexander
 *
 */
public class View {
	
	private Vector position;
	private Vector bounds;
	
	public void setBounds( Vector vector )
	{
		this.bounds = vector;
	}
	
	public void setPosition( Vector position )
	{
		this.position = position;
	}
	
	public Vector getBounds()
	{
		return bounds;
	}
	
	public Vector getPosition()
	{
		return position;
	}
}	
