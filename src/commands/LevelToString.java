package commands;

import levels.Element;
import levels.Level;

public class LevelToString {

	public static String getToString(Level level){

		String levelStr = "";
		//		Element grid[][] = new Element[level.getNumOfRows()][level.getNumOfCols()];
		Element grid[][] = level.getLevelGrid();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] != null){
					levelStr += grid[i][j].getSymbolId();
				}
				else{
					levelStr += " ";
				}
			}
			if(i != grid.length -1){
				levelStr += "\n";
			}
		}
		return levelStr;
	}
}
