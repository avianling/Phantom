package input;

/**
 * this is the interface for objects which collect the users input and send that out to observers.
 * They must be able to register listeners, which are notified whenever relevant events occur.
 * @author alex
 *
 */
public interface IEventModel {
	
	// changed these to describe key listeners
	// so that there is a distinction between key and mouse listeners.
	public void addKeyListener( IKeyListener o );
	public void removeKeyListener( IKeyListener o );
	
	public void addMouseListener( IMouseListener listener );
	public void removeMouseListener( IMouseListener listener );
}
