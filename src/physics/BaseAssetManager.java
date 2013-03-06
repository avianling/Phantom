package physics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import math.Vector;

import core.Configuration;
import exceptions.AssetException;
import geometry.Polygon;
import geometry.ShapeFactory;

public class BaseAssetManager extends ShapeFactory implements AssetManager {

	private HashMap<String, PhysicsAsset > assetStore; 
	
	private String _mediaDir;
	
	public BaseAssetManager( String mediaDir)
	{
		_mediaDir = mediaDir;//Configuration.getMediaDir();
		assetStore = new HashMap<String, PhysicsAsset >();
		
		ShapeFactory.setShapeFactory(this);
	}
	
	@Override
	public PhysicsAsset loadAsset(String assetName) throws AssetException {
		if ( assetStore.containsKey(assetName) )
		{
			System.out.println("Serving an asset from the store.");
			return assetStore.get(assetName);
		} else {
			// must load from a file.
			try
			{
				ObjectInputStream s = new ObjectInputStream( new FileInputStream(_mediaDir + assetName + ".p") );
			
				Object o = s.readObject();
				
				PolygonAsset asset = new PolygonAsset( (Vector[])o );
				assetStore.put(assetName, asset);
				return asset; 
			}
			catch ( FileNotFoundException e )
			{
				e.printStackTrace();
				throw new AssetException("Unable to find asset file");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new AssetException("Invalid asset file");
			} catch (IOException e )
			{
				e.printStackTrace();
				throw new AssetException("Unable to find asset file");
			}
		}
	}

	public Polygon getPolygonFromAsset(String assetName) throws AssetException {
		// get the asset
		PhysicsAsset asset = loadAsset(assetName);
		
		if ( asset instanceof PolygonAsset )
		{
			return new Polygon( ((PolygonAsset)asset).getPoints() );
		} else {
			throw new AssetException("Asset is not a polygon");
		}
	}

}
