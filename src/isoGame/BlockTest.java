package isoGame;

import core.EDrawingLayer;
import graphics.ISprite;
import graphics.StaticSprite;
import math.Vector;

public class BlockTest extends baseIsoDrawable {
	private ISprite spr;
	
	public BlockTest(Vector position)
	{
		super();
		
		setPosition(position);
		setRotation(0.0f);
		
		spr = new StaticSprite("block_64.png");
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
