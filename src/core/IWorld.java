package core;

import math.Vector;
import physics.CollisionManager;

/**
 * IWorld interface is the interface used to simulate the environment
 * things which implement the IWorld class should be singletons.
 * @author alex
 *
 */
public interface IWorld {
	
	public void add( Object obj );
	
	public void display();
	
	public IWorld initalize();
	
	public void simulate();

	public void terminate();
	
	public void clear();
	
	public void delete( GameObject object );
	
	public void save( String worldName );

	/**
	 * setCollisionManager
	 * <br/>
	 * Set which collision manager will be used for this world. 
	 * This is not required - a world can be build and ran without a collision manager by not supplying this value or by supplying a null value.
	 * @param collisionManager
	 */
	public void setCollisionManager(CollisionManager collisionManager);

	/**
	 * getCollisionManager
	 * <br/>
	 * Fetch the collision manager is use by the world.
	 * @return
	 */
	public CollisionManager getCollisionManager();
	
}
