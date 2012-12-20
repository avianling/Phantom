package core.saving;


/**
 * Objects which extend this interface can be saved by using the Serializer helper class.
 * @author alexander.borsboom
 *
 */
public interface Savable {
	
	/**
	 * Save the objects state using the provided writer. 
	 * @param writer A Serializer which will be used to save the object.
	 */
	public void save( Serializer writer ) throws SerializerNotLoadedException;
	
	/**
	 * Load the object from a given block of save data.
	 * @param data
	 * @throws InvalidDataException 
	 */
	public void load( SaveData data ) throws InvalidDataException;
}
