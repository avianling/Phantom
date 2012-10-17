package graphics;

import core.Configuration;
import core.IWorld;
import input.IEventModel;
import input.SwingEventModel;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingModel extends JFrame implements IDisplayModel, IContentManager {
	
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
		
		public void drawImage( BufferedImage img, int x, int y )
		{
			g2.drawImage(img, x, y, null);
		}
		
		
		public void drawLine( float X1, float Y1, float X2, float Y2 )
		{
			g2.drawLine((int)X1, (int)Y1, (int)X2, (int)Y2);
		}

		public void drawImage(BufferedImage img, int x, int y, float rotation) {
			// TODO : Improve the efficiency of this action.
			//AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(rotation), 0,0); //img.getWidth()/2, img.getHeight()/2 );
			AffineTransform tx = new AffineTransform();
			tx.translate(x + img.getWidth()/2, y + img.getHeight()/2);
			tx.rotate(Math.toRadians(rotation));
			tx.translate(-img.getWidth()/2, -img.getHeight()/2);
			g2.drawImage( img, tx, null );
		}
	}
	
	private IEventModel eventModel;
	
	private myPanel drawingArea;
	
	public void drawRectangle( float x, float y, float w, float h )
	{
		drawingArea.drawRectangle((int)x, (int)y, (int)w, (int)h);
	}
	
	public void drawImage( Object img, float x, float y )
	{
		if ( BufferedImage.class.isInstance(img) )
		{
			drawingArea.drawImage( (BufferedImage)img, (int)x, (int)y );
		} else {
			System.out.println("Warning: image supplied is not valid.");
		}
	}
	
	public void drawImage(Object img, float x, float y, float rotation )
	{
		drawingArea.drawImage( (BufferedImage)img, (int)x, (int)y, rotation );
	}
	
	public void drawLine( float X1, float Y1, float X2, float Y2 )
	{
		drawingArea.drawLine(X1, Y1, X2, Y2);
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
		addMouseListener((MouseListener) eventModel);
		addMouseMotionListener((MouseMotionListener) eventModel);
		
		Container content = getContentPane();
		
		drawingArea = new myPanel();
		drawingArea.theWorld = Configuration.getWorldModel();
		drawingArea.setBackground(Color.white);
		drawingArea.setSize(Configuration.getWidth(),Configuration.getHeight());
		content.add(drawingArea);
	
		show();
	}
	
	@Override
	public Object loadImage( String imageName )
	{
		// try and load the file.
		try {
			BufferedImage I = ImageIO.read(new File(imageName));
			
			return I;
		} catch (IOException e) {
			System.out.println("Error loading the specified file.");
		}
		return null;
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
