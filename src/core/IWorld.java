package core;

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
	
}
