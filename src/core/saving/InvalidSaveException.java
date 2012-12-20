package core.saving;

/**
 * This exception is be thrown in two scenarios:
 * <ul>
 * <li>When the save data provided to the deserializer is incorrect and cannot be processed </li>
 * <li>When the save data for a given object cannot be used to make that object, i.e. the save data is corrupt, or the wrong version. </li>
 * </ul>
 * @author alexander.borsboom
 * @see Deserializer
 */
public class InvalidSaveException extends Exception {
	InvalidSaveException() {
		super("The save data is incorrect or badly formatted.");
	}
	public InvalidSaveException(String message )
	{
		super(message);
	}
	
	public InvalidSaveException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
