package core;

/**
 * Collidable is the superclass for object where they are able to collide with other objects.<br/>
 * Objects will only ever collide with other collidable objects, and collisions will trigger a collisionEvent method call.
 * @author Alexander
 *
 */
public interface Collidable extends GameObject {

	/**
	 * A callback which is initialized whenever this object collides with another.
	 * @param other : whatever we collided with.
	 */
	public abstract void collisionEvent( Collidable other );
	
	/**
	 * Checks if a collision occurs between this object and the specified other object.
	 * @param other : the object we are checking for collision with.
	 * @return  true is a collision occurs, otherwise false.
	 */
	public abstract boolean collision( Collidable other );
	
	/**
	 * Checks if a collision occurs between this object and the specific point.
	 * @param x
	 * @param y
	 * @return true if a collision occurs, false otherwise.
	 */
	public abstract boolean collision( float x, float y );
}
