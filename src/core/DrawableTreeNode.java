package core;

public class DrawableTreeNode {
	private DrawableTreeNode _left;
	private DrawableTreeNode _right;
	
	private boolean _l;
	private boolean _r;
	
	private Drawable value;
	
	public boolean isLeaf()
	{
		return ( _l == false && _r == false );
	}
	
	private int getDepth()
	{
		if ( value == null )
		{
			return 0;
		} else {
			return value.getDepth();
		}
	}
	
	public DrawableTreeNode()
	{
		_l = false;
		_r = false;
		value = null;
	}
	
	public DrawableTreeNode( Drawable obj )
	{
		_l = false;
		_r = false;
		value = obj;
	}
	
	public void add( Drawable obj )
	{
		// if the objects depth is smaller than our depth, it goes on the left.
		// if the objects depth is greater than our depth, it goes on the right.
		int ourDepth = getDepth();
		
		if ( obj.getDepth() <= ourDepth )
		{
			if ( _l ) {
				_left.add(obj);
			} else {
				_l = true;
				_left = new DrawableTreeNode(obj);
			}
		}
		
		if ( obj.getDepth() > ourDepth )
		{
			if ( _r )
			{
				_right.add(obj);
			} else {
				_r = true;
				_right = new DrawableTreeNode(obj);
			}
		}
		
	}
	
	public void draw()
	{
		if ( _l )
		{
			_left.draw();
		}
		
		if ( value != null )
		{
			value.draw();
		}
		
		if ( _r )
		{
			_right.draw();
		}
	}
	
	
}
