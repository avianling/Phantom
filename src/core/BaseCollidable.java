package core;

import physics.Collidable;
import math.Vector;

public class BaseCollidable extends BaseObject implements Collidable, Drawable {
	
	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.ground;
	}
	
	public void step()
	{
		//System.out.println("Lolwut");
	}
	
	public BaseCollidable()
	{
		super();
		setPosition(new Vector(0,0));
		setSpeed(new Vector(0,0));
		setBounds(new Vector(32,32));
		Configuration.getWorldModel().add(this);
	}
	
	public BaseCollidable( float x, float y, float w, float h )
	{
		super();
		Configuration.getWorldModel().add(this);
		setPosition(new Vector(x,y));
		setBounds(new Vector((int)w,(int)h));
		Configuration.getWorldModel().add(this);
	}

	@Override
	public void draw() {
		//System.out.println("Draw method called for base collidable.");
		Configuration.getDisplayModel().drawRectangle(position(),bounds());
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("A collision has occured!");
	}
}
