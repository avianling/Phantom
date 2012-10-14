package physics;

public class Vector {
	public double X;
	public double Y;
	
	public Vector( double x, double y )
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
	
	public double dot(Vector other)
	{
		return (X*other.X + Y*other.Y);
	}
	
	public double cross(Vector other)
	{
		return X*other.Y - other.X*Y;
	}
	
	public double len()
	{
		return Math.sqrt(X*X + Y*Y);
	}
	
	public Vector norm()
	{
		double length = Math.sqrt(X*X + Y*Y);
		return new Vector( X/length, Y/length);
	}
}
