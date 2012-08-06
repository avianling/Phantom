package graphics;

import core.Configuration;
import core.IWorld;
import input.IEventModel;
import input.SwingEventModel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyListener;

public class SwingModel extends JFrame implements IDisplayModel {
	
	class myPanel extends JPanel {
		IWorld theWorld;
		
		private Graphics2D g2;
		
		public void paintComponent(Graphics g)
		{
			super.paintComponents(g);
			
			g2 = (Graphics2D) g;
			
			theWorld.display();
		}
		
		
		public void drawRectangle(int x, int y, int w, int h )
		{
			g2.drawRect(x,y,w,h);
		}
	}
	
	private IEventModel eventModel;
	
	private myPanel drawingArea;
	
	public void drawRectangle( float x, float y, float w, float h )
	{
		drawingArea.drawRectangle((int)x, (int)y, (int)w, (int)h);
	}
	
	public SwingModel() throws Exception
	{
		setTitle(Configuration.getTitle());
		setSize(Configuration.getWidth(), Configuration.getHeight());
		setLocation(0,0);
		
		// store the key event listener
		
		eventModel = Configuration.getEventModel();
		// if it is not a KeyListener, then it is not a valid event model to use with swing.
		if ( !KeyListener.class.isInstance(eventModel) )
		{
			throw new Exception("Invalid event model for use with swing!");
		}
		
		addWindowListener(new SwingExitListener());
		addKeyListener((KeyListener) eventModel);
		
		Container content = getContentPane();
		
		drawingArea = new myPanel();
		drawingArea.theWorld = Configuration.getWorldModel();
		drawingArea.setBackground(Color.white);
		drawingArea.setSize(Configuration.getWidth(),Configuration.getHeight());
		content.add(drawingArea);
	
		show();
	}
	
	@Override
	public void initalize()
	{
		// does nothing in this implementation of the display model
	}
	
	@Override
	public void draw()
	{
		repaint();
	}
}
