package core;

public class BaseCollidable extends BaseObject implements Collidable, Drawable {
	
	@Override
	public int getDepth() {
		return 0;
	}
	
	public void step()
	{
		//System.out.println("Lolwut");
	}
	
	public BaseCollidable()
	{
		super();
		setPosition(0,0);
		setSpeed(0,0);
		setBounds(32,32);
		Configuration.getWorldModel().add(this);
	}
	
	public BaseCollidable( float x, float y, float w, float h )
	{
		super();
		Configuration.getWorldModel().add(this);
		setPosition(x,y);
		setBounds((int)w,(int)h);
		Configuration.getWorldModel().add(this);
	}

	@Override
	public void draw() {
		//System.out.println("Draw method called for base collidable.");
		Configuration.getDisplayModel().drawRectangle(X(),Y(),W(),H());
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("A collision has occured!");
	}

	@Override
	public boolean collision(float x, float y) {
		if ( x > X() && y > Y() && x < (X()+W()) && y < (Y()+H()) )
		{
			return true;
		}
		
		return false;
	}

	// Broken.
	public boolean collision(Collidable other) {
		// check if a collision occurs between this object and the other.
		
		// first check that the other object is one which we can collide with.
		if (Collidable.class.isInstance(other))
		{	
			float otherX = other.X() - X();
			float otherY = other.Y() - Y();
			
			// if their start position is within our bounds, we collide.
			if ( otherY > H() ) { return false;}
			if ( otherX > W() ) { return false;}
			if ( otherX < -other.W() ) { return false; }
			if ( otherY < -other.H() ) { return false; }
			
			return true;
		}
		
		return false;
	}
}
