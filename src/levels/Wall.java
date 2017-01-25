package levels;

import java.io.Serializable;

public class Wall implements Element, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int wallCol;
	private int wallRow;
	private String wallId;
	private final char symbolId = '#';
	
	public Wall(int row, int col, int idNum){
		wallCol = col;
		wallRow = row;
		wallId = "wall"+idNum;
	}
	
	public int getCol() {
		return wallCol;
	}
	
	public int getRow() {
		return wallRow;
	}
	
	public String getId() {
		return wallId;
	}
	
	public char getSymbolId(){
		return symbolId;
	}


}
