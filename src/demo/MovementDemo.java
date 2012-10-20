package demo;

import physics.Collidable;
import math.Vector;
import core.BaseObject;
import core.Dynamic;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;
import input.EKey;
import input.IKeyListener;

public class MovementDemo extends BaseObject implements Dynamic, Drawable, Collidable, IKeyListener {

	public MovementDemo()
	{
		super();
		setPosition(new Vector(128,128));
		setBounds(new Vector(32,32));
		setSpeed(new Vector(0,0));
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.ground;
	}
	
	@Override
	public void KeyPressed(EKey key) {
		if ( key == EKey.KeyLeft)
		{
			setSpeed( speed().add(new Vector(-4,0)) );
		}
		if ( key == EKey.KeyRight )
		{
			setSpeed(speed().add(new Vector(4,0)));
		}
		if ( key==EKey.KeyUp )
		{
			setSpeed(speed().add(new Vector(0,-4)));
		}
		if ( key==EKey.KeyDown )
		{
			setSpeed(speed().add(new Vector(0,4)));
		}
	}

	@Override
	public void KeyReleased(EKey key) {
		if ( key==EKey.KeyLeft || key==EKey.KeyRight )
		{
			setSpeed(speed().multiply(new Vector(0,1)) );
		}
		if ( key==EKey.KeyUp || key==EKey.KeyDown )
		{
			setSpeed( speed().multiply(new Vector(1,0)) );
		}
	}

	public void draw() {
		Configuration.getDisplayModel().drawRectangle(position(), bounds());
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("A collision occured!");
	}
	
}
