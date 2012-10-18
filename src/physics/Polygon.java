package physics;

import math.Vector;

public class Polygon implements CollisionBody {

	// Our origonal coordinates.
	// Should not be modified!
	private Vector _originalPoints[];
	
	protected Vector _transformedPoints[];
	
	private int size;
	
	private Vector _position;
	private double _yrotation;
	private double _xscale, _yscale;
	
	public Polygon( Vector[] pointArray )
	{
		size = pointArray.length;
		
		_originalPoints = new Vector[size];
		
		int i=0;
		for ( Vector v : pointArray )
		{
			_originalPoints[i] = new Vector( v.X, v.Y);
			i++;
		}
		
		System.out.println("We have " + _originalPoints.length + " points");
		_transformedPoints = new Vector[size];
		
		_position = new Vector(0,0);
		_yrotation = 0.0;
		_xscale = 1.0;
		_yscale = 1.0;
		
		recalculateTransform();
	}
	
	// A method to calculate the transformed state of the polygon.
	private void recalculateTransform()
	{
		int i=0;
		for ( Vector v : _originalPoints )
		{
			float newX, newY;
			
			newX = (float)(_xscale * v.X * Math.cos(_yrotation) - _xscale * v.Y * Math.sin(_yrotation) + _position.X);
			newY = (float)(_yscale * v.X * Math.sin(_yrotation) + _yscale * v.Y * Math.cos(_yrotation) + _position.Y);
			
			_transformedPoints[i] = new Vector(newX, newY);
			i++;
			System.out.println("Calculated a point for " + i);
		}
	}
	
	
	
	@Override public void setPosition( Vector position )
	{
		// Store the position and set our dirty bit.
		_position = position;
		recalculateTransform();
	}
	
	public void print()
	{
		for ( Vector v : _transformedPoints )
		{
			System.out.println(v.X + ":" + v.Y );
		}
	}
	
	
	
	@Override public void setRotation( double rotation )
	{
		_yrotation = Math.toRadians(rotation);
		recalculateTransform();
	}
	
	@Override public void setScale( double xScale, double yScale )
	{
		_xscale = xScale;
		_yscale = yScale;
		recalculateTransform();
	}
	
	
	
	private boolean pointWithin( Vector point )
	{
		//int v1 = (int)p.subtract(a).cross(b.subtract(a));
		
		for ( int i=0; i < size; i ++ )
		{
			// Make it cyclic.
			int endPoint = (i+1)%(size);
			System.out.println(i + "->" + endPoint );
			Vector start = _transformedPoints[i];
			Vector end = _transformedPoints[endPoint];
						
			try {
				Vector line1 = end.subtract(start);
				
				int result = (int)point.subtract(start).cross(end.subtract(start));
				if ( result > 0 )
				{
					return false;
				}
			}
			catch ( NullPointerException e )
			{
				System.out.println("Error: Invalid collision object? Not sure how this happened.");
				return false;
			}
		}
		
		// if we reach this point, then none of our points are outside of our polygon.
		// thus, the point is within the polygon.
		return true;
	}
	
	@Override
	public boolean collision(CollisionBody other) {
		
		// Phase one:
		// If any of their points are within our shape, then we are colliding.
		if ( Polygon.class.isInstance(other) )
		{
			Polygon o = (Polygon)other;
			for ( Vector v : o._transformedPoints )
			{
				if ( pointWithin(v) )
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
}
