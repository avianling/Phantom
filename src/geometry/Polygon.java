package geometry;

import physics.Collidable;
import physics.CollisionBody;
import physics.PolygonAsset;
import core.Configuration;
import exceptions.AssetException;
import math.Vector;

public class Polygon implements CollisionBody, Shape {

	private PolygonAsset asset;
	
	protected Vector _transformedPoints[];
	private Vector _origonalPoints[];
	
	private int size;
	
	private Vector _position;
	private double _yrotation;
	private double _xscale, _yscale;
	
	// An offset - sometimes there is a discrepancy between the object this polygon represents and where the shape this polygon represents is located?
	// Applied after transformation(?)
	private Vector _offset;
	
	public void setOffset( Vector newOffset )
	{
		_offset = newOffset;
		recalculateTransform();
	}
	
	private Collidable _parent;
	private AARectangle _boundingBox;
	
	public Polygon( Vector[] pointArray )
	{
		size = pointArray.length;
		
		//asset = new PolygonAsset(pointArray);
		//_origonalPoints = asset.getPoints();
		
		_origonalPoints = pointArray;
		_transformedPoints = new Vector[size];
		
		_position = new Vector(0,0);
		_yrotation = 0.0;
		_xscale = 1.0;
		_yscale = 1.0;
		_offset = new Vector(0,0);
		
		_parent = null;
		
		_boundingBox = new AARectangle(); 
		
		recalculateTransform();
		
		Configuration.getWorldModel().getCollisionManager().add(this);
	}
	
	
	@Override
	public void setParent(Collidable newParent)
	{
		_parent = newParent;
	}
	
	@Override
	public void printSmallestPoint()
	{
		float smallestY = 999999999;
		for ( Vector v : _transformedPoints )
		{
			if ( v.Y < smallestY )
			{
				smallestY = v.Y;
			}
		}
		System.out.println(smallestY);
	}
	
	@Override
	public Collidable getParent()
	{	
		return _parent;
	}
	
	@Override
	public void notifyParent(Collidable other)
	{
		_parent.collisionEvent(other);
	}
	
	// A method to calculate the transformed state of the polygon.
	private void recalculateTransform()
	{
		// keep track of the smallest & largest points so that we can calculate the bounding box for the polygon.
		float minX, maxX, minY, maxY;
		
		int i=0;
		for ( Vector v : _origonalPoints )
		{
			float newX, newY;
			
			newX = (float)(_xscale * ( v.X - _offset.X ) * Math.cos(_yrotation) - _xscale * ( v.Y - _offset.Y ) * Math.sin(_yrotation) + _position.X + _offset.X);
			newY = (float)(_yscale * ( v.X - _offset.X ) * Math.sin(_yrotation) + _yscale * ( v.Y - _offset.Y ) * Math.cos(_yrotation) + _position.Y + _offset.Y);
			
			_transformedPoints[i] = new Vector(newX, newY);
			i++;
		}
		
		minX = _transformedPoints[0].X;
		maxX = _transformedPoints[0].X;
		minY = _transformedPoints[0].Y;
		maxY = _transformedPoints[0].Y;
		
		for ( Vector v : _transformedPoints )
		{
			if ( v.X < minX ) { minX = v.X; };
			if ( v.X > maxX ) { maxX = v.X; };
			if ( v.Y < minY ) { minY = v.Y; };
			if ( v.Y > maxY ) { maxY = v.Y; };
		}
		
		_boundingBox.setPosition( new Vector(minX, minY ));
		_boundingBox.setSize( new Vector(maxX - minX, maxY - minY ) );
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
	
	
	//TODO: Change to more effective method from duke nukem.
	private boolean pointWithin( Vector point )
	{
		//int v1 = (int)p.subtract(a).cross(b.subtract(a));
		
		for ( int i=0; i < size; i ++ )
		{
			// Make it cyclic.
			int endPoint = (i+1)%(size);
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
	
	private boolean doLinesIntersect( Vector startLine1, Vector endLine1, Vector startLine2, Vector endLine2 )
	{
		Vector directionLine1 = endLine1.subtract(startLine1);
		Vector directionLine2 = endLine2.subtract(startLine2);
		
		double parameter = ( directionLine1.X*startLine1.Y + directionLine1.Y*startLine2.X-startLine1.X*directionLine1.Y - directionLine1.X*startLine2.Y ) / ( directionLine1.X*directionLine2.Y - directionLine1.Y*directionLine2.X );
		double parameter2 = ( startLine2.X - startLine1.X + directionLine2.X * parameter ) / directionLine1.X;
		
		// calculate the point where the two lines collide.
		//Vector result = startLine2.add( directionLine2.multiply((float)parameter) );
		return ( parameter > 0 && parameter < 1.0 && parameter2 > 0 && parameter2 < 1.0 );
	}
	
	@Override
	public boolean collision(CollisionBody other) {
		
		// Phase one:
		// If any of their points are within our shape, then we are colliding.
		
		if ( Polygon.class.isInstance(other) )
		{
			Vector l1Start, l2Start, l1End, l2End;
			
			Polygon o = (Polygon)other;
			for ( int i=0; i < o.size; i++ )
			{
				int endpoint = (i+1)%o.size;
				
				l1Start = o._transformedPoints[i];
				l1End = o._transformedPoints[endpoint];
				
				for ( int j=0; j < size; j++ )
				{
					int ourEndpoint = (j+1)%size;
					
					l2Start = _transformedPoints[j];
					l2End = _transformedPoints[ourEndpoint];
					
					if ( doLinesIntersect(l1Start, l1End, l2Start, l2End) )
					{
						return true;
					}
				}
				
			}
		}
		
		return false;
	}

	
	/**
	 * A method to return the length of the shape when it is project along a given axis.
	 * Only takes unit vectors!
	 */
	@Override
	public float getAxisLength(Vector axis) {	
		Vector unitAxis = axis.norm();
		
		if ( _transformedPoints.length > 2 )
		{
			// assign initial values for smallest and longest length
			float smallest = _transformedPoints[0].dot(unitAxis);
			float largest = _transformedPoints[1].dot(unitAxis);
			
			for ( Vector v : _transformedPoints )
			{
				float l = v.dot(unitAxis);
				if ( l < smallest ) { smallest = l; };
				if ( l > largest ) { largest = l; };
			}
			
			return (largest - smallest);
		}
		else
		{
			return 0;
		}
	}

	@Override
	public Vector getPosition() {
		return _position;
	}

	@Override
	public AARectangle getBoundingBox() {
		return _boundingBox;
	}
	
}
