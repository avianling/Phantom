package input;

import java.util.ArrayList;

public class BaseEventModel implements IEventModel {

	private ArrayList<IKeyListener> keyListeners;
	private ArrayList<IMouseListener> mouseListeners;
	
	protected static IEventModel singleton;
	
	public static IEventModel getEventModel()
	{
		if ( singleton == null )
		{
			// the object does not exist, so we create it and return it.
			IEventModel e = new BaseEventModel();
			singleton = e;
			return e;
		} 
		else
		{
			return singleton;
		}
	}
	
	public BaseEventModel()
	{
		keyListeners = new ArrayList<IKeyListener>();
		mouseListeners = new ArrayList<IMouseListener>();
	}
	
	@Override
	public void addKeyListener(IKeyListener o) {
		// if the object is a key listener, add it to the key listeners list.
		keyListeners.add((IKeyListener)o);
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
		keyListeners.remove((IKeyListener)o);
	}
	
	
	@Override
	public void addMouseListener(IMouseListener listener )
	{
		mouseListeners.add(listener);
	}
	
	@Override
	public void removeMouseListener( IMouseListener listener )
	{
		mouseListeners.remove(listener);
	}
	
	
}
