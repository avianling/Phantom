package demo;

import graphics.AnimatedSprite;
import graphics.ISprite;
import input.EKey;
import input.IKeyListener;
import core.BaseObject;
import core.Collidable;
import core.Configuration;
import core.Drawable;
import core.Dynamic;
import core.EDrawingLayer;
import core.GameObject;

public class PlayerDemo extends BaseObject implements Dynamic, Drawable, Collidable, IKeyListener {
	
	private boolean canJump;
	
	// While this countdown is true we don't collide with walls or solid objects.
	private int jumpingCountdown;
	
	public PlayerDemo()
	{
		//super();
		
		setPosition(50,50);
		setBounds(24,32);
		Configuration.getWorldModel().add(this);
		
		canJump = false;
		jumpingCountdown = 0;
	}
	
	@Override
	public void KeyPressed(EKey key) {
		if ( key == EKey.KeyLeft )
		{
			// Move to the left
			_dX = -4;
		}
		if ( key == EKey.KeyRight )
		{
			_dX = 4;
		}
		
		if ( key == EKey.KeyUp && canJump )
		{
			// A crude way of jumping - give us a high upwards velocity.
			_dY = - 8;
			jumpingCountdown = 8;
		}
	}

	@Override
	public void KeyReleased(EKey key) {
		if ( key == EKey.KeyLeft )
		{
			// Move to the left
			_dX = 0;
		}
		
		if ( key == EKey.KeyRight )
		{
			_dX = 0;
		}
	}
	
	@Override
	public void step()
	{
		// Move according to out speed.
		setPosition( X() + _dX, Y() + _dY );
		_dY += 0.2;
		if ( jumpingCountdown >= 1 )
		{
			jumpingCountdown--;
		}
	}

	@Override
	public void collisionEvent(Collidable other) {	
		// Stop falling.
		if ( jumpingCountdown < 1 )
		{
			_dY = 0;
		}
		
		// if the other object is above us, we always fall even if jumpingCountdown is running.
		if ( BaseObject.class.isInstance(other) )
		{
			BaseObject o = (BaseObject)other;
			
			// If they are below us, we can jump again.
			if ( o.Y() >= Y() )
			{
				// we can jump again
				canJump = true;
				
				// move to the collision position.
				setPosition(X(), o.Y() - H() );
			}
			else
			{
				// If we hit something above us, then we start falling again.
				jumpingCountdown = 0;
				_dY = 1;
				
				// jump to the collision position, so that we don't end up halfway inside the object.
				setPosition(X(), o.Y() + o.H());
			}
		}
	}

	@Override
	public boolean collision(Collidable other) {
		
		// A tempory sort of collision checking
		// assumes all objects are rectangular.
		if ( BaseObject.class.isInstance(other) )
		{
			BaseObject o = (BaseObject)other;
			
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

	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle( X(), Y(), W(), H());
	}

	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.foreground;
	}

}
