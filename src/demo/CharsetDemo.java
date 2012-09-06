package demo;

import core.BaseObject;
import core.Collidable;
import core.Dynamic;
import core.Configuration;
import core.Drawable;
import graphics.AnimatedSprite;
import graphics.CharsetSprite;
import graphics.ISprite;
import graphics.StaticSprite;
import input.EKey;
import input.IKeyListener;

public class CharsetDemo extends BaseObject implements Drawable, Collidable, Dynamic {

	private ISprite picture;
	
	public CharsetDemo()
	{
		super();
		setPosition(128,128);
		setBounds(32,32);
		setSpeed(0,0);
		
		picture = new CharsetSprite("test.bmp",128,128);
		Configuration.getWorldModel().add(this);
		
	}

	public void draw() {
		//Configuration.getDisplayModel().drawImage(picture, X(), Y());
		picture.draw(X(), Y());
	}
	
	public void step()
	{
		super.step();
		picture.advance();
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
