package core;

import math.Vector;

/**
 * base Object is the superclass for things which have a real tangible game presence.
 * Essentially, anything which has a position and size is a real object and will 
 * inherit from BaseObject.
 * @author Alexander
 *
 */
public class BaseObject implements GameObject {
	
	// the getters for the position and widths and so on.
	public Vector position() { return _position; };
	public Vector speed() { return _speed; };
	public Vector bounds() { return _bounds; };
	public Vector scale() { return _scale; };
	public Vector offset() { return _offset; };
	public float rotation() { return _rotation; };
	public float angularSpeed() { return _angularSpeed; };
	
	/**
	 * The x and x coordinates of the object are stored in the protected vector _position<br>
	 * Coordinates relate to the position of the top left hand corner of the object, rather than the center<br/>
	 * These should be set by using the <b>setPosition( Vector newPosition )</b> method.
	 */
	protected Vector _position;
	
	protected Vector _scale;
	
	protected float _rotation;
	
	/**
	 * The offset of an object is where the centre of the object is - rotation and scaling are performed around this point.
	 */
	protected Vector _offset;
	
	
	/**
	 * The speed of the object, expressed as a vector.
	 */
	protected Vector _speed;
	
	protected float _angularSpeed;
	

	/**
	 * the width and height of the object.<br/>
	 * used for calculating the boundaries of the object.
	 * This is an integer for some weird reason, I figured a float would be a bit pointless.
	 */
	protected Vector _bounds;
	
	/**
	 * This function will set the position of the game object in the world.
	 */
	public void setPosition( Vector newPosition )
	{
		_position = newPosition;
	}
	
	/**
	 * A function to set the speed of the object.
	 * @param dX
	 * @param dY
	 */
	public void setSpeed( Vector newSpeed )
	{
		_speed = newSpeed;
	}
	
	public void setAngularSpeed( float newAngularSpeed )
	{
		_angularSpeed = newAngularSpeed;
	}
	
	public void setScale( Vector newScale )
	{
		_scale = newScale;
	}
	
	public void setOffset( Vector newOffset )
	{
		_offset = newOffset;
	}
	
	public void setRotation( float newRotation )
	{
		_rotation = newRotation;
	}
	
	/**
	 * A function to set the width and height of this object. Not sure it it'll be used.
	 * @param w
	 * @param h
	 */
	public void setBounds( Vector newBounds )
	{
		_bounds = newBounds;
	}
	
	/**
	 * The default constructor.<br/>
	 * This is mainly used to specify default values for things.
	 */
	/*public void RealObject()
	{
		setPosition(0,0);
		setBounds(32,32);
		
		// add ourselves to the world class.
		Configuration.getWorldModel().add(this);
	}*/
	
	
	public void step()
	{
		setPosition( position().add(speed()) );
		setRotation( rotation() + angularSpeed() );
	}
}
