package core;

public class BaseCollidable implements ICollidable {

	private float x,y, width, height;
	
	
	
	public BaseCollidable()
	{
		x = 32.f;
		y = 32.f;
		width = 64.f;
		height = 64.f;
	}
	
	public BaseCollidable( float x, float y, float w, float h )
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	@Override
	public float X() {return x;}

	@Override
	public float Y() {return y;}
	public float W() {return width;}
	public float H() {return height;}

	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw() {
		//System.out.println("Draw method called for base collidable.");
		Configuration.getDisplayModel().drawRectangle(X(),Y(),W(),H());
	}

	@Override
	public void collisionEvent(ICollidable other) {
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
	public boolean collision(ICollidable other) {
		// check if a collision occurs between this object and the other.
		
		// first check that the other object is one which we can collide with.
		if (ICollidable.class.isInstance(other))
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

	@Override
	public float collision(float iX, float iY, float dX, float dY) {
		// TODO Auto-generated method stub
		return 0;
	}
}
