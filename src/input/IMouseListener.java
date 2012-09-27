package input;

public interface IMouseListener {
	/**
	 * A method called whenever the user depressed the left mouse button.
	 * @param mouseX
	 * @param mouseY
	 */
	public void leftPressed( int mouseX, int mouseY );
	
	/**
	 * A method called whenever the user releases the left mouse button.
	 * @param mouseX
	 * @param mouseY
	 */
	public void leftReleased( int mouseX, int mouseY );
	
	public void rightPressed( int mouseX, int mouseY );
	
	public void rightReleased( int mouseX, int mouseY );
	
	public void mouseMoved( int mouseX, int mouseY );
	
	
}
