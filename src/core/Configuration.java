package core;
import graphics.*;
import input.IEventModel;
import input.IMouseListener;
import input.SwingEventModel;
import demo.*;
import java.lang.reflect.*;
import physics.*;

public class Configuration {

	// which version of the world model are we using?
	//public static  _worldModel_ = BaseWorld.class;
	
	public static void main( String [] args )
	{
		width = 640;
		height = 480;
		
		try {
			contentManager = new ContentManager("D:\\Phantom\\Media\\");
			worldModel = new BaseWorld();
			eventModel = new SwingEventModel();
			displayModel = new SwingModel();
			
			worldModel.initalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Drawable thing = new BaseCollidable(128.f, 64.f, 128.f, 64.f);
		//Drawable thing2 = new BaseCollidable(16,16,32,32);
		//ImageDemo id = new ImageDemo();
		//id.setPosition(100, 100);
		//MovementDemo copy1 = new MovementDemo();
		//MovementDemo copy2 = new MovementDemo();
		//MovementDemo copy3 = new MovementDemo();
		//MovementDemo copy4 = new MovementDemo();
		
		//CharsetDemo cd = new CharsetDemo();
		//cd.setPosition(100,300);
		
		//FollowingNode n = new FollowingNode();


		//DEMO Stuff
		
		PlayerDemo player = new PlayerDemo();
		
		// Make a block for the player to stand on.
		StaticBlock floor = new StaticBlock(0,400,640,80);
		new StaticBlock(400, 300, 240, 20);
		new StaticBlock(200, 200, 240, 35);
		
		LineCollisionChecker test = new LineCollisionChecker();
		
		
		
		while (true)
		{
			worldModel.simulate();
		}
	}
	
	private static int fps = 60;
	private static IWorld worldModel;
	private static IDisplayModel displayModel;
	private static IEventModel eventModel;
	private static IContentManager contentManager;
	private static String title = "Darwin v0.2";
	
	
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
	
	private static int width, height;
}
