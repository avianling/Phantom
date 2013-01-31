package textViewer;

import input.BaseEventModel;
import math.Vector;
import graphics.IDisplayModel;
import graphics.View;

public class TextDisplayModel extends BaseEventModel implements IDisplayModel {
	private View _view;
	
	@Override
	public void setActiveView( View view )
	{
		_view = view;
	}
	
	@Override
	public void initalize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawRectangle(Vector position, Vector bounds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawRectangle(Vector position, Vector bounds, Vector scale,
			float rotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawRectangle(Vector position, Vector bounds, Vector offset,
			Vector scale, float rotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawImage(Object image, Vector position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawImage(Object image, Vector position, float rotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawImage(Object image, Vector position, Vector scale,
			float rotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawImage(Object image, Vector position, Vector offset,
			Vector scale, float rotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawLine(Vector start, Vector end) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawText(String text, Vector position, int size) {
		System.out.println("Drawing Text: " + text + " at " + position);
	}

}
