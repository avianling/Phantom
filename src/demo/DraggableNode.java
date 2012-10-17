package demo;

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
		setPosition(20, 20);
		setBounds(20,20);
		
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void leftPressed(int mouseX, int mouseY) {
		int left = (int) (X() - 0.5*W());
		int right = (int) (left + W());
		int top = (int) (Y() - 0.5*H());
		int bottom = (int) (top + H());
		
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
			setPosition(mouseX, mouseY);
		}
	}

	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle((float)(_X - 0.5*_width), (float)(_Y - 0.5*_height), _width, _height);
	}

	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.ground;
	}
	
}
