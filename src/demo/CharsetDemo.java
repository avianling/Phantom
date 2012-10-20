package demo;

import physics.Collidable;
import math.Vector;
import core.BaseObject;
import core.Dynamic;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;
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
		setPosition(new Vector(128,128));
		setBounds(new Vector(32,32));
		setSpeed(new Vector(0,0));
		
		picture = new CharsetSprite("test.bmp",128,128);
		Configuration.getWorldModel().add(this);
		
	}

	public void draw() {
		//Configuration.getDisplayModel().drawImage(picture, X(), Y());
		picture.draw(position());
	}
	
	public void step()
	{
		super.step();
		picture.advance();
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("A collision occured!");
	}

	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.background;
	}
	
}
