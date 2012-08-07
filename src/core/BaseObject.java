package core;

/**
 * base Object is the superclass for things which have a real tangible game presence.
 * Essentially, anything which has a position and size is a real object and will 
 * inherit from BaseObject.
 * @author Alexander
 *
 */
public class BaseObject implements GameObject {
	
	// the getters for the position and widths and so on.
	public float X() { return _X; };
	public float Y() { return _Y; };
	public float W() { return _width; };
	public float H() { return _height; };
	public float dX() { return _dX; };
	public float dY() { return _dY; };
	
	/**
	 * The x and x coordinates of the object are stored in the protected fields _X and _Y.<br>
	 * Coordinates relate to the position of the top left hand corner of the object, rather than the center<br/>
	 * These should be set by using the <b>setPosition( float x, float y )</b> method.
	 */
	protected float _X, _Y;
	
	
	/**
	 * The speed of the object, expressed as the speeds on the x and y axis.
	 */
	protected float _dX, _dY;
	

	/**
	 * the width and height of the object.<br/>
	 * used for calculating the boundaries of the object.
	 * This is an integer for some weird reason, I figured a float would be a bit pointless.
	 */
	protected int _width, _height;
	
	/**
	 * This function will set the position of the game object in the world.
	 * @param x - the desired x coordinate.
	 * @param y - the desired y corrdinate.
	 */
	public void setPosition( float x, float y )
	{
		_X = x;
		_Y = y;
	}
	
	/**
	 * A function to set the speed of the object.
	 * @param dX
	 * @param dY
	 */
	public void setSpeed( float dX, float dY )
	{
		_dX = dX;
		_dY = dY;
	}
	
	/**
	 * A function to set the width and height of this object. Not sure it it'll be used.
	 * @param w
	 * @param h
	 */
	public void setBounds( int w, int h )
	{
		_width = w;
		_height = h;
	}
	
	/**
	 * The default constructor.<br/>
	 * This is mainly used to specify default values for things.
	 */
	public void RealObject()
	{
		setPosition(0,0);
		setBounds(32,32);
		
		// add ourselves to the world class.
		Configuration.getWorldModel().add(this);
	}
	
	
	public void step()
	{
		setPosition( X() + dX(), Y() + dY() );
	}
}
