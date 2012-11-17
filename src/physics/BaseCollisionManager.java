package physics;

import java.util.ArrayList;
import java.util.List;

import core.Configuration;
import core.Drawable;
import core.EDrawingLayer;

public class BaseCollisionManager implements CollisionManager, Drawable {

	private List<CollisionBody> _collidableList;
	
	public BaseCollisionManager()
	{
		_collidableList = new ArrayList<CollisionBody>();
		
		Configuration.getWorldModel().add(this);
	}
	
	@Override
	public void simulate() {
		// For everything, check it against everything else:
		for ( int i=0; i < _collidableList.size(); i++ )
		{
			for ( int j=i; j < _collidableList.size(); j++ )
			{
				if ( i!=j )
				{
					if (_collidableList.get(i).collision(_collidableList.get(j)))
					{
						_collidableList.get(i).notifyParent(_collidableList.get(j).getParent());
						_collidableList.get(j).notifyParent(_collidableList.get(i).getParent());
					}
				}
			}
		}
	}

	@Override
	public void add(CollisionBody object) {
		_collidableList.add(object);
	}

	@Override
	public void draw() {
		for ( CollisionBody b : _collidableList )
		{
			if ( Polygon.class.isInstance(b) )
			{
				Polygon p = (Polygon)b;
				
				for ( int i=0; i < p._transformedPoints.length; i++ )
				{
					int endpoint = (i+1)%p._transformedPoints.length;
					
					Configuration.getDisplayModel().drawLine(p._transformedPoints[i], p._transformedPoints[endpoint]);
				}
			}
		}
	}

	@Override
	public EDrawingLayer getLayer() {
		return EDrawingLayer.foreground;
	}

}
