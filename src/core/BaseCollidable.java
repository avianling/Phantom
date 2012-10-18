package core;

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

	@Override
	public boolean collision(float x, float y) {
		float X, Y, W, H;
		X = (float) position().X;
		Y = (float) position().Y;
		W = (float) bounds().X;
		H = (float) bounds().Y;
		
		
		if ( x > X && y > Y && x < (X+W) && y < (Y+H) )
		{
			return true;
		}
		
		return false;
	}

	// Broken.
	public boolean collision(Collidable other) {
		float X, Y, W, H;
		X = (float) position().X;
		Y = (float) position().Y;
		W = (float) bounds().X;
		H = (float) bounds().Y;
		
		// check if a collision occurs between this object and the other.
		
		// first check that the other object is one which we can collide with.
		if (Collidable.class.isInstance(other))
		{	
			float otherX = other.position().X - X;
			float otherY = other.position().Y - Y;
			
			// if their start position is within our bounds, we collide.
			if ( otherY > H ) { return false;}
			if ( otherX > W ) { return false;}
			if ( otherX < -other.bounds().X ) { return false; }
			if ( otherY < -other.bounds().Y ) { return false; }
			
			return true;
		}
		
		return false;
	}
}
