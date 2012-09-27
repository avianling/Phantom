package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SwingEventModel implements KeyListener, IEventModel, MouseListener, MouseMotionListener {
	
	private HashMap<Integer, EKey> keyMapping;
	
	private ArrayList<IKeyListener> keyListeners;
	private ArrayList<IMouseListener> mouseListeners;
	protected static SwingEventModel singleton;
	
	public SwingEventModel()
	{
		// initalize the array of listeners.
		keyListeners = new ArrayList<IKeyListener>();
		mouseListeners = new ArrayList<IMouseListener>();
		
		// setup the mapping from swingEventsCodes to EKey codes.
		keyMapping = new HashMap<Integer, EKey>();
		keyMapping.put(32, EKey.KeySpace);
		keyMapping.put(38, EKey.KeyUp);
		keyMapping.put(37, EKey.KeyLeft);
		keyMapping.put(39, EKey.KeyRight);
		keyMapping.put(40, EKey.KeyDown);
		keyMapping.put(65, EKey.Key_a);
		keyMapping.put(66, EKey.Key_b);
		keyMapping.put(67, EKey.Key_c);
		keyMapping.put(68, EKey.Key_d);
		keyMapping.put(69, EKey.Key_e);
		keyMapping.put(70, EKey.Key_f);
		keyMapping.put(71, EKey.Key_g);
		keyMapping.put(72, EKey.Key_h);
		keyMapping.put(73, EKey.Key_i);
		keyMapping.put(74, EKey.Key_j);
		keyMapping.put(75, EKey.Key_k);
		keyMapping.put(76, EKey.Key_l);
		keyMapping.put(77, EKey.Key_m);
		keyMapping.put(78, EKey.Key_n);
		keyMapping.put(79, EKey.Key_o);
		keyMapping.put(80, EKey.Key_p);
		keyMapping.put(81, EKey.Key_q);
		keyMapping.put(82, EKey.Key_r);
		keyMapping.put(83, EKey.Key_s);
		keyMapping.put(84, EKey.Key_t);
		keyMapping.put(85, EKey.Key_u);
		keyMapping.put(86, EKey.Key_v);
		keyMapping.put(87, EKey.Key_w);
		keyMapping.put(88, EKey.Key_x);
		keyMapping.put(89, EKey.Key_y);
		keyMapping.put(90, EKey.Key_z);
	}
	
	
	@Override
	public void addKeyListener(IKeyListener o) {
		// if the object is a key listener, add it to the key listeners list.
		keyListeners.add(o);
	}
	
	
	public void sendKeyPressedEvent( EKey key )
	{
		for ( IKeyListener i : keyListeners)
		{
			i.KeyPressed(key);
		}
	}

	
	public void sendKeyReleasedEvent( EKey key )
	{
		for ( IKeyListener i : keyListeners )
		{
			i.KeyReleased(key);
		}
	}

	@Override
	public void removeKeyListener(IKeyListener o) {
			keyListeners.remove(o);
	}

	
	@Override
	public void addMouseListener(IMouseListener listener)
	{
		mouseListeners.add(listener);
	}
	
	@Override
	public void removeMouseListener(IMouseListener listener)
	{
		mouseListeners.remove(listener);
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println("A Key has been pressed");
		int keyCode = arg0.getKeyCode();
		//System.out.println(keyCode + ":" + arg0.getKeyChar());
		if ( keyMapping.containsKey(keyCode) )
		{
			sendKeyPressedEvent(keyMapping.get(keyCode));
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if ( keyMapping.containsKey(keyCode) )
		{
			sendKeyReleasedEvent(keyMapping.get(keyCode));
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//System.out.println("A key has been typed");
		// TODO Not Currently Supported.
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Not implemented at this point.
		// is encapsulated by the mousePressed and mouseReleased events.
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Not Currently Supported.
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Not Currently Supported.
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// Extract the x,y coords of the event.
		int x, y;
		x = arg0.getX();
		y = arg0.getY();
		
		// Check if it is a left click or right click.
		if (arg0.getButton() == 1)
		{
			for ( IMouseListener l : mouseListeners )
			{
				l.leftPressed(x,y);
			}
		}
		
		if (arg0.getButton() == 2 )
		{
			for ( IMouseListener l : mouseListeners )
			{
				l.rightPressed(x, y);
			}
		}
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Extract the x,y coords of the event.
		int x, y;
		x = arg0.getX();
		y = arg0.getY();
		
		// Check if it is a left click or right click.
		if (arg0.getButton() == 1)
		{
			for ( IMouseListener l : mouseListeners )
			{
				l.leftReleased(x,y);
			}
		}
		
		if (arg0.getButton() == 2 )
		{
			for ( IMouseListener l : mouseListeners )
			{
				l.rightReleased(x, y);
			}
		}
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		for ( IMouseListener l : mouseListeners )
		{
			l.mouseMoved( arg0.getX(), arg0.getY() );
		}
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Notify everyone about the movement!
		for ( IMouseListener l : mouseListeners )
		{
			l.mouseMoved( arg0.getX(), arg0.getY() );
		}
	}
	
}
