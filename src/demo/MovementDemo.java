package demo;

import core.BaseObject;
import core.Collidable;
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
		setPosition(128,128);
		setBounds(32,32);
		setSpeed(0,0);
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
			setSpeed(-4,dY());
		}
		if ( key == EKey.KeyRight )
		{
			setSpeed(4,dY());
		}
		if ( key==EKey.KeyUp )
		{
			setSpeed(dX(),-4);
		}
		if ( key==EKey.KeyDown )
		{
			setSpeed(dX(),4);
		}
	}

	@Override
	public void KeyReleased(EKey key) {
		if ( key==EKey.KeyLeft || key==EKey.KeyRight )
		{
			setSpeed(0,dY());
		}
		if ( key==EKey.KeyUp || key==EKey.KeyDown )
		{
			setSpeed(dX(), 0 );
		}
	}

	public void draw() {
		Configuration.getDisplayModel().drawRectangle(X(), Y(), W(), H());
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("A collision occured!");
	}

	@Override
	public boolean collision(Collidable other) {
		return false;
	}

	@Override
	public boolean collision(float x, float y) {
		return false;
	}
	
}
