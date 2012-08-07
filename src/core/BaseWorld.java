package core;

import input.EKey;
import input.IKeyListener;

import java.util.ArrayList;

public class BaseWorld implements IWorld, IKeyListener {

	// a list of all of the things which are drawable in the game world.
	private ArrayList<Drawable> drawingList;
	private ArrayList<Collidable> collidableList;
	private ArrayList<Dynamic> dynamicList;
	
	// the time at which the last frame was calculated.
	private long nextTick;
	private int millisPerFrame;
	
	public BaseWorld()
	{
		drawingList = new ArrayList<Drawable>();
		collidableList = new ArrayList<Collidable>();
		dynamicList = new ArrayList<Dynamic>();
		
		// make this world an event listener.
		//BaseEventModel.getEventModel().addListener(this);
		initalize();
		
		millisPerFrame = 1000 / Configuration.getFPS();
		nextTick = System.currentTimeMillis()+millisPerFrame;
	}
	
	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		// here we are supposed to add an object to the world.
		// determine what type it is and then add the object to the appropriate array
		// of objects.
		if ( Drawable.class.isInstance(obj) )
		{
			// add to the drawing list.
			drawingList.add((Drawable) obj );
		}
		
		// if the object is collidable, the put it into the collidable list.
		if ( Collidable.class.isInstance(obj) )
		{
			collidableList.add((Collidable)obj);
		}
		
		// if the object is dynamic, add the object to the dynamic list.
		if ( Dynamic.class.isInstance(obj) )
		{
			dynamicList.add((Dynamic)obj);
		}
		
		// if the object is able to receive key input, add it to the event listeners observers list.
		if ( IKeyListener.class.isInstance(obj) )
		{
			Configuration.getEventModel().addListener(obj);
		}
	}
	
	public void checkCollisions()
	{
		int start = 0;
		for ( Collidable left : collidableList )
		{
			for ( int i=start; i < collidableList.size(); i++ )
			{
				Collidable right = collidableList.get(i);
				if ( left.collision(right) )
				{
					left.collisionEvent(right);
					right.collisionEvent(left);
				}
			}
			start++;
		}
	}
	
	public void display() {
		// TODO Fill out this function. should make all the drawable object draw themselves.
		for ( Drawable e : drawingList )
		{
			e.draw();
		}
	}
	
	@Override
	/**
	 * void : simulate
	 * Simulate is the function which causes the game world to advance one tick.
	 * This should also make things draw themselves by firing the draw event in the displayModel.
	 */
	public void simulate()
	{
		// check if we need to make a new calculation.
		if ( System.currentTimeMillis() >= nextTick )
		{
			// check weather any collisions have happened.
			checkCollisions();
			//System.out.println("Event Fired!");
			
			// run the objects step events.
			for ( Dynamic d : dynamicList )
			{
				d.step();
			}
			
			// update the timers.
			nextTick = System.currentTimeMillis() + millisPerFrame;
		}
		
		Configuration.getDisplayModel().draw();
	}

	@Override
	public IWorld initalize() {
		// TODO Auto-generated method stub
		// do all the setup for the world
		return null;
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		// cleanup the world.
	}

	
	@Override
	public void KeyPressed(EKey key) {}

	@Override
	public void KeyReleased(EKey key) {}

}
