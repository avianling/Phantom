package physics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

import math.Vector;

import core.Configuration;

public class BaseAssetManager implements AssetManager {

	private HashMap<String, PhysicsAsset > assetStore; 
	
	private String mediaDir;
	
	public BaseAssetManager()
	{
		mediaDir = Configuration.getMediaDir();
		assetStore = new HashMap<String, PhysicsAsset >();
	}
	
	@Override
	public PhysicsAsset loadAsset(String assetName) {
		if ( assetStore.containsKey(assetName) )
		{
			System.out.println("Serving an asset from the store.");
			return assetStore.get(assetName);
		} else {
			// must load from a file.
			try
			{
				ObjectInputStream s = new ObjectInputStream( new FileInputStream(mediaDir + assetName + ".p") );
			
				Object o = s.readObject();
				
				PolygonAsset asset = new PolygonAsset( (Vector[])o );
				assetStore.put(assetName, asset);
				return asset;
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				return null;
			}
		}
	}

}
