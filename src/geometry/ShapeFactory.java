package geometry;

import exceptions.AssetException;

/**
 * A factory for producing shapes.
 * @author alexander.boorsboom
 *
 */
public abstract class ShapeFactory {
	public abstract Polygon getPolygonFromAsset( String assetName ) throws AssetException;
	
	private static ShapeFactory _singleton;
	
	public static ShapeFactory get() throws Exception {
		//TODO: What happens if no shapefactory has been set?
		if ( _singleton != null )
		{
			return _singleton;
		} else {
			throw new Exception( "No shape factory has been defined." );
		}
	}
	
	public static void setShapeFactory( ShapeFactory factory )
	{
		_singleton = factory;
	}
}
