package input;

import math.Vector;

/**
 * Clickable objects are those which can be clicked on.
 * These pass click events off to ClickListeners.
 * For memory reasons, clickable objects can only store a single listener.
 * @author Alexander
 *
 */
public interface Clickable {
	public void setListener( IMouseListener listener );
	
	public IMouseListener getListener();
	
	// process a click which has occured. 
	public void click( Vector ClickPosition, Boolean left );
}
