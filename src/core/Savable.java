package core;

import exceptions.SerializerNotLoadedException;

/**
 * Objects which extend this interface can be saved by using the Serializer helper class.
 * @author alexander.borsboom
 *
 */
public interface Savable {
	
	/**
	 * Save the objects state using the provided writer. 
	 * @param writer
	 */
	public void save( Serializer writer ) throws SerializerNotLoadedException;
}
