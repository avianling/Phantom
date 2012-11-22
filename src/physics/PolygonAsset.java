package physics;

import math.Vector;

/**
 * PolygonAsset is a readonly asset containing the base physics information for a polygon.
 * all polygons of a particular type 
 * @author alexander.boorsboom
 *
 */
public class PolygonAsset implements PhysicsAsset {
	private final Vector[] points;
	
	public PolygonAsset( Vector[] points )
	{
		this.points = points;
	}
	
	public Vector[] getPoints() {
		return points;
	}
}
