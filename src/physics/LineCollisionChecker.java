package physics;

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
		Configuration.getDisplayModel().drawLine( A.position(),B.position() );
		Configuration.getDisplayModel().drawLine(C.position(), D.position() );
		
		/*v1.X = B.position().X - A.position().X;
		v1.Y = B.position().Y - A.position().Y;
		v2.X = D.position().X - C.position().X;
		v2.Y = D.position().Y - C.position().Y;*/
		
		v1 = B.position().subtract(A.position());
		v2 = D.position().subtract(C.position());
		
		float dp = (float) v1.dot(v2);
		System.out.println(dp);
	}

	@Override
	public EDrawingLayer getDepth() {
		// TODO Auto-generated method stub
		return EDrawingLayer.ground;
	}
}
