package exceptions;

public class InvalidAttributeException extends Exception {
	public InvalidAttributeException( String Message ) {
		super(Message);
	}
	
	public InvalidAttributeException( String message, Throwable cause )
	{
		super(message, cause );
	}
}
