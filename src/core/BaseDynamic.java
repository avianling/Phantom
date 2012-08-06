package core;

public class BaseDynamic implements IDynamic {

	private float _lastX, _lastY;
	private float _dX, _dY;
	private float _X, _Y;
	
	@Override
	public void step() {
		applyVelocity();
	}

	@Override
	public void applyVelocity() {
		// store the old position in case we have to reverse the movement.
		_lastX = X();
		_lastY = Y();
		
		// adjust our current position by our speed.
		_X += _dX;
		_Y += _dY;
	}

	@Override
	public void setSpeed(float dX, float dY) {
		_dX = dX;
		_dY = dY;
	}

	@Override
	public float X() {
		return _X;
	}

	@Override
	public float Y() {
		return _Y;
	}

	@Override
	public float dX() {
		return _dX;
	}

	@Override
	public float dY() {
		return _dY;
	}
	
}
