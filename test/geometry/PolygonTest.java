package geometry;

import static org.junit.Assert.*;

import math.Vector;

import org.junit.Before;
import org.junit.Test;

import exceptions.AssetException;

public class PolygonTest {

	@Before
	public void setUp() throws Exception {
		// setup a shapefactory to use.
		physics.AssetManager m = new physics.BaseAssetManager("Media\\");
	}
	
	/**
	 * Test the creation of a polygon.
	 */
	@Test
	public void createFromAsset() {
		try {
			physics.AssetManager m = new physics.BaseAssetManager("Media\\");
			Polygon p = ShapeFactory.get().getPolygonFromAsset("PlayerDemo");
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void createFromVectors() {
		try {			
			Vector topLeft = new Vector(0,0);
			Vector topRight = new Vector( 15, 0 );
			Vector bottomLeft = new Vector( 0, 15 );
			Vector bottomRight = new Vector( 15, 15 );
			
			Vector[] shape = { topLeft, topRight, bottomLeft, bottomRight };
			
			Polygon m = new Polygon(shape);
			
		} catch ( Exception e )
		{
			fail();
		}		
	}
	
	@Test
	public void ExactOverlapCollisionTest()
	{		
		Vector topLeft = new Vector(0,0);
		Vector topRight = new Vector( 15, 0 );
		Vector bottomLeft = new Vector( 0, 15 );
		Vector bottomRight = new Vector( 15, 15 );
		
		Vector[] shape = { topLeft, topRight, bottomLeft, bottomRight };
		
		Polygon m = new Polygon(shape);
		Polygon m2 = new Polygon(shape);
		
		if ( m.collision(m2) != true )
		{
			fail("woo");
		}
	}
	
	@Test
	public void CornerOverlapCollisionTest()
	{		
		Vector topLeft = new Vector(0,0);
		Vector topRight = new Vector( 15, 0 );
		Vector bottomLeft = new Vector( 0, 15 );
		Vector bottomRight = new Vector( 15, 15 );
		
		Vector[] shape = { topLeft, topRight, bottomLeft, bottomRight };
		
		Polygon m = new Polygon(shape);
		Polygon m2 = new Polygon(shape);
		
		// now the two shapes are overlapping just on one corner. This means that they should not be colliding?
		m2.setPosition(new Vector(15,15) );
		if ( m.collision(m2) == true )
		{
			fail("woo");
		}	
	}
	
	

}
