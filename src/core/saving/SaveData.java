package core.saving;

import exceptions.InvalidAttributeException;

/**
 * SaveData objects and classes are used to provide information about saved state to objects so that they can assume the stored state. 
 * SaveData objects should be created by a loader and passed to objects which can then load the contained state.
 * @author alexander.borsboom
 *
 */
public interface SaveData {
	
	/**
	 * Get the given attribute as an integer. 
	 * @param attributeName
	 * @return The value of the attribute as an integer
	 * @throws InvalidAttributeException If there is no attribute saved under the given name. 
	 */
	public int getInt( String attributeName ) throws InvalidAttributeException;
	
	/**
	 * Find the given attribute and return it as a floating point value
	 * @param attributeName The name of the attribute. Passed as a string. If this attribute does not exist an InvalidAttributeException is thrown.
	 * @return The value of the attribute as a float.
	 * @throws InvalidAttributeException If there is no attribute saved under the given name.
	 */
	public float getFloat( String attributeName ) throws InvalidAttributeException;
	
	/**
	 * Stores the provided value under the given attribute name so that it can be retrieved later.
	 * @param attributeName
	 * @param value
	 */
	public void set( String attributeName, String value );
}
