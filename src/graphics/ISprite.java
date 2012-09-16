package graphics;

/**
 * Animation is an interface for animated sprites.
 * 
 * @author Alexander
 *
 */
public interface ISprite {
	
	/**
	 * A method which returns the current frame of the animation.
	 * The precise object returned is left as an implementation detail.
	 * @return an image representing the current frame of the animation
	 */
	/*public Object getCurrentFrame();*/
	// not using?
	
	/**
	 * A method which increments the frame of the animation.
	 * @return true if this is the last frame of the animation, false otherwise.
	 */
	public boolean advance();
	
	/** A method to draw the sprite at the given position
	 * @param x the x position
	 * @param y the y position
	 */
	public void draw( float x, float y );
	
	/**
	 * A method to draw the sprite at a given position and rotation.
	 * @param x The x position
	 * @param y The y position
	 * @param rotation The rotation of the sprite. 
	 */
	public void draw( float x, float y, float rotation);
}
