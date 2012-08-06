package graphics;

import core.Configuration;
import input.SwingEventModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class SwingModel extends JPanel implements IDisplayModel {

	private JFrame frame;
	
	private Graphics2D g;
	
	public SwingModel()
	{
		// create a JFrame to contain this object.
		frame = new JFrame();
		frame.setSize(Configuration.getWidth(), Configuration.getHeight());
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
		
		this.repaint();
	}
	
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponents(g);
		g = (Graphics2D)graphics;
		
		// call the display method of the world.
		Configuration.getWorldModel().display();
	}
	
	

	@Override
	public void drawRectangle(float X, float Y, float W, float H) {
		// TODO Auto-generated method stub
		g.drawRect((int)X, (int)Y, (int)W, (int)H);
	}

}
