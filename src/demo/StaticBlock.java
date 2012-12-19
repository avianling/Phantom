package demo;

import physics.Collidable;
import physics.CollisionBody;
import physics.Polygon;
import math.Vector;
import core.BaseObject;
import core.BaseObjectSavable;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;
import core.GameObject;
import core.saving.Serializer;
import exceptions.SerializerNotLoadedException;

public class StaticBlock extends BaseObjectSavable implements Collidable, Drawable {

	private CollisionBody body;
	
	public StaticBlock( float x, float y, int w, int h )
	{
		setPosition(new Vector(x,y));
		setBounds(new Vector(w,h));
		
		Vector[] points = {new Vector(x,y), new Vector(x+w,y), new Vector(x+w, y+h), new Vector(x, y+h) };
		
		body = new Polygon(points);
		body.setParent(this);
		
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle(position(), bounds());
	}

	@Override
	public EDrawingLayer getLayer() {
		return EDrawingLayer.background;
	}

	@Override
	public void collisionEvent(Collidable other) {
		//System.out.println("something stood on me");
		
		// do nothing
	}
	
	/**
	 * The save method.
	 * For this static block object, only the position, height and width and rotation are important. Nothing else.
	 */
	@Override
	public void save(Serializer writer) throws SerializerNotLoadedException {
		super.save(writer);
		writer.save("width", bounds().X );
		writer.save("height", bounds().Y );
	}
	
}
