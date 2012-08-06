package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SwingEventModel implements KeyListener, IEventModel {
	
	private HashMap<Integer, EKey> keyMapping;
	
	private ArrayList<IKeyListener> keyListeners;
	protected static SwingEventModel singleton;
	
	public SwingEventModel()
	{
		// initalize the array of listeners.
		keyListeners = new ArrayList<IKeyListener>();
		
		// setup the mapping from swingEventsCodes to EKey codes.
		keyMapping = new HashMap<Integer, EKey>();
		keyMapping.put(32, EKey.KeySpace);
		keyMapping.put(38, EKey.KeyUp);
		keyMapping.put(37, EKey.KeyLeft);
		keyMapping.put(39, EKey.KeyDown);
		keyMapping.put(40, EKey.KeyRight);
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
	
	/*public static SwingEventModel getEventModel()
	{
		if ( singleton == null )
		{*/
			/* the object does not exist, so we create it and return it.
			 * Note: When you create a new type of event model,
			 * you have to change the line below from SwingEventModel to
			 * whichever new eventmodel you are creating.
			 * This feels a bit iffy.
			 */
			/*SwingEventModel e = new SwingEventModel();
			singleton = e;
			return e;
		} 
		else
		{
			return singleton;
		}
	}*/
	
	
	@Override
	public void addListener(Object o) {
		// if the object is a key listener, add it to the key listeners list.
		if ( IKeyListener.class.isInstance(o) )
		{
			keyListeners.add((IKeyListener)o);
		}
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
	public void removeListener(Object o) {
		if ( IKeyListener.class.isInstance(o) )
		{
			keyListeners.remove((IKeyListener)o);
		}
	}

	
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("A Key has been pressed");
		int keyCode = arg0.getKeyCode();
		System.out.println(keyCode + ":" + arg0.getKeyChar());
		if ( keyMapping.containsKey(keyCode) )
		{
			sendKeyPressedEvent(keyMapping.get(keyCode));
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("A Key has been released");
		int keyCode = arg0.getKeyCode();
		if ( keyMapping.containsKey(keyCode) )
		{
			sendKeyReleasedEvent(keyMapping.get(keyCode));
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("A key has been typed");
	}
	
}
