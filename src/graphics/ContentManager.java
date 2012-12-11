package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import core.Configuration;

public class ContentManager implements IContentManager {

	private Map<String, Image> content;
	
	private String _rootDir;
	
	public ContentManager( )
	{
		// create the content map.
		content = new HashMap<String, Image>();
		
		_rootDir = Configuration.getMediaDir();
	}
	
	@Override
	public Object loadImage(String imageName) {
		// check if the object has already been loaded before.
		if ( content.containsKey(imageName) )
		{
			// we already have this image, so return a reference to the already loaded image.
			return content.get(imageName);
		} else {
			// load the image
			try {
				BufferedImage I = ImageIO.read(new File(_rootDir + imageName));
				content.put(imageName, I);
				return I;
			} catch (IOException e) {
				System.out.println("Error, unable to load the supplied image");
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		return null;
	}

}
