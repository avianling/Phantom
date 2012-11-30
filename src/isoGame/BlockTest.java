package isoGame;

import core.EDrawingLayer;
import exceptions.AssetException;
import exceptions.ObjectCreationException;
import graphics.ISprite;
import graphics.StaticSprite;
import math.Vector;

public class BlockTest extends baseIsoDrawable {
	private ISprite spr;
	
	public BlockTest(Vector position) throws ObjectCreationException
	{
		super();
		
		setPosition(position);
		setRotation(0.0f);
		
		try
		{
			spr = new StaticSprite("block_64.png");
		} catch ( AssetException e )
		{
			throw new ObjectCreationException("Unable to load asset", e );
		}
		
	}
	
	@Override
	public EDrawingLayer getLayer()
	{
		return EDrawingLayer.ground;
	}
	
	@Override
	public void draw()
	{
		position();
		rotation();
		spr.draw(position(),rotation());
	}
}
