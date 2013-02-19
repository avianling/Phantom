package geometry;

import math.Vector;

public class AARectangle implements Shape {
	private Vector _position;
	private Vector _size;
	
	@Override
	public Vector getPosition() {
		return _position;
	}
	
	public Vector getSize() {
		return _size;
	}
	
	public AARectangle() {
		setPosition( new Vector(0,0) );
		setSize( new Vector(0,0) );
	}

	@Override
	public void setPosition(Vector position) {
		_position = position;
	}

	@Override
	public AARectangle getBoundingBox() {
		return this;
	}

	public void setSize(Vector size) {
		_size = size;
	}
}
