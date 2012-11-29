package isoGame;

import core.BaseObject;

/**
 * isoBase is the base class for isometric tile based objects.
 * The significant difference between normal objects and isobase is that
 * isobase stores the position of which tile it is in, ignores rotation
 * and has a helper function tileToReal which converts tile coordinates into real position coordinates.
 * 
 * Tiles are numbered with the top left being 0,0.
 * @author Alexander
 *
 */
public class isoBase extends BaseObject {
	public isoBase()
	{
		super();
	}
}
