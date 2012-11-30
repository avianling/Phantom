package graphics;

import exceptions.AssetException;

public interface IContentManager {
	/**
	 * A function to load an image from the disk.
	 * @param imageName the name of the image to load.
	 */
	public Object loadImage( String imageName ) throws AssetException;
}
