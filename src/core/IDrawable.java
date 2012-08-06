package core;

public interface IDrawable {

	public float X();
	public float Y();
	public float W();
	public float H();
	
	public void setPosition(float x, float y);
	
	public void draw();
}
