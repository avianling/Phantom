/**
 * 
 */
package core;

import core.saving.Savable;
import core.saving.Serializer;
import exceptions.SerializerNotLoadedException;

/**
 * @author alexander.boorsboom
 *
 */
public class BaseObjectSavable extends BaseObject implements Savable {

	/* (non-Javadoc)
	 * @see core.Savable#save(core.Serializer)
	 */
	@Override
	public void save(Serializer writer) throws SerializerNotLoadedException {
		// Write all of our important variables to the file!
		
		// i.e. write our position, rotation, speed to a file for retrival later.
		// the other attributes are pretty constant.
		writer.save("posX", this.position().X);
		writer.save("posY", this.position().Y);
		writer.save("rotation", this.rotation());
	}

}
