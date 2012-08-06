package input;

import java.util.ArrayList;

public class BaseEventModel implements IEventModel {

	private ArrayList<IKeyListener> keyListeners;
	
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
	
	
	
	
	
}
