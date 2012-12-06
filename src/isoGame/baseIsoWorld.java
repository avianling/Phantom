package isoGame;

import input.EKey;
import input.IKeyListener;
import input.IMouseListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import math.Vector;

import core.Configuration;
import core.Drawable;
import core.Dynamic;
import core.EDrawingLayer;
import core.GameObject;

import physics.Collidable;

public class baseIsoWorld implements isoWorld, IKeyListener, IMouseListener {

	// a list of all of the things which are drawable in the game world.
	//private ArrayList<Drawable> drawingList;
	//private DrawableTreeNode drawableTreeRoot;
	private ArrayList<Collidable> collidableList;
	private ArrayList<Dynamic> dynamicList;
	
	// A map relating each drawing layer key to a seperate list of drawings.
	// For isoworld, stores things which do not have a depth partial order
	private HashMap<EDrawingLayer, List<Drawable> > drawingLayerMap;
	
	// This is similar to the above list, except that its contents have some attached depth information.
	// i.e. are ordered by depth and layer.
	private HashMap<EDrawingLayer, List<isoDrawable> > isoDrawingLayerMap;
	
	// A list mapping each tile on the game board to the object occupying that board.
	private HashMap<Vector, isoBase > tileMap;
	
	// the time at which the last frame was calculated.
	private long nextTick;
	private int millisPerFrame;
	
	private int tileSize;
	
	public baseIsoWorld()
	{
		//drawingList = new ArrayList<Drawable>();
		//drawableTreeRoot = new DrawableTreeNode();
		collidableList = new ArrayList<Collidable>();
		dynamicList = new ArrayList<Dynamic>();
		
		tileMap = new HashMap<Vector, isoBase>();
		tileSize = 32;
		
		drawingLayerMap = new HashMap<EDrawingLayer, List<Drawable> >();
		isoDrawingLayerMap = new HashMap<EDrawingLayer, List<isoDrawable> >();
		
		// for each drawing layer which exists, create a list to hold the objects which are at that depth.
		for ( EDrawingLayer layer : EDrawingLayer.values() )
		{
			System.out.println("Creating the list of drawable objects");
			ArrayList list = new ArrayList< Drawable >();
			drawingLayerMap.put(layer, list );
			
			ArrayList isoList = new ArrayList< isoDrawable>();
			isoDrawingLayerMap.put(layer, isoList);
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

		// If the incoming object is drawable, but not isometric - i.e. has no depth information, put it into the drawing layer map.
		// otherwise, it goes into the isoDrawingLayerMap somewhere, based off its depth.
		if ( Drawable.class.isInstance(obj) )
		{
			if ( isoDrawable.class.isInstance(obj) )
			{
				isoDrawable temp = (isoDrawable)obj;
				System.out.println(temp.getLayer());
				/*int depth = temp.getDepth();
				int i = 0; // <- our progress through the layer
				int position = 0; // <- which position we will occupy.
				for ( isoDrawable other : isoDrawingLayerMap.get(temp.getLayer()) )
				{
					if ( depth < other.getDepth() )
					{
						position = i;
						break;
					}
					i++;
				}*/
				
				// stick the object in at the appropriate depth.
				isoDrawingLayerMap.get(temp.getLayer()).add(temp);
				
				
				
			} else {
				
				Drawable temp = (Drawable)obj;
				drawingLayerMap.get(temp.getLayer()).add(temp);
			}
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
	
	public void display() {
		for ( EDrawingLayer layer : EDrawingLayer.values() )
		{
			// Sort the list of objects in the depth based map and then draw them.
			// TODO: change this to quick sort. Currently is merge sort.
			Collections.sort(isoDrawingLayerMap.get(layer));
			for ( isoDrawable d : isoDrawingLayerMap.get(layer)) 
			{
				d.draw();
			}
			
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
	public baseIsoWorld initalize() {
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
	public float getTileSize() {
		return tileSize;
	}

	@Override
	public isoBase objectInTile(Vector tile) {
		return tileMap.get(tile);
	}

	@Override
	public void updatePosition(isoBase object, Vector oldPosition, Vector newPosition) {
		tileMap.remove(oldPosition);
		tileMap.put(newPosition, object);
	}

	@Override
	public Vector isoToReal(Vector iso) {
		return new Vector( (float)(tileSize*(iso.X - iso.Y)*0.8660254), (float)(tileSize*(iso.X+iso.Y)*0.5) );	
	}
	
	public Vector realToIso(Vector real)
	{
		float yi = (float)((real.Y * 0.8660254) / (real.X ));
		float xi = (real.Y / (2*tileSize) ) - yi;
		return new Vector(xi,yi);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String worldName) {
		// TODO Auto-generated method stub
		
	}

}
