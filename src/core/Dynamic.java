package core;

/**
 * Dynamic is the superclass for objects which have interactive bahavior.<br/>
 * Those objects which inherit from Dynamic must supply some step method which is called every game cycle.<br>
 * Note: Object collision checking is treated differently from dynamic objects. An object which is collidable is not automatically dynamic and vice versa.
 * @author Alexander
 *
 */
public interface Dynamic extends GameObject {
		
	/**
	 * The step function. This is run each game cycle for Dynamic objects.
	 */
	public void step();
	
}
