package core;

import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.SerializerNotLoadedException;

/**
 * This is a helper class to aid the user in storing object state.
 * @author alexander.borsboom
 *
 */
public interface Serializer {
	
	/**
	 * Open a file for saving with the given name.
	 * @param fileName
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void startSession( String fileName ) throws IOException;
	
	/**
	 * Close the session. Run this last!
	 * @throws SerializerNotLoadedException 
	 */
	public void endSession() throws SerializerNotLoadedException;
	
	/**
	 * Start saving an object.
	 * @param object
	 * @throws SerializerNotLoadedException
	 */
	public void startObject( String name ) throws SerializerNotLoadedException;
	
	/**
	 * Finish saving this object and move onto the next one.
	 * @throws SerializerNotLoadedException
	 */
	public void endObject() throws SerializerNotLoadedException;
	
	/**
	 * Save an integer to the current save file.
	 * @param name
	 * @param value
	 */
	public void save( String name, int value ) throws SerializerNotLoadedException;
	
	/**
	 * Save a floating point value to the current save file.
	 * @param name
	 * @param value
	 */
	public void save( String name, float value) throws SerializerNotLoadedException;
	

}
