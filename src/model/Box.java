package model;

import java.io.Serializable;

public class Box implements MovingElement, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int boxCol;
	private int boxRow;
	private String boxId;
	private final char symbolId = '@';
	boolean isAtDest;
	
	public Box(int row, int col, int idNum){
		boxCol = col;
		boxRow = row;
		boxId = "box"+idNum;
		isAtDest = false;
	}
	
	public int getCol() {
		return boxCol;
	}
	public int getRow() {
		return boxRow;
	}
	public void moveLeft() {
		boxCol--;
	}
	public void moveRight() {
		boxCol++;
	}
	public void moveUp() {
		boxRow--;
	}
	public void moveDown() {
		boxRow++;
	}
	public String getId() {
		return boxId;
	}
	
	public void setIsAtDest(boolean isAtDest){
		this.isAtDest = isAtDest;
	}
	
	public boolean getIsAtDest(){
		return isAtDest;
	}
	
	public char getSymbolId(){
		return symbolId;
	}
}
