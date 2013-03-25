package core;

import input.Clickable;
import input.IMouseListener;
import math.Vector;
import geometry.AARectangle;
import geometry.Shape;

/**
 * A clickable shape is an object containing a reference to a shape and a method to be ran when the shape is clicked on.
 * @author Alexander
 *
 */
public class ClickableShape implements Shape, Clickable {
	
	private Shape _shape;
	private IMouseListener _listener;
	
	public ClickableShape( Shape shape, IMouseListener listener )
	{
		_shape = shape;
		setListener(listener);
	}

	@Override
	public Vector getPosition() {
		return _shape.getPosition();
	}

	@Override
	public void setPosition(Vector position) {
		_shape.setPosition(position);
	}

	@Override
	public AARectangle getBoundingBox() {
		return _shape.getBoundingBox();
	}

	@Override
	public void setListener(IMouseListener listener) {
		// TODO Auto-generated method stub
		_listener = listener;
	}

	@Override
	public IMouseListener getListener() {
		return _listener;
	}

	@Override
	public void click(Vector ClickPosition, Boolean left) {
		_listener.leftPressed((int)ClickPosition.X, (int)ClickPosition.Y);
	}



}
