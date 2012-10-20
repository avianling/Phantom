package demo;

import physics.Collidable;
import physics.CollisionBody;
import physics.Polygon;
import math.Vector;
import graphics.AnimatedSprite;
import graphics.ISprite;
import input.EKey;
import input.IKeyListener;
import core.BaseObject;
import core.Configuration;
import core.Drawable;
import core.Dynamic;
import core.EDrawingLayer;
import core.GameObject;

public class PlayerDemo extends BaseObject implements Dynamic, Drawable, Collidable, IKeyListener {
	
	private boolean canJump;
	
	private CollisionBody body;
	
	// While this countdown is true we don't collide with walls or solid objects.
	private int jumpingCountdown;
	
	public PlayerDemo()
	{
		//super();
		
		Vector[] points = { new Vector(0,0), new Vector(24,0), new Vector(24,32), new Vector(0,32) };
		body = new Polygon(points);
		body.setParent(this);
		
		setPosition(new Vector(50,50));
		setSpeed(new Vector(0,0));
		setBounds(new Vector(24,32));
		Configuration.getWorldModel().add(this);
		
		canJump = false;
		jumpingCountdown = 0;
		
		body.setPosition(new Vector(50,50));
	}
	
	@Override
	public void setPosition( Vector newPosition)
	{
		_position = newPosition;
		body.setPosition(newPosition);
	}
	
	@Override
	public void KeyPressed(EKey key) {
		if ( key == EKey.KeyLeft )
		{
			// Move to the left
			//_dX = -4;
			setSpeed( new Vector( -4, speed().Y) );
		}
		if ( key == EKey.KeyRight )
		{
			setSpeed( new Vector( 4, speed().Y) );
		}
		
		if ( key == EKey.KeyUp && canJump )
		{
			// A crude way of jumping - give us a high upwards velocity.
			setSpeed( speed().add(new Vector(0,-8) ) );
			canJump = false;
			jumpingCountdown = 8;
		}
	}

	@Override
	public void KeyReleased(EKey key) {
		if ( key == EKey.KeyLeft )
		{
			// Move to the left
			setSpeed( speed().multiply(new Vector(0,1) ) );
		}
		
		if ( key == EKey.KeyRight )
		{
			setSpeed( speed().multiply(new Vector(0,1) ) );
		}
	}
	
	@Override
	public void step()
	{
		// Move according to out speed.
		setPosition( position().add(speed()) );
		//_dY += 0.2;
		setSpeed( speed().add(new Vector(0,0.02f) ) );
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
			setSpeed( speed().multiply(new Vector(1,0)) );
		}
		
		// if the other object is above us, we always fall even if jumpingCountdown is running.
		if ( BaseObject.class.isInstance(other) )
		{
			BaseObject o = (BaseObject)other;
			
			// If they are below us, we can jump again.
			if ( o.position().Y >= position().Y )
			{
				// we can jump again
				canJump = true;
				
				// move to the collision position.
				//setPosition(X(), o.Y() - H() );
				setPosition( new Vector( position().X, o.position().Y - bounds().Y ) );
			}
			else
			{
				// If we hit something above us, then we start falling again.
				jumpingCountdown = 0;
				//_dY = 1;
				setSpeed( new Vector(speed().X, 1 ) );
				
				// jump to the collision position, so that we don't end up halfway inside the object.
				//setPosition(X(), o.Y() + o.H());
				setPosition( new Vector( position().X, o.position().Y + o.bounds().Y ) );
			}
		}
	}

	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle( position(), bounds());
	}

	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.foreground;
	}

}
