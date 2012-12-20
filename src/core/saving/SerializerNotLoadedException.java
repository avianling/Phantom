package core.saving;

public class SerializerNotLoadedException extends Exception {
	public SerializerNotLoadedException(String message)
	{
		super(message);
	}
	
	public SerializerNotLoadedException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
