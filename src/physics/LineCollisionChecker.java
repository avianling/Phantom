package physics;

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
		
		A.setPosition(50,50);
		B.setPosition(150, 150);
		C.setPosition(150, 50);
		D.setPosition(50, 150);
	}

	@Override
	public void draw() {
		Configuration.getDisplayModel().drawLine( A.X(), A.Y(),B.X(), B.Y() );
		Configuration.getDisplayModel().drawLine(C.X(), C.Y(), D.X(), D.Y() );
		
		v1.X = B.X() - A.X();
		v1.Y = B.Y() - A.Y();
		v2.X = D.X() - C.X();
		v2.Y = D.Y() - C.Y();
		
		float dp = (float) v1.dot(v2);
		System.out.println(dp);
	}

	@Override
	public EDrawingLayer getDepth() {
		// TODO Auto-generated method stub
		return EDrawingLayer.ground;
	}
}
