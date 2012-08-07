package core;

public interface GameObject {

	// Getters and setters for positions and speeds etc.
	public float X();
	public float Y();
	public float W();
	public float H();
	public float dX();
	public float dY();
	
	public void setPosition( float x, float y );
	
	public void setSpeed( float dX, float dY );
	
	public void setBounds( int w, int h );
}
