package physics;

import exceptions.AssetException;

public interface AssetManager {
	
	public PhysicsAsset loadAsset(String assetName ) throws AssetException;
	
}
