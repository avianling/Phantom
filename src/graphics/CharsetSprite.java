package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;

import core.Configuration;

public class CharsetSprite implements ISprite {

	public int _frame;
	private int _animStart;
	private int _animEnd;
	private int _length;
	
	private int _frameHeight;
	private int _frameWidth;
	
	private int _xTile;
	private int _yTile;
	
	private int column, row;
	
	private BufferedImage _image;
	
	public CharsetSprite( String imageName, int frameWidth, int frameHeight)
	{
		_image = (BufferedImage) Configuration.getContentManager().loadImage(imageName);
		
		_frameHeight = frameHeight;
		_frameWidth = frameWidth;
		_frame = 0;
		int height = ( _image).getHeight();
		int width = ( _image).getWidth();
		_xTile = width / frameWidth;
		_yTile = height / frameHeight;
		_length = _xTile * _yTile;
	}
	
	@Override
	public boolean advance() {
		_frame++;
		_frame = _frame % _length;
		return _frame==0;
	}

	@Override
	public void draw(float x, float y) {
		column = (_frame % _xTile);
		row = (_frame / _yTile);
		Configuration.getDisplayModel().drawImage( _image.getSubimage(column*_frameWidth, row*_frameHeight, _frameWidth, _frameHeight) , x, y);
	}

}
