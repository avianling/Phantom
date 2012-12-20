package core.saving;


/**
 * Deserializers are used for converting a saved world state into real objects.
 * @author alexander.borsboom
 *
 */
public interface Deserializer {
	/**
	 * Should look for and load the provided saved state and recreate the objects contained within the state.
	 * Objects are loaded through reflection, and then passed 
	 * @param stateName The name of the state to load.
	 * @throws NoSuchSaveException
	 * @throws InvalidSaveException
	 * @see Savable
	 */
	public void load( String stateName ) throws NoSuchSaveException, InvalidSaveException;
}
