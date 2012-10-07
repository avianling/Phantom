package demo;

import core.BaseObject;
import core.Collidable;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;
import core.GameObject;

public class StaticBlock extends BaseObject implements Collidable, Drawable {

	public StaticBlock( float x, float y, int w, int h )
	{
		setPosition(x,y);
		setBounds(w,h);
		
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle(X(), Y(), W(), H());
	}

	@Override
	public EDrawingLayer getDepth() {
		// TODO Auto-generated method stub
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
			
			double xmin = X();
			double ymin = Y();
			double xmax = X() + W();
			double ymax = Y() + H();
			
			double oxmin = o.X();
			double oymin = o.Y();
			double oxmax = o.X() + o.W();
			double oymax = o.Y() + o.H();
			
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
		if ( x > _X && x < ( _X + _width ) && y > _Y && y < ( _Y + _height ) )
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
}
