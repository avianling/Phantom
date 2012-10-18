package demo;

import math.Vector;
import input.IMouseListener;
import core.BaseObject;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;

public class DraggableNode extends BaseObject implements Drawable, IMouseListener {

	private boolean _dragging;
	
	public DraggableNode()
	{
		_dragging = false;
		setPosition(new Vector(20, 20));
		setBounds(new Vector(20,20));
		
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void leftPressed(int mouseX, int mouseY) {
		int left = (int) (position().X - 0.5*bounds().X);
		int right = (int) (left + bounds().X);
		int top = (int) (position().Y - 0.5*bounds().Y);
		int bottom = (int) (top + bounds().Y);
		
		if ( mouseX > left && mouseX < right && mouseY > top && mouseY < bottom )
		{
			_dragging = true;
		}
	}

	@Override
	public void leftReleased(int mouseX, int mouseY) {
		_dragging = false;		
	}

	@Override
	public void rightPressed(int mouseX, int mouseY) {
		System.out.println(mouseX + ":" + mouseY );
	}

	@Override
	public void rightReleased(int mouseX, int mouseY) {}
	
	@Override
	public void mouseMoved(int mouseX, int mouseY) {
		if (_dragging)
		{
			setPosition(new Vector(mouseX, mouseY));
		}
	}

	@Override
	public void draw() {
		Vector leftCorner = position().subtract( bounds().multiply(0.5f) );
		
		Configuration.getDisplayModel().drawRectangle(leftCorner, bounds());
	}

	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.ground;
	}
	
}
