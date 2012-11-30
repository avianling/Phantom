package isoGame;

import math.Vector;
import core.Configuration;
import core.Dynamic;
import core.EDrawingLayer;
import exceptions.AssetException;
import exceptions.ObjectCreationException;
import graphics.ISprite;
import graphics.StaticSprite;
import input.IMouseListener;
import physics.Collidable;

public class PlayerOne extends baseIsoDrawable implements IMouseListener, Dynamic {

	private Vector target;
	
	private ISprite cross;
	
	public PlayerOne(Vector position) throws ObjectCreationException
	{
		super();
		
		Configuration.getEventModel().addMouseListener(this);
		
		setPosition(position);
		target = new Vector(position.X, position.Y);
		
		try {
			cross = new StaticSprite("cross.png");
		} catch ( AssetException e )
		{
			throw new ObjectCreationException( "Unable to load asset", e );
		}
		
	}
	
	@Override
	public void draw()
	{
		Configuration.getDisplayModel().drawRectangle(position(), new Vector(16,16));
	}
	
	@Override
	public EDrawingLayer getLayer()
	{
		return EDrawingLayer.ground;
	}
	
	@Override
	public void step()
	{
		Vector direction = target.subtract(position()).norm();
		setSpeed(direction);
		setPosition( position().add(speed()) );
	}
	
	@Override
	public void leftPressed(int mouseX, int mouseY) {}

	@Override
	public void leftReleased(int mouseX, int mouseY) {}

	@Override
	public void rightPressed(int mouseX, int mouseY) {}

	@Override
	public void rightReleased(int mouseX, int mouseY) {
		target = new Vector(mouseX, mouseY);
	}

	@Override
	public void mouseMoved(int mouseX, int mouseY) {}

}
