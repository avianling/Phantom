package demo;

import input.EKey;
import input.IKeyListener;
import core.BaseObject;
import core.Collidable;
import core.Configuration;
import core.Drawable;
import core.Dynamic;
import core.EDrawingLayer;

import java.lang.Math;

public class rosRotation extends BaseObject implements Dynamic, Drawable, IKeyListener {
	
	private double angle;
	private double distance;
	private double angularSpeed;
	
	public rosRotation()
	{
		angle = 0.0;
		angularSpeed = 0.0;
		distance = 160;
		setBounds(32,32);
		setSpeed(0,0);
		Configuration.getWorldModel().add(this);
	}
	
	private double toRadians( double degrees )
	{
		return (degrees/180.0)*Math.PI;
	}
	
	@Override
	public EDrawingLayer getDepth() {
		return EDrawingLayer.background;
	}
	
	@Override
	public void step()
	{
		angle += angularSpeed;
		
		if ( angle > 180 )
		{
			angle = angle - 360;
		}
		if ( angle < -180 )
		{
			angle = angle + 360;
		}
		
		// work out where we should be from trig.
		double newY = 200 + distance * Math.sin(toRadians(angle));
		double newX = 200 + distance * Math.cos(toRadians(angle));
		
		setPosition((float)newX, (float)newY);
		
		System.out.println("The angle of the box is: " + angle+90);
	}
	
	@Override
	public void KeyPressed(EKey key) {
		if ( key == EKey.KeyLeft)
		{
			angularSpeed = 1.0;
		}
		if ( key == EKey.KeyRight )
		{
			angularSpeed = -1.0;
		}
	}

	@Override
	public void KeyReleased(EKey key) {
		if ( key == EKey.KeyLeft || key == EKey.KeyRight )
		{
			angularSpeed = 0;
		}
	}

	@Override
	public void draw() {
		Configuration.getDisplayModel().drawRectangle(X()-16, Y()-16, 32, 32 );
	}

}
