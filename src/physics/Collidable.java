package physics;

import core.GameObject;

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
}
