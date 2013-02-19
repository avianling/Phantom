package physics;


/**
 * A manager for control and simulating collisions.
 * @author Alexander
 *
 */
public interface CollisionManager {
	public void simulate();
	
	public void add(CollisionBody object);
	
	public void remove(CollisionBody object);
	
	public AssetManager getAssetManager();
}
