package demo;

import core.BaseObject;
import core.Collidable;
import core.Dynamic;
import core.Configuration;
import core.Drawable;
import graphics.AnimatedSprite;
import graphics.ISprite;
import graphics.StaticSprite;
import input.EKey;
import input.IKeyListener;

public class ImageDemo extends BaseObject implements Drawable, Collidable, Dynamic {

	private ISprite picture;
	
	private float _rotation;
	
	public ImageDemo()
	{
		super();
		setPosition(128,128);
		setBounds(32,32);
		setSpeed(0,0);
		
		picture = new AnimatedSprite("test.png",3);
		Configuration.getWorldModel().add(this);
		_rotation = 0;
		
	}

	public void draw() {
		//Configuration.getDisplayModel().drawImage(picture, X(), Y());
		picture.draw(X(), Y(), _rotation);
	}
	
	public void step()
	{
		super.step();
		_rotation += 0.1;
		//picture.advance();
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("A collision occured!");
	}

	@Override
	public boolean collision(Collidable other) {
		return false;
	}

	@Override
	public boolean collision(float x, float y) {
		return false;
	}

	@Override
	public int getDepth() {
		return -1;
	}
	
}
