package physics;

import math.Vector;

public interface CollisionBody {
	public boolean collision(CollisionBody other);
	public void setPosition(Vector position);
	public void setRotation(double yRotation);
	public void setScale(double xScale, double yScale);
	public void setParent(Collidable parent);
	public Collidable getParent();
	public void notifyParent(Collidable other);
}
