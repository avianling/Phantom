/**
 * 
 */
package core;

import math.Vector;
import core.saving.InvalidDataException;
import core.saving.Savable;
import core.saving.SaveData;
import core.saving.Serializer;
import core.saving.SerializerNotLoadedException;
import exceptions.InvalidAttributeException;

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

	@Override
	public void load(SaveData data) throws InvalidDataException {
		try {
			setPosition( new Vector(data.getFloat("posX"), data.getFloat("posY")) );
			setRotation( data.getFloat("rotation") );
		} catch ( InvalidAttributeException e )
		{
			throw new InvalidDataException( "The data required for this object to load is missing", e);
		}
	}

}
