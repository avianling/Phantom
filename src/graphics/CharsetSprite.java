package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;

import math.Vector;

import core.Configuration;

public class CharsetSprite implements ISprite {

	public float _frame;
	private int _animStart;
	private int _animEnd;
	private int _length;
	
	private int _frameHeight;
	private int _frameWidth;
	
	private int _xTile;
	private int _yTile;
	
	private float _speed;
	
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
		
		_speed = 20;
	}
	
	@Override
	public boolean advance() {
		_frame = _frame + ( _speed / Configuration.getFPS() );
		_frame = _frame % _length;
		return _frame==0;
	}

	@Override
	public void draw(Vector position) {
		column = ((int)_frame % _xTile);
		row = ((int)_frame / _yTile);
		Configuration.getDisplayModel().drawImage( _image.getSubimage(column*_frameWidth, row*_frameHeight, _frameWidth, _frameHeight) , position);
	}
	
	@Override
	public void draw(Vector position, float rotation)
	{
		column = ((int)_frame % _xTile);
		row = ((int)_frame / _yTile);
		Configuration.getDisplayModel().drawImage( _image.getSubimage(column*_frameWidth, row*_frameHeight, _frameWidth, _frameHeight) , position, rotation);
	}

}
