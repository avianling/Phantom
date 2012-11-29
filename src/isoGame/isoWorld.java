package isoGame;

import math.Vector;
import core.IWorld;

/**
 * isoWorld is a variant and extension of IWorld.
 * isoWorld contains the specific information required to make a valid isometric game(?)
 * -> Tile size
 * -> Tile occupied by...
 * -> is a given tile passable?
 * -> Ways of notifying the world about where objects are.
 * etc
 * @author Alexander
 *
 */
public interface isoWorld extends IWorld {
	/**
	 * returns the width of a tile.
	 * Assumes all tiles are cubes.
	 * @return size of a tile along any axis.
	 */
	public float getTileSize();
	
	/**
	 * A method to return the object contained within a given tile.
	 * @param tile Which tile to check
	 * @return The object in the tile as an isoBase object.
	 */
	public isoBase objectInTile(Vector tile);
	
	/**
	 * This method notifies the world about a change in an objects position.
	 * This is used to keep the tile occupancy maps up to date.
	 * @param object
	 * @param oldPosition
	 * @param newPosition
	 */
	public void updatePosition( isoBase object, Vector oldPosition, Vector newPosition);
	
	/**
	 * A helped method to convert from isometric coordinates to real XY coordinates.
	 * @param isometricCoordinates position in isometric space
	 * @return position in real space
	 */
	public Vector isoToReal( Vector isometricCoordinates );
}
