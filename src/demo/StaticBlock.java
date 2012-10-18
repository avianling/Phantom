package demo;

import math.Vector;
import core.BaseObject;
import core.Collidable;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;
import core.GameObject;

public class StaticBlock extends BaseObject implements Collidable, Drawable {

	public StaticBlock( float x, float y, int w, int h )
	{
		setPosition(new Vector(x,y));
		setBounds(new Vector(w,h));
		
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle(position(), bounds());
	}

	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.background;
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("something stood on me");
		
		// do nothing
	}

	@Override
	public boolean collision(Collidable other) {
		if ( GameObject.class.isInstance(other) )
		{
			GameObject o = (GameObject)other;
			
			double xmin = position().X;
			double ymin = position().Y;
			double xmax = position().X + bounds().X;
			double ymax = position().Y + bounds().Y;
			
			double oxmin = o.position().X;
			double oymin = o.position().Y;
			double oxmax = o.position().X + o.bounds().X;
			double oymax = o.position().Y + o.bounds().Y;
			
			if ( oxmax < xmin ) { return false; }; // if the other is to the right of us, no collision
			if ( oxmin > xmax ) { return false; }; // if the other is to the left ....
			if ( oymax < ymin ) { return false; }; // if the other is above...
			if ( oymin > ymax ) { return false; }; // if the other is below...
			
			return true;
		}
		return false;
	}

	@Override
	public boolean collision(float x, float y) {
		/*if ( x > _X && x < ( _X + _width ) && y > _Y && y < ( _Y + _height ) )
		{
			return true;
		}
		else 
		{
			return false;
		}*/
		return false;
	}
	
}
