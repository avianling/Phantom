package graphics;

import java.awt.Image;

import math.Vector;

import core.Configuration;

public class AnimatedSprite implements ISprite {

	private int _animStart;
	private int _animEnd;
	private int _frame;
	private int _length;
	
	private Image _frames[];
	
	public AnimatedSprite( String name, int frames )
	{
		_animStart = 0;
		_animEnd = frames;
		_frame = 0;
		_length = frames;
		
		_frames = new Image[frames];
		
		for ( int i=0; i< frames; i++ )
		{
			_frames[i] = (Image) Configuration.getContentManager().loadImage(name+i);
		}
		
	}
	
	
	@Override
	public boolean advance() {
		_frame++;
		_frame = _frame % _length;
		return _frame==0;
	}

	@Override
	public void draw(Vector position) {
		Configuration.getDisplayModel().drawImage(_frames[_frame], position);
	}

	@Override
	public void draw(Vector position, float rotation) {
		Configuration.getDisplayModel().drawImage(_frames[_frame], position, rotation);
	}
	
	@Override
	public void draw(Vector position, Vector offset, Vector scale, float rotation )
	{
		Configuration.getDisplayModel().drawImage(_frames[_frame], position, offset, scale, rotation );
	}
}
