package demo;

import physics.Collidable;
import math.Vector;
import core.BaseObject;
import core.Dynamic;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;
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
		setPosition(new Vector(128,128));
		setBounds(new Vector(32,32));
		setSpeed(new Vector(0,0));
		
		picture = new AnimatedSprite("test.png",3);
		Configuration.getWorldModel().add(this);
		_rotation = 0;
		
	}

	public void draw() {
		//Configuration.getDisplayModel().drawImage(picture, X(), Y());
		picture.draw(position(), _rotation);
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
	public EDrawingLayer getDepth() {
		return EDrawingLayer.foreground;
	}
	
}
