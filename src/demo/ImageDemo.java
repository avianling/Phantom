package demo;

import core.BaseObject;
import core.Collidable;
import core.Dynamic;
import core.Configuration;
import core.Drawable;
import input.EKey;
import input.IKeyListener;

public class ImageDemo extends BaseObject implements Drawable, Collidable {

	private Object picture;
	
	public ImageDemo()
	{
		super();
		setPosition(128,128);
		setBounds(32,32);
		setSpeed(0,0);
		
		picture = Configuration.getContentManager().loadImage("C:\\test.png");
		Configuration.getWorldModel().add(this);
		
		
		
	}

	public void draw() {
		Configuration.getDisplayModel().drawImage(picture, X(), Y());
	}
	

	@Override
	public void collisionEvent(Collidable other) {
		// TODO Auto-generated method stub
		//System.out.println("A collision occured!");
	}

	@Override
	public boolean collision(Collidable other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collision(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return -1;
	}
	
}
