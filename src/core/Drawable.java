package core;

/**
 * Drawable is the parent class for things which need to be drawn.
 * @author Alexander
 *
 */
public interface Drawable {
	
	/**
	 * The all important draw method. This will make the drawable object draw itself,
	 * using whichever is the current displaymodel as specified in the Configuration
	 * superfile.
	 */
	public void draw();
}
