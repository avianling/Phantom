package core;

import input.EKey;
import input.IKeyListener;
import input.IMouseListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exceptions.SerializerNotLoadedException;

import physics.Collidable;
import physics.CollisionManager;

public class BaseWorld implements IWorld, IKeyListener, IMouseListener {

	// a list of all of the things which are drawable in the game world.
	//private ArrayList<Drawable> drawingList;
	//private DrawableTreeNode drawableTreeRoot;
	private ArrayList<Dynamic> dynamicList;
	
	// A map relating each drawing layer key to a seperate list of drawings.
	private HashMap<EDrawingLayer, List<Drawable> > drawingLayerMap;
	
	/**
	 * The collision manager used for managing collisions in this world.
	 */
	private CollisionManager collisionManager;
	
	
	public void setCollisionManager( CollisionManager collisionManager )
	{
		this.collisionManager = collisionManager;
	}
	
	public CollisionManager getCollisionManager()
	{
		return collisionManager;
	}
	
	// the time at which the last frame was calculated.
	private long nextTick;
	private int millisPerFrame;
	
	public BaseWorld()
	{
		dynamicList = new ArrayList<Dynamic>();
		
		drawingLayerMap = new HashMap<EDrawingLayer, List<Drawable> >();
		// for each drawing layer which exists, create a list to hold the objects which are at that depth.
		for ( EDrawingLayer layer : EDrawingLayer.values() )
		{
			System.out.println("Creating the list of drawable objects");
			ArrayList<Drawable> list = new ArrayList< Drawable >();
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
	
	/**
	 * checkCollisions
	 * <br/>
	 * If we have a valid collision manager, check to see if any collisions have occured.
	 * Collision managers will automatically notify objects of collisions via callbacks.
	 */
	public void checkCollisions()
	{
		if ( collisionManager != null )
		{
			collisionManager.simulate();
		}
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
	
	
	/**
	 * void : simulate
	 * Simulate is the function which causes the game world to advance one tick.
	 * This should also make things draw themselves by firing the draw event in the displayModel.
	 */
	@Override
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
			checkCollisions();
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
	
	/**
	 * <h4>Clear</h4>
	 * Delete all the objects in the world by calling the delete method on each object.
	 * <br/>
	 * If you wish to add events which are fired when an object is deleted, please see the onDelete method of the BaseObject class.
	 */
	@Override
	public void clear()
	{
		for ( EDrawingLayer l : EDrawingLayer.values() )
		{
			List<Drawable> list = drawingLayerMap.get(l);
			while ( list.size() != 0 )
			{
				((GameObject)list.get(0)).delete();
			}
		}
		
		while ( dynamicList.size() != 0 )
		{
			if ( GameObject.class.isInstance(dynamicList.get(0)))
			{
				GameObject obj = (GameObject)dynamicList.get(0);
				obj.delete();
			}
		}
	}

	@Override
	/**
	 * Remove all references to the provided object.
	 * Currently not thread safe.
	 */
	public void delete(GameObject object) {
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

	/**
	 * Save the current state of the world file into a JSON style text file.
	 * e.g.
	 *  {
	 * 		objectName : { fieldName : fieldValue, vectorExample : { X : 32, Y : 23 } },
	 * 		other object : {}
	 *  }
	 *  
	 *  currently, this does not allow the saving of reference types. Will probably have an object naming system where each object gets
	 *  a unique name which can be used to get a reference to that object. 
	 *  
	 *  <h4> Warning: </h4>
	 *  <p> Currently, only objects which extend BaseObject will be saved. </p>
	 */
	@Override
	public void save(String worldName) {
		// Save the world to a given filename.
		// for every object we can find, record their name and position, rotation etc.
		
		try {
			// make everything dirty:
			for ( EDrawingLayer l : EDrawingLayer.values() )
			{
				List<Drawable> list = drawingLayerMap.get(l);
				
				for ( Drawable d : list )
				{
					if ( d instanceof BaseObject )
					{
						((BaseObject)d).clean=false;
					}
				}
			}
			
			for ( Dynamic d : dynamicList )
			{
				if ( d instanceof BaseObject )
				{
					((BaseObject)d).clean=false;
				}
			}
			
			// now, for every object which is not already clean, we save it and make it clean.
			Serializer saver = new FileSerializer();
			saver.startSession(worldName);
			
			boolean first = true;
			
			for ( EDrawingLayer l : EDrawingLayer.values() )
			{
				List<Drawable> list = drawingLayerMap.get(l);
				
				for ( Drawable d : list )
				{
					// write the objects information to the record. 
					if ( d instanceof BaseObject )
					{
						BaseObject b = (BaseObject)d;
						
						if ( b.clean == false )
						{
							b.clean = true;
							if ( d instanceof Savable )
							{
								Savable s = (Savable)b;
								saver.startObject( d.getClass().getName() );
								s.save(saver);
								saver.endObject();
							}
						}
					}
				}
			}
			
			for ( Dynamic d : dynamicList )
			{
				// write the objects information to the record. 
				if ( d instanceof BaseObject )
				{
					BaseObject b = (BaseObject)d;
					
					if ( b.clean == false )
					{
						b.clean = true;
						if ( d instanceof Savable )
						{
							Savable s = (Savable)b;
							saver.startObject( d.getClass().getName() );
							s.save(saver);
							saver.endObject();
						}
					}
				}
			}
			
			saver.endSession();
			
			
		} catch (IOException | SerializerNotLoadedException e) {
			e.printStackTrace();
		}
	}

}
