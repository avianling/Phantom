package core;

import input.EKey;
import input.IKeyListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseWorld implements IWorld, IKeyListener {

	// a list of all of the things which are drawable in the game world.
	//private ArrayList<Drawable> drawingList;
	//private DrawableTreeNode drawableTreeRoot;
	private ArrayList<Collidable> collidableList;
	private ArrayList<Dynamic> dynamicList;
	
	// A map relating each drawing layer key to a seperate list of drawings.
	private HashMap<EDrawingLayer, List<Drawable> > drawingLayerMap;
	
	// the time at which the last frame was calculated.
	private long nextTick;
	private int millisPerFrame;
	
	public BaseWorld()
	{
		//drawingList = new ArrayList<Drawable>();
		//drawableTreeRoot = new DrawableTreeNode();
		collidableList = new ArrayList<Collidable>();
		dynamicList = new ArrayList<Dynamic>();
		
		drawingLayerMap = new HashMap<EDrawingLayer, List<Drawable> >();
		// for each drawing layer which exists, create a list to hold the objects which are at that depth.
		for ( EDrawingLayer layer : EDrawingLayer.values() )
		{
			System.out.println("Creating the list of drawable objects");
			ArrayList list = new ArrayList< Drawable >();
			drawingLayerMap.put(layer, list );
		}
		
		drawingLayerMap.get(EDrawingLayer.background);
		
		// make this world an event listener.
		//BaseEventModel.getEventModel().addListener(this);
		initalize();
		
		millisPerFrame = 1000 / Configuration.getFPS();
		nextTick = System.currentTimeMillis()+millisPerFrame;
	}
	
	@Override
	public void add(Object obj) {
		// here we are supposed to add an object to the world.
		// determine what type it is and then add the object to the appropriate array
		// of objects.
		if ( Drawable.class.isInstance(obj) )
		{
			// add to the drawing list.
			Drawable temp = (Drawable)obj;
			drawingLayerMap.get(temp.getDepth()).add(temp);
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
		/*for ( Drawable e : drawingList )
		{
			e.draw();
		}*/
		for ( EDrawingLayer layer : EDrawingLayer.values() )
		{
			for ( Drawable d : drawingLayerMap.get(layer))
			{
				d.draw();
			}
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
		// do all the setup for the world
		return null;
	}

	@Override
	public void terminate() {
		// cleanup the world.
	}

	
	@Override
	public void KeyPressed(EKey key) {}

	@Override
	public void KeyReleased(EKey key) {}

}
