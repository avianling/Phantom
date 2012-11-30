package exceptions;

public class ObjectCreationException extends Exception {
	public ObjectCreationException(String message )
	{
		super(message);
	}
	
	public ObjectCreationException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
