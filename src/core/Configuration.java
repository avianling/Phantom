package core;
import graphics.*;
import input.IEventModel;
import input.IMouseListener;
import input.SwingEventModel;
import isoGame.BlockTest;
import isoGame.PlayerOne;
/*import isoGame.BlockTest;
import isoGame.PlayerOne;
import isoGame.baseIsoWorld;
import isoGame.gridDrawer;*/
import demo.*;
import exceptions.AssetException;
import exceptions.ObjectCreationException;
import exceptions.SerializerNotLoadedException;

import java.io.IOException;
import java.lang.reflect.*;

import math.Vector;
import physics.*;
import textViewer.TextDisplayModel;

public class Configuration {

	// which version of the world model are we using?
	//public static  _worldModel_ = BaseWorld.class;
	
	public static void main( String [] args )
	{
		width = 640;
		height = 480;
		
		try {
			contentManager = new ContentManager();
			worldModel = new BaseWorld();
			assetManager = new BaseAssetManager();
			worldModel.setCollisionManager(new BaseCollisionManager());
			eventModel = new TextDisplayModel();
			displayModel = (IDisplayModel) eventModel;
			
			//eventModel = new SwingEventModel();
			//displayModel = new SwingModel();
			
			worldModel.initalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		


		//DEMO Stuff
		try {
			PlayerDemo player = new PlayerDemo( new Vector(50, 50) );
		
			/*for ( int i=55; i < 90; i += 5 )
			{
				new PlayerDemo( new Vector( i, 50 ) );
			}*/
			
			// Make a block for the player to stand on.
			StaticBlock floor = new StaticBlock(0,400,640,80);
			new StaticBlock(400, 300, 240, 20);
			new StaticBlock(200, 200, 240, 35);
			
			//LineCollisionChecker test = new LineCollisionChecker();
			//gridDrawer grid = new gridDrawer();
			
			BlockTest upper = new BlockTest( new Vector(128,128));
			BlockTest lower = new BlockTest( new Vector(160,160));
			
			BlockTest lowerright = new BlockTest( new Vector(260,160));
			BlockTest upperright = new BlockTest( new Vector(228,128));
			
			PlayerOne temp = new PlayerOne(new Vector(250, 250));
			
			worldModel.save("testSave");
		} catch ( ObjectCreationException e )
		{
			System.out.println(e.getMessage());
		}
		
		//AssetGenerator.saveAssetFile("PlayerDemo.p", new Vector[] { new Vector(0,0), new Vector(24,0), new Vector(24,32), new Vector(0,32) });
		
		while (true)
		{
			worldModel.simulate();
		}
	}
	
	private static int fps = 45;
	private static IWorld worldModel;
	private static IDisplayModel displayModel;
	private static IEventModel eventModel;
	private static IContentManager contentManager;
	private static AssetManager assetManager;
	private static CollisionManager collisionManager;
	private static String title = "Darwin v0.5";
	
	private static String mediaDir = "Media\\";
	
	public static String getTitle()
	{
		return title;
	}
	
	public static int getFPS()
	{
		return fps;
	}
	
	public static IWorld getWorldModel()
	{
		return worldModel;
	}
	
	public static IDisplayModel getDisplayModel()
	{
		return displayModel;
	}
	
	public static IEventModel getEventModel()
	{
		return eventModel;
	}
	
	public static int getWidth()
	{
		return width;
	}
	
	public static int getHeight()
	{
		return height;
	}
	
	public static IContentManager getContentManager()
	{
		return contentManager;
	}
	
	public static CollisionManager getCollisionManager()
	{
		return collisionManager;
	}
	
	public static String getMediaDir()
	{
		return mediaDir;
	}
	
	public static AssetManager getAssetManager()
	{
		return assetManager;
	}
	
	private static int width, height;
}
