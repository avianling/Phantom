package demo;

import core.BaseDynamic;
import core.Configuration;
import core.IDrawable;
import input.EKey;
import input.IKeyListener;

public class MovementDemo extends BaseDynamic implements IKeyListener, IDrawable {

	public MovementDemo()
	{
		_dX = 0; _dY = 0;
		_X = 128; _Y = 128;
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void KeyPressed(EKey key) {
		if ( key == EKey.KeyLeft)
		{
			setSpeed(-4,dY());
		}
		if ( key == EKey.KeyRight )
		{
			setSpeed(4,dY());
		}
		if ( key==EKey.KeyUp )
		{
			setSpeed(dX(),-4);
		}
		if ( key==EKey.KeyDown )
		{
			setSpeed(dX(),4);
		}
	}

	@Override
	public void KeyReleased(EKey key) {
		if ( key==EKey.KeyLeft || key==EKey.KeyRight )
		{
			setSpeed(0,dY());
		}
		if ( key==EKey.KeyUp || key==EKey.KeyDown )
		{
			setSpeed(dX(), 0 );
		}
	}

	@Override
	public float W() {
		return 32;
	}

	@Override
	public float H() {
		return 32;
	}

	@Override
	public void setPosition(float x, float y) {
		_X = x; _Y = y;
	}

	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle(X(), Y(), W(), H());
	}

}
