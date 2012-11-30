package exceptions;

public class AssetException extends Exception {

	/**
	 * weird thing that java decided i needed to have.
	 */
	private static final long serialVersionUID = 6139959323866318843L;
	
	public AssetException( String Message ) {
		super(Message);
	}
	
	public AssetException( String message, Throwable cause )
	{
		super(message, cause );
	}

}
