package graphics;

import math.Vector;

/**
 * A View is a portion of the game world which is visible.
 * @author alexander.boorsboom
 *
 */
public class View {
	private Vector _position;
	private Vector _size;
	
	/**
	 * Construct a view with the default values for size and position.
	 * 
	 */
	public View()
	{
		_position = new Vector( 0, 0 );
		_size = new Vector( 640, 480 );
	}
	
	/**
	 * Construct a view with the given dimensions.
	 * @param position
	 * @param size
	 */
	public View( Vector position, Vector size )
	{
		_position = position;
		_size = size;
	}
	
	/**
	 * Change the position of the view in the game world, relative to the origin.
	 * @param position
	 */
	public void setPosition( Vector position )
	{
		_position = position;
	}
	
	/**
	 * Change the size of this view.
	 * Should notify the graphics model of the change in size.
	 * @param size
	 */
	public void setSize( Vector size )
	{
		_size = size;
	}
	
	public Vector getPosition()
	{
		return _position;
	}
	
	public Vector getSize()
	{
		return _size;
	}
}
