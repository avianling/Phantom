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
import core.saving.InvalidDataException;
import core.saving.SaveData;
import core.saving.Serializer;
import core.saving.SerializerNotLoadedException;
import exceptions.InvalidAttributeException;

public class StaticBlock extends BaseObjectSavable implements Collidable, Drawable {

	private CollisionBody body;
	
	public StaticBlock()
	{
		super();
		
	}
	
	public StaticBlock( float x, float y, int w, int h )
	{
		super();
		setPosition(new Vector(x,y));
		setBounds(new Vector(w,h));

		Vector[] points = {new Vector(x,y), new Vector(x+w,y), new Vector(x+w, y+h), new Vector(x, y+h) };
		
		body = new Polygon(points);
		body.setParent(this);
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
	
	@Override
	public void load(SaveData data) throws InvalidDataException {
		try {
			super.load(data);
			
			float w = data.getFloat("width");
			float h = data.getFloat("height");
			float x = position().X;
			float y = position().Y;
			setBounds( new Vector(w,h) );
			
			Vector[] points = {new Vector(x,y), new Vector(x+w,y), new Vector(x+w, y+h), new Vector(x, y+h) };
			
			body = new Polygon(points);
			body.setParent(this);
			
		} catch ( InvalidAttributeException e )
		{
			throw new InvalidDataException( "Unable to get information from save data", e );
		}
	}
	
}
