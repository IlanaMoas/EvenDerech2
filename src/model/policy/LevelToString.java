package model.policy;

import java.util.ArrayList;
import model.data.*;
//import levels.Element;
//import levels.Level;

public class LevelToString {

	public static String getToString(Level level){

		String levelStr = "";
		//		Element grid[][] = new Element[level.getNumOfRows()][level.getNumOfCols()];
		ArrayList<ArrayList<Element>> grid = level.getVirtualLevel();
		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				if(grid.get(i).get(j) != null){
					levelStr += grid.get(i).get(j).getSymbolId();
				}
				else{
					levelStr += " ";
				}
			}
			if(i != grid.size() -1){
				levelStr += "\n";
			}
		}
		return levelStr;
	}
}
