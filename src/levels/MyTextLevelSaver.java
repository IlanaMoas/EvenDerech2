package levels;

import java.io.FileOutputStream;
import java.io.IOException;

import commands.LevelToString;

public class MyTextLevelSaver implements LevelSaver {

	public void saveLevel(String fileName, Level level) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(fileName);
			os.write(LevelToString.getToString(level).getBytes());
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
				System.err.println("Error saving level to text file.");
			}
		}

	}
}
