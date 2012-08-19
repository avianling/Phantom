package graphics;

/**
 * An interface for drawing things in a 2D space.
 * @author alex
 *
 */
public interface IDisplayModel {
	
	// setup the object.
	public void initalize();
	
	// calls the draw routine.
	public void draw();
	
	// draws a simple rectangle.
	public void drawRectangle( float X, float Y, float W, float H);
	
	// draws an image at the given position
	public void drawImage( Object image, float x, float y );
}
