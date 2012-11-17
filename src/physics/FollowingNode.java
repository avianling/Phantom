package physics;

import math.Vector;
import input.IMouseListener;
import core.BaseObject;
import core.Configuration;
import core.Drawable;
import core.Dynamic;
import core.EDrawingLayer;

public class FollowingNode extends BaseObject implements IMouseListener, Drawable, Dynamic {
	
	private Vector _p,a,b,c,d,start;
	
	// Contains a point P, moves that point so that it is always at the mouse point.
	public FollowingNode()
	{
		_p = new Vector(2,2);
		Configuration.getWorldModel().add(this);
		a = new Vector(100,100);
		b = new Vector(220,200);
		c = new Vector(400,300);
		d = new Vector(0,300);
		start = new Vector(200,50);
	}

	@Override
	public void draw() {
		// draw the rectangle surrounding us.
		Configuration.getDisplayModel().drawRectangle(new Vector(100,100), new Vector(200,200));
		
		// Draw the rectangle to indicate the P node.
		Configuration.getDisplayModel().drawRectangle(_p.subtract(new Vector(3,3)), new Vector(6,6));
		for ( int x = 0; x < 600; x = x + 5 )
		{
			for ( int y = 0; y < 600; y = y+5 )
			{
				if (check(x,y))
				{
					// if it is inside the block, draw some shit
					Configuration.getDisplayModel().drawRectangle(new Vector(x,y),new Vector(5,5));
				}
			}
		}
	}

	@Override
	public EDrawingLayer getLayer() {
		return EDrawingLayer.ground;
	}
	
	private boolean check( float px, float py )
	{
		Vector p = new Vector(px, py);
		int v1 = (int)p.subtract(a).cross(b.subtract(a));
		int v2 = (int)p.subtract(b).cross(c.subtract(b));
		int v3 = (int)p.subtract(c).cross(d.subtract(c));
		int v4 = (int)p.subtract(d).cross(a.subtract(d));
		
		if ( v1 <= 0 && v2 <= 0 && v3 <= 0 && v4 <= 0 )
		{
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void step()
	{
		int val = (int)_p.subtract(b).cross(c.subtract(b));
	}

	@Override
	public void leftPressed(int mouseX, int mouseY) {}

	@Override
	public void leftReleased(int mouseX, int mouseY) {}

	@Override
	public void rightPressed(int mouseX, int mouseY) {}

	@Override
	public void rightReleased(int mouseX, int mouseY) {}

	@Override
	public void mouseMoved(int mouseX, int mouseY) {
		// move our object.
		_p.X = mouseX;
		_p.Y = mouseY;
	}
}
