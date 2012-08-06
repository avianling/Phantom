package input;

/**
 * This is an interface which is implemented by all objects which recieve keyboard input.
 * @author alex
 *
 */
public interface IKeyListener {
	public void KeyPressed( EKey key );
	public void KeyReleased( EKey key );
}
