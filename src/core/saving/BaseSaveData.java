package core.saving;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import exceptions.InvalidAttributeException;

public class BaseSaveData implements SaveData {

	private Map<String,String> _data;
	
	public BaseSaveData()
	{
		_data = new TreeMap<String, String>();
	}
	
	@Override
	public int getInt(String attributeName) throws InvalidAttributeException {
		if ( _data.containsKey(attributeName) )
		{
			return Integer.parseInt(_data.get(attributeName));
		} else {
			throw new InvalidAttributeException("The given attribute name is undefined.");
		}
	}

	@Override
	public float getFloat(String attributeName)	throws InvalidAttributeException {
		if ( _data.containsKey(attributeName) )
		{
			return Float.parseFloat(_data.get(attributeName));
		} else {
			throw new InvalidAttributeException("The given attribute name is undefined.");
		}
	}

	@Override
	public void set(String attributeName, String value) {
		_data.put(attributeName, value);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		Set<String> s = _data.keySet();
		for ( String item : s )
		{
			builder.append(item);
			builder.append(" : ");
			builder.append( _data.get(item) );
			builder.append("\n");
		}
		
		return builder.toString();
	}

}
