package graphics;

import core.Configuration;
import core.IWorld;
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
	
	private myPanel drawingArea;
	
	public void drawRectangle( float x, float y, float w, float h )
	{
		drawingArea.drawRectangle((int)x, (int)y, (int)w, (int)h);
	}
	
	public SwingModel()
	{
		setTitle(Configuration.getTitle());
		setSize(Configuration.getWidth(), Configuration.getHeight());
		setLocation(0,0);
		
		addWindowListener(new SwingExitListener());
		// TODO
		// add key listener stuff later.
		
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
	
	
	/*private JFrame frame;
	
	private Graphics2D g;
	
	public SwingModel()
	{
		// create a JFrame to contain this object.
		frame = new JFrame();
		frame.setSize(Configuration.getWidth(), Configuration.getHeight());
		frame.setBackground(Color.white);
		frame.add(this);
		frame.setVisible(true);
		
		// create an exit listener to detect when the window is closed
		// and terminate the program when this happens.
		frame.addWindowListener(new SwingExitListener());
		// forces the use of the SwingEventModel, since we are using swing after all.
		frame.addKeyListener( SwingEventModel.getEventModel() );
	}
	
	@Override
	public void initalize() {
		// does nothing in this implementation
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		// make the object redraw itself.
		System.out.println("Draw event called for SwingModel");
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		//super.paintComponents(g);
		System.out.println("paintComponent is called!");
		g = (Graphics2D)graphics;
		
		// call the display method of the world.
		Configuration.getWorldModel().display();
	}
	
	

	@Override
	public void drawRectangle(float X, float Y, float W, float H) {
		// TODO Auto-generated method stub
		g.drawRect((int)X, (int)Y, (int)W, (int)H);
	}*/
	
	

}
