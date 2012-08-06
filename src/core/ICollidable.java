package core;

public interface ICollidable extends IDrawable, IDynamic {
	/**
	 * A callback which is initialized whenever this object collides with another.
	 * @param other : whatever we collided with.
	 */
	public void collisionEvent( ICollidable other );
	
	/**
	 * Checks if a collision occurs between this object and the specific point.
	 * @param x
	 * @param y
	 * @return true if a collision occurs, false otherwise.
	 */
	public boolean collision( float x, float y );
	
	/**
	 * Checks if a collision occurs between this object and the specified other object.
	 * @param other : the object we are checking for collision with.
	 * @return 
	 */
	public boolean collision( ICollidable other );
	
	/**
	 * Checks if the object collides at any point with the given line.
	 * @param iX
	 * @param iY
	 * @param dX
	 * @param dY
	 * @return a t value at which the collision occurs along the line.
	 */
	public float collision( float iX, float iY, float dX, float dY);
}
