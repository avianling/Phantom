package isoGame;

import core.Drawable;

/**
 * isoDrawable is an extension of drawable which allows the programmer to specify a numeric depth within a drawing layer. 
 * @author Alexander
 *
 */
public interface isoDrawable extends Drawable, Comparable<isoDrawable>  {
	/**
	 * return the depth of a drawable object within the layer it resides in. 
	 * @return int depth - the depth of an object in its layer. 
	 */
	public int getDepth();
	
	public int compareTo(isoDrawable other);
}
