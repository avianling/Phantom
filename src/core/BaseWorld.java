package core;

import input.EKey;
import input.IKeyListener;
import input.IMouseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import physics.Collidable;

public class BaseWorld implements IWorld, IKeyListener, IMouseListener {

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
		
		// make this world an event listener.
		//Configuration.getEventModel().addKeyListener(this);		
		
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
			drawingLayerMap.get(temp.getLayer()).add(temp);
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
			Configuration.getEventModel().addKeyListener((IKeyListener)obj);
		}
		
		// if the object is listening for mouse input, register it as a mouse listener.
		if ( IMouseListener.class.isInstance(obj) )
		{
			Configuration.getEventModel().addMouseListener((IMouseListener)obj);
		}
	}
	
	public void checkCollisions()
	{
	}
	
	public void display() {
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
			// run the objects step events.
			for ( Dynamic d : dynamicList )
			{
				d.step();
			}
			
			// check weather any collisions have happened.
			Configuration.getCollisionManager().simulate();
			//System.out.println("Event Fired!");
			
			
			// update the timers.
			nextTick = System.currentTimeMillis() + millisPerFrame;
		}
		
		Configuration.getDisplayModel().draw();
	}

	@Override
	public IWorld initalize() {
		// do all the setup for the world
		Configuration.getEventModel().addMouseListener(this);
		
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

	@Override
	public void leftPressed(int mouseX, int mouseY) {}

	@Override
	public void leftReleased(int mouseX, int mouseY) {}

	@Override
	public void rightPressed(int mouseX, int mouseY) {}

	@Override
	public void rightReleased(int mouseX, int mouseY) {}

	// Variables for holding the mouse positions.
	private int _mouseX, _mouseY;
	
	public int mouseX() { return _mouseX; };
	public int mouseY() { return _mouseY; };
	
	@Override
	public void mouseMoved(int mouseX, int mouseY) {
		_mouseX = mouseX;
		_mouseY = mouseY;
	}
	
	@Override
	public void clear()
	{
		// TODO: Delete all references to all objects.
	}

	@Override
	/**
	 * Remove all references to the provided object.
	 * Currently not thread safe.
	 */
	public void delete(GameObject object) {
		// TODO Auto-generated method stub
		if ( Drawable.class.isInstance(object) )
		{
			Drawable d = (Drawable)object;
			EDrawingLayer layer = d.getLayer();
			drawingLayerMap.get(layer).remove(d);
		}
		
		
		// if the object is dynamic, add the object to the dynamic list.
		if ( Dynamic.class.isInstance(object) )
		{
			dynamicList.remove((Dynamic)object);
		}
		
		if ( Collidable.class.isInstance(object) )
		{
			collidableList.remove((Collidable)object);
		}
		
		// if the object is able to receive key input, add it to the event listeners observers list.
		if ( IKeyListener.class.isInstance(object) )
		{
			Configuration.getEventModel().removeKeyListener((IKeyListener)object);
		}
		
		// if the object is listening for mouse input, register it as a mouse listener.
		if ( IMouseListener.class.isInstance(object) )
		{
			Configuration.getEventModel().removeMouseListener((IMouseListener)object);
		}
		
	}

}
