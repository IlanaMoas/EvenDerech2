package model.data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class MyXMLLevelSaver implements LevelSaver{

	@Override
	public void saveLevel(String fileName, Level level) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(os);
//			XMLEncoder xmlEncoder = new XMLEncoder(bos);
			XMLEncoder encoder = new XMLEncoder(bos);
			encoder.writeObject(level);
			encoder.flush();
			encoder.close();
			Thread.sleep(10000);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		if(os != null){
			try{
				os.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		else{
			System.err.println("Error saving level to xml file.");
		}
		
	}

}
