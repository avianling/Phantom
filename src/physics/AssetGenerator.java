package physics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class AssetGenerator {
	public static void saveAssetFile( String fileName, math.Vector[] vectors )
	{
		try {
			ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Alexander.Boorsboom\\Documents\\GitHub\\Phantom\\Media\\" + fileName ));
			s.writeObject(vectors);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
