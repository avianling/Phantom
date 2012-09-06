package graphics;

import java.awt.Image;

import core.Configuration;

public class StaticSprite implements ISprite {

	private Image _image;
	
	public StaticSprite(String imageName)
	{
		// Load the image from somewhere and store it.
		// should be replaced with a method which gets it from a content manager.
		_image = (Image) Configuration.getContentManager().loadImage(imageName);
	}
	
	/**
	 * The advance method in static sprite does nothing as the sprite is not animated.
	 */
	@Override
	public boolean advance() {
		return false;
	}

	@Override
	public void draw(float x, float y) {
		Configuration.getDisplayModel().drawImage(_image, x, y);
	}

}