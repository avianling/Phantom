package core.saving;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import demo.PlayerDemo;

/**
 * The FileDeserializer is for loading states from saves created with the FileSerializer.
 * @see FileSerializer
 * @author alexander.boorsboom
 */
public class FileDeserializer implements Deserializer {

	@Override
	public void load(String stateName) throws NoSuchSaveException, InvalidSaveException {
		
		// try to open the file.
		try {
			
			BufferedReader r = new BufferedReader( new FileReader( stateName + ".world" ) );
			
			try {
				// Start reading through the file?
				// first line is a {
				r.readLine();
				
				String className = r.readLine();
				
				while ( className.compareTo("}") != 0 )
				{
					String line = r.readLine();
					
					className = className.substring(1, className.indexOf(":"));
					System.out.println("Class Name:" + className);
					
					// Make a new SaveData object to save the information we have gathered from the saved state.
					SaveData s = new BaseSaveData();
					
					int divider;
					
					while ( line.compareTo("\t}") != 0 )
					{
						divider = line.indexOf(":");
						// Start at position 2, since there are two starting tab characters. 
						s.set( line.substring(2, divider ), line.substring(divider+1, line.length()));
						line = r.readLine();
					}
					
					// try and create a class to deal with this new data.
					Class c = Class.forName(className);
					
					try {
						Object o = c.newInstance();
						Savable obj = (Savable)o;
						obj.load(s);
					} catch (InstantiationException | IllegalAccessException e) {
						// Error of some sort.
						e.printStackTrace();
					} catch ( InvalidDataException e )
					{
						throw new InvalidSaveException("The data for an object was invalid", e);
					}
					
					//System.out.println(s.toString());
					
					className = r.readLine();
				}
				
				
			} catch (IOException e) {
				throw new InvalidSaveException("Unspecified IO exception occured", e);
			} catch (ClassNotFoundException e) {
				throw new InvalidSaveException("Tried to load a non existant object.",e);
			}
			
		} catch ( FileNotFoundException e )
		{
			throw new NoSuchSaveException("Unable to find the specified save file", e);
		}
		
		
		
	}

}
