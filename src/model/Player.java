package model;

import java.io.Serializable;

public class Player implements MovingElement, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int playerCol;
	private int playerRow;
	private String playerId;
	private final char symbolId = 'A';

	public Player(int row, int col, int idNum){
		playerCol = col;
		playerRow = row;
		playerId = "player"+idNum;
	}

	public int getCol() {
		return playerCol;
	}

	public int getRow() {
		return playerRow;
	}

	public void moveLeft() {
		playerCol--;	
	}

	public void moveRight() {
		playerCol++;	
	}

	public void moveUp() {
		playerRow--;	
	}

	public void moveDown() {
		playerRow++;	
	}

	public String getId() {
		return playerId;
	}
	
	public char getSymbolId(){
		return symbolId;
	}
}
