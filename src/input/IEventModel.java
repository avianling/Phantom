package input;

/**
 * this is the interface for objects which collect the users input and send that out to observers.
 * They must be able to register listeners, which are notified whenever relevant events occur.
 * @author alex
 *
 */
public interface IEventModel {
	public void addListener( Object o );
	public void removeListener( Object o );
}
