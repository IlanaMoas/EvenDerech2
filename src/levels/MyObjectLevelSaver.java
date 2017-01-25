package levels;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MyObjectLevelSaver implements LevelSaver {

	public void saveLevel(String fileName, Level level) {
		ObjectOutputStream os = null;
		try {
			 os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(level);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				System.err.println("Error saving level to object file.");
			}
		}
		
	}

}
