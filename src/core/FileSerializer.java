package core;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.SerializerNotLoadedException;

public class FileSerializer implements Serializer {
	
	private BufferedWriter output;
	
	@Override
	public void startSession(String fileName) throws IOException {
		// open the file and get a writer to it.
		FileWriter s = new FileWriter( fileName + ".world" );
		output = new BufferedWriter(s);
		output.write("{\n");
	}

	@Override
	public void endSession() throws SerializerNotLoadedException {
		// TODO Auto-generated method stub
		if ( output != null )
		{
			try {
				output.write("}");
				output.close();
			} catch (IOException e) {
				throw new SerializerNotLoadedException("Unable to write to file", e);
			}
		}
	}

	@Override
	public void save(String name, int value) throws SerializerNotLoadedException {
		if ( output!=null )
		{
			try {
				output.write("\t\t" + name + " :" + value + ",\n");
			} catch (IOException e) {
				throw new SerializerNotLoadedException( "Unable to write to file!", e );
			}
		} else {
			throw new SerializerNotLoadedException( "The serializer has not been initalized correctly" );
		}
	}

	@Override
	public void save(String name, float value) throws SerializerNotLoadedException {
		if ( output!=null )
		{
			try {
				output.write("\t\t" + name + " :" + value + "\n");
			} catch (IOException e) {
				throw new SerializerNotLoadedException( "Unable to write to file!", e );
			}
		} else {
			throw new SerializerNotLoadedException( "The serializer has not been initalized correctly" );
		}
	}

	@Override
	public void startObject(String name) throws SerializerNotLoadedException {
		if ( output!=null )
		{
			try {
				output.write("\t" + name + " : {\n");
			} catch (IOException e) {
				throw new SerializerNotLoadedException( "Unable to write to file!", e );
			}
		} else {
			throw new SerializerNotLoadedException( "The serializer has not been initalized correctly" );
		}
	}

	@Override
	public void endObject() throws SerializerNotLoadedException {
		if ( output!=null )
		{
			try {
				output.write("\t}\n");
			} catch (IOException e) {
				throw new SerializerNotLoadedException( "Unable to write to file!", e );
			}
		} else {
			throw new SerializerNotLoadedException( "The serializer has not been initalized correctly" );
		}
	}

}
