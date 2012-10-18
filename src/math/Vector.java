package math;

public class Vector {
	public float X;
	public float Y;
	
	public Vector( float x, float y )
	{
		X = x;
		Y = y;
	}
	
	public Vector add( Vector other )
	{
		// return a new point which is the sum of both points.
		return new Vector(X+other.X, Y+other.Y);
	}
	
	public Vector subtract( Vector other )
	{
		return new Vector(X-other.X, Y-other.Y);
	}
	
	public Vector multiply( float factor )
	{
		return new Vector(X*factor, Y*factor);
	}
	
	public Vector multiply( Vector other )
	{
		return new Vector(X*other.X, Y*other.Y);
	}
	
	public float dot(Vector other)
	{
		return (X*other.X + Y*other.Y);
	}
	
	public float cross(Vector other)
	{
		return X*other.Y - other.X*Y;
	}
	
	public float len()
	{
		return (float)Math.sqrt(X*X + Y*Y);
	}
	
	public Vector norm()
	{
		double length = Math.sqrt(X*X + Y*Y);
		return new Vector( (float)(X/length), (float)(Y/length));
	}
}
