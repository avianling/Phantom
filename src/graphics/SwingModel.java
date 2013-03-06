package graphics;

import core.Configuration;
import core.IWorld;
import input.IEventModel;
import input.SwingEventModel;

import javax.imageio.ImageIO;
import javax.swing.*;

import math.Vector;

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
		
		public void drawRectangle( int x, int y, int w, int h, float xscale, float yscale, float rotation )
		{
			Graphics2D graphics = (Graphics2D) g2.create();
			AffineTransform tx = new AffineTransform();
			tx.translate(x + w/2, y + h/2);
			tx.rotate(Math.toRadians(rotation));
			tx.translate(-w/2, -h/2);
			graphics.transform(tx);
			graphics.drawRect(0, 0, w, h);
			graphics.dispose();

			//g2.rotate(Math.toRadians(-rotation));
		}
		
		public void drawRectangle( int x, int y, int w, int h, int xoffset, int yoffset, float xscale, float yscale, float rotation )
		{
			Graphics2D graphics = (Graphics2D) g2.create();
			AffineTransform tx = new AffineTransform();
			tx.translate(x + xoffset, y + yoffset );
			tx.rotate(Math.toRadians(rotation));
			tx.translate(-xoffset, -yoffset );
			graphics.transform(tx);
			graphics.drawRect(0, 0, w, h);
			graphics.dispose();
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
		
		public void drawImage( BufferedImage image, int x, int y, int xoffset, int yoffset, int xscale, int yscale, float rotation )
		{
			AffineTransform tx = new AffineTransform();
			
			/// Code for previous system which assumed that the center of the object was at the center of the image.
			/*tx.translate(x + image.getWidth()/2, y + image.getHeight()/2);
			tx.rotate(Math.toRadians(rotation));
			tx.scale(xscale, yscale);
			tx.translate(-(image.getWidth()*xscale)/2, -(image.getHeight()*yscale)/2);*/
			
			/// Code for the new system which assumes that the center is at a specified offset.
			tx.translate(x + xoffset, y + yoffset);
			tx.rotate(Math.toRadians(rotation));
			tx.scale(xscale, yscale);
			tx.translate(-xoffset, -yoffset);
			g2.drawImage(image, tx, null);
		}
		
		public void drawText( String text, int x, int y, int size )
		{
			//TODO: improve font loading method.
			// font loading could be optimized?
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
			Font font = new Font("Arial", Font.PLAIN, size);
			
			g2.setFont(font);
			g2.drawString(text, x, y);
		}
	}
	
	private IEventModel eventModel;
	
	private myPanel drawingArea;
	
	
	private View _view;
	
	public void setActiveView( View Newview )
	{
		_view = Newview;
	}
	
	
	public void drawRectangle( Vector position, Vector bounds )
	{
		Vector Pos = position.subtract(_view.getPosition());
		drawingArea.drawRectangle((int)Pos.X, (int)Pos.Y, (int)bounds.X, (int)bounds.Y);
	}
	// TODO: Need to update the others?
	
	
	
	
	public void drawRectangle( Vector position, Vector bounds, Vector scale, float rotation )
	{
		drawingArea.drawRectangle( (int)position.X, (int)position.Y, (int)bounds.X, (int)bounds.Y, (int)scale.X, (int)scale.Y, rotation );
	}
	
	
	
	
	public void drawRectangle( Vector position, Vector bounds, Vector offset, Vector scale, float rotation )
	{
		drawingArea.drawRectangle( (int)position.X, (int)position.Y, (int)bounds.X, (int)bounds.Y, (int)offset.X, (int)offset.Y, (int)scale.X, (int)scale.Y, rotation);
	}
	
	
	
	
	
	public void drawImage( Object img, Vector position )
	{
		if ( BufferedImage.class.isInstance(img) )
		{
			drawingArea.drawImage( (BufferedImage)img, (int)position.X, (int)position.Y );
		} else {
			System.out.println("Warning: image supplied is not valid.");
		}
	}
	
	
	
	
	
	public void drawImage(Object img, Vector position, float rotation )
	{
		drawingArea.drawImage( (BufferedImage)img, (int)position.X, (int)position.Y, rotation );
	}
	
	
	
	
	// Draws the given image at the provided position, scale and rotation.
	// Assumes that the centre of the object is at the centre of the image.
	public void drawImage( Object image, Vector position, Vector scale, float rotation)
	{
		int halfwidth = ((BufferedImage)image).getWidth()/2;
		int halfheight = ((BufferedImage)image).getHeight()/2;
		drawingArea.drawImage( (BufferedImage)image, (int)position.X, (int)position.Y, halfwidth, halfheight, (int)scale.X, (int)scale.Y, rotation);
	}
	
	
	public void drawImage( Object image, Vector position, Vector offset, Vector scale, float rotation )
	{
		drawingArea.drawImage((BufferedImage)image,(int)position.X, (int)position.Y, (int)offset.X, (int)offset.Y, (int)scale.X, (int)scale.Y, rotation);
	}
	
	
	public void drawLine( Vector start, Vector end )
	{
		drawingArea.drawLine((int)start.X, (int)start.Y, (int)end.X, (int)end.Y);
	}
	
	public SwingModel() throws Exception
	{
		setTitle(Configuration.getTitle());
		setSize(Configuration.getWidth(), Configuration.getHeight());
		setActiveView( new View( new Vector(0,0), new Vector( Configuration.getWidth(), Configuration.getHeight() ) ) );
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
	public void drawText( String text, Vector position, int size )
	{
		drawingArea.drawText(text, (int)position.X, (int)position.Y, size );
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





	@Override
	public void setView(View view) {
		
		
	}
}
