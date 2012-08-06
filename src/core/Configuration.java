package core;
import graphics.*;
import java.lang.reflect.*;

public class Configuration {

	// which version of the world model are we using?
	//public static  _worldModel_ = BaseWorld.class;
	
	public static void main( String [] args )
	{
		System.out.println("Hello World!");
		
		width = 640;
		height = 480;
		
		worldModel = new BaseWorld();
		displayModel = new SwingModel();
		
		IDrawable thing = new BaseCollidable(128.f, 64.f, 64.f, 64.f);
		worldModel.add(thing);
		
		//worldModel.simulate();
		
		worldModel.add(new BaseCollidable());
		
		while (true)
		{
			worldModel.simulate();
		}
	}
	
	private static int fps = 1;
	private static IWorld worldModel;
	private static IDisplayModel displayModel;
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
	
	public static int getWidth()
	{
		return width;
	}
	
	public static int getHeight()
	{
		return height;
	}
	
	private static int width, height;
}
