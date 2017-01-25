package levels;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;



public class MyObjectLevelLoader implements LevelLoader{

	public Level loadLevel(InputStream is) {
		try {
			ObjectInputStream ois = new ObjectInputStream(is);
			return (Level) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error reading object from stream.");
			e.printStackTrace();
		}
		return null;
		
	}

	
}
