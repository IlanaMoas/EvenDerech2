package model.data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class MyXMLLevelLoader implements LevelLoader{

	@Override
	public Level loadLevel(InputStream is) {

		XMLDecoder decoder = null;
		Level level = null;
		try{
			BufferedInputStream bis = new BufferedInputStream(is);
			decoder = new XMLDecoder(bis);
//			Element[][] board = createGrid(is);
//			level.setVirtualLevel(((Level)decoder.readObject()).getVirtualLevel());
			level = (Level) decoder.readObject();
//			decoder.flush();
			decoder.close();
			
			Thread.sleep(10000);
			
			//	      System.out.println("List 'arrayList2' = '" + arrayList2 + "'");
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
//		try {
//			
//			Level level = new Level();
//			//			level.setLevelName();
//			level.setVirtualLevel(board);
//
//			return level;
//		}catch (IOException e) {
//			System.err.println("Error reading stream when creating grid level.");
//			e.printStackTrace();
//		}
		if(level != null){
			return level;
		}
		else{
			System.err.println("Error. XML file must represent a level object.");
			return null;
		}
	}

}
