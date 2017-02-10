package model.data;

import javafx.scene.image.Image;

public class Player implements MovingElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int col;
	private int row;
	private String id;
	private final char symbolId = 'A';
	private Image playerImg = new Image("/resources/Player.jpeg");

	public Player(){}
	
	public Player(int row, int col, int idNum){
		this.col = col;
		this.row = row;
		this.id = "player"+idNum;
	}
	
	public Player(Player player){
		this.col = player.getCol();
		this.row = player.getRow();
		this.id = player.getId();
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public void moveLeft() {
		col--;	
	}

	public void moveRight() {
		col++;	
	}

	public void moveUp() {
		row--;	
	}

	public void moveDown() {
		row++;	
	}

	public String getId() {
		return id;
	}
	
	public char getSymbolId(){
		return symbolId;
	}
	
	@Override
	public String toString(){
		return String.format("[Player: id='%s', col=%d, row=%d, symbol='%s']", id, col, row, symbolId);
	}

	@Override
	public Image getImage() {
		return playerImg;
	}
}
