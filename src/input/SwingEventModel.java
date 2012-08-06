package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SwingEventModel implements KeyListener, IEventModel {
	
	private HashMap<Integer, EKey> keyMapping;
	
	private ArrayList<IKeyListener> keyListeners;
	protected static SwingEventModel singleton;
	
	private SwingEventModel()
	{
		// initalize the array of listeners.
		keyListeners = new ArrayList<IKeyListener>();
		
		// setup the mapping from swingEventsCodes to EKey codes.
		keyMapping = new HashMap<Integer, EKey>();
		keyMapping.put(38, EKey.KeyUp);
		keyMapping.put(37, EKey.KeyLeft);
		keyMapping.put(39, EKey.KeyDown);
		keyMapping.put(40, EKey.KeyRight);
	}
	
	public static SwingEventModel getEventModel()
	{
		if ( singleton == null )
		{
			/* the object does not exist, so we create it and return it.
			 * Note: When you create a new type of event model,
			 * you have to change the line below from SwingEventModel to
			 * whichever new eventmodel you are creating.
			 * This feels a bit iffy.
			 */
			SwingEventModel e = new SwingEventModel();
			singleton = e;
			return e;
		} 
		else
		{
			return singleton;
		}
	}
	
	
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
		//System.out.println(keyCode + ":" + arg0.getKeyChar());
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
		System.out.println("A key has been typed");
	}
	
}
