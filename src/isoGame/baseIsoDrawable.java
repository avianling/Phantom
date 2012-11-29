package isoGame;

import math.Vector;
import core.EDrawingLayer;

public class baseIsoDrawable extends isoBase implements isoDrawable {

	private EDrawingLayer _layer;
	private int _depth;
	
	public void setDepth(int depth)
	{
		_depth = depth;
	}
	
	public baseIsoDrawable()
	{
		super();
		
		_layer = EDrawingLayer.ground;
		_depth = 0;
	}
	
	@Override
	protected void positionChanged(Vector newPosition)
	{
		// Update our depth?
		setDepth((int)(1-(newPosition.Y)));
	}
	
	@Override
	public void draw() {}

	@Override
	public EDrawingLayer getLayer() {
		return _layer;
	}

	@Override
	public int getDepth() {
		return _depth;
	}

	@Override
	public int compareTo(isoDrawable other)
	{
		int otherDepth = ((isoDrawable)other).getDepth();
		int ourDepth = getDepth();
		if ( otherDepth > ourDepth ) { return 1; };
		if ( otherDepth < ourDepth ) { return -1; };
		return 0;
	}

}
