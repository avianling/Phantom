package exceptions;

/**
 * An AssetException will be thrown whenever the user requests that a physics asset is created and the action can not be completed.
 * This can be caused by two underlying problems: Either the asset does not exist or the asset is corrupt.
 * @author alexander.borsboom
 */
public class AssetException extends Exception {

	/**
	 * weird thing that java decided i needed to have.
	 */
	private static final long serialVersionUID = 6139959323866318843L;
	
	public AssetException() {
		super("Unable to create the requested physics Asset.");
	}
	
	public AssetException( String Message ) {
		super(Message);
	}
	
	public AssetException( String message, Throwable cause )
	{
		super(message, cause );
	}

}
