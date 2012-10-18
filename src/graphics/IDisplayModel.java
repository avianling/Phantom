package graphics;

import math.Vector;

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
	public void drawRectangle( Vector position, Vector bounds);
	
	// draws an image at the given position
	public void drawImage( Object image, Vector position );
	
	public void drawImage( Object image, Vector position, float rotation);
	
	public void drawLine(Vector start, Vector end );
}
