package model.data;

import java.io.Serializable;

public class BoxEndPoint implements Element, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int boxDestCol;
	private int boxDestRow;
	private String boxDestId;
	private final char symbolId = 'o';
//	private boolean isOccupied;
	
	public BoxEndPoint(int row, int col, int idNum){
		boxDestCol = col;
		boxDestRow = row;
		boxDestId = "dest"+idNum;
//		isOccupied = false;
	}
	
	public int getCol() {
		return boxDestCol;
	}

	public int getRow() {
		return boxDestRow;
	}

	public String getId() {
		return boxDestId;
	}
	
	public char getSymbolId(){
		return symbolId;
	}
	
//	public boolean getIsOccupied(){
//		return isOccupied;
//	}
//	
//	public void setIsOccupied(boolean occupied){
//		isOccupied = occupied;
//	}

}
