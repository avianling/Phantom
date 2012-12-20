package core.saving;

public class InvalidDataException extends Exception {
	InvalidDataException() {
		super("Save data required for this objects load method is missing.");
	}
	public InvalidDataException(String message )
	{
		super(message);
	}
	
	public InvalidDataException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
