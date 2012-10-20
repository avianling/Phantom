package physics;

import graphics.IDisplayModel;
import math.Vector;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;
import demo.DraggableNode;

public class LineCollisionChecker implements Drawable {
	
	private DraggableNode A,B,C,D;
	
	private Vector v1, v2;
	
	public LineCollisionChecker()
	{
		Configuration.getWorldModel().add(this);
		
		A = new DraggableNode();
		B = new DraggableNode();
		C = new DraggableNode();
		D = new DraggableNode();
		
		v1 = new Vector(0,0);
		v2 = new Vector(0,0);
		
		A.setPosition(new Vector(50,50));
		B.setPosition(new Vector(150, 150));
		C.setPosition(new Vector(150, 50));
		D.setPosition(new Vector(50, 150));
	}

	@Override
	public void draw() {
		IDisplayModel graphics = Configuration.getDisplayModel();
		graphics.drawLine( A.position(),B.position() );
		graphics.drawLine(C.position(), D.position() );
		
		v1 = B.position().subtract(A.position());
		v2 = D.position().subtract(C.position());
		
		// Calculate the collision point?
		// TODO: Check if the lines are parallel?
		
		Vector position = findLineCollisionPoint(A.position(),B.position(),C.position(),D.position());
		position = position.subtract(new Vector(3,3) );
		
		graphics.drawRectangle(position, new Vector(6,6) );
	}
	
	public Vector findLineCollisionPoint( Vector startLine1, Vector endLine1, Vector startLine2, Vector endLine2 )
	{
		Vector directionLine1 = endLine1.subtract(startLine1);
		Vector directionLine2 = endLine2.subtract(startLine2);
		
		double parameter = ( directionLine1.X*startLine1.Y + directionLine1.Y*startLine2.X-startLine1.X*directionLine1.Y - directionLine1.X*startLine2.Y ) / ( directionLine1.X*directionLine2.Y - directionLine1.Y*directionLine2.X );
		//System.out.println(parameter);
		// calculate the point where the two lines collide.
		Vector result = startLine2.add( directionLine2.multiply((float)parameter) );
		return result;
	}

	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.ground;
	}
}
