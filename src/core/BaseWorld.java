package core;

import input.EKey;
import input.IKeyListener;

import java.util.ArrayList;

public class BaseWorld implements IWorld, IKeyListener {

	// a list of all of the things which are drawable in the game world.
	private ArrayList<IDrawable> drawingList;
	private ArrayList<ICollidable> collidableList;
	private ArrayList<IDynamic> dynamicList;
	
	// the time at which the last frame was calculated.
	private long nextTick;
	private int millisPerFrame;
	
	public BaseWorld()
	{
		drawingList = new ArrayList<IDrawable>();
		collidableList = new ArrayList<ICollidable>();
		dynamicList = new ArrayList<IDynamic>();
		
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
		if ( IDrawable.class.isInstance(obj) )
		{
			// add to the drawing list.
			drawingList.add((IDrawable) obj );
		}
		
		// if the object is collidable, the put it into the collidable list.
		if ( ICollidable.class.isInstance(obj) )
		{
			collidableList.add((ICollidable)obj);
		}
		
		// if the object is dynamic, add the object to the dynamic list.
		if ( IDynamic.class.isInstance(obj) )
		{
			dynamicList.add((IDynamic)obj);
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
		for ( ICollidable left : collidableList )
		{
			for ( int i=start; i < collidableList.size(); i++ )
			{
				start++;
				ICollidable right = collidableList.get(i);
				if ( left.collision(right) )
				{
					left.collisionEvent(right);
					right.collisionEvent(left);
				}
			}
		}
	}
	
	public void display() {
		// TODO Fill out this function. should make all the drawable object draw themselves.
		for ( IDrawable e : drawingList )
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
			for ( IDynamic d : dynamicList )
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
