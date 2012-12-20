package core.saving;

public class NoSuchSaveException extends Exception {
	NoSuchSaveException() {
		super("Unable to find the world to load");
	}
	public NoSuchSaveException(String message )
	{
		super(message);
	}
	
	public NoSuchSaveException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
