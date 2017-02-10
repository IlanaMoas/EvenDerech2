package model.data;

import javafx.scene.image.Image;

public class Box implements MovingElement{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int col;
	private int row;
	private String id;
	private final char symbolId = '@';
	private boolean isAtDest;
	private Image boxImg = new Image("/resources/Box.png");
	
	public Box(){}
	
	public Box(int row, int col, int idNum){
		this.col = col;
		this.row = row;
		this.id = "box"+idNum;
		this.isAtDest = false;
	}
	
	public Box(Box box){
		this.col = box.getCol();
		this.row = box.getRow();
		this.id = box.getId();
		this.isAtDest = box.getIsAtDest();
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
	
	public void setIsAtDest(boolean isAtDest){
		this.isAtDest = isAtDest;
	}
	
	public boolean getIsAtDest(){
		return isAtDest;
	}
	
	public char getSymbolId(){
		return symbolId;
	}
	
	@Override
	public String toString(){
		return String.format("[Box: id='%s', col=%d, row=%d, symbol='%s', isAtDest=%b]", id, col, row, symbolId, isAtDest);
	}

	@Override
	public Image getImage() {
		return boxImg;
	}
}
