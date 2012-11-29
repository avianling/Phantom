package isoGame;

import math.Vector;
import graphics.IDisplayModel;
import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;

public class gridDrawer extends isoBase implements Drawable {

	public gridDrawer()
	{
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void draw() {
		isoWorld w = (isoWorld) Configuration.getWorldModel();
		IDisplayModel d = Configuration.getDisplayModel();
		
		for ( float i=-0.5f; i < 20; i++ )
		{
			for ( float j=-0.5f; j < 20; j++ )
			{
				Vector p1 = w.isoToReal(new Vector(i,j));
				Vector p2 = w.isoToReal(new Vector(i+1,j));
				Vector p3 = w.isoToReal(new Vector(i+1,j+1));
				Vector p4 = w.isoToReal(new Vector(i,j+1));
				
				d.drawLine(p1, p2);
				d.drawLine(p2, p3);
				d.drawLine(p3, p4);
				d.drawLine(p4, p1);
			}
		}
	}

	@Override
	public EDrawingLayer getLayer() {
		return EDrawingLayer.background;
	}

}
