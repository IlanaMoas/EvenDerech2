package model.data;

import javafx.scene.image.Image;

public class Wall implements Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int col;
	private int row;
	private String id;
	private final char symbolId = '#';
	Image wallImg = new Image("/resources/Wall.jpeg");
	
	public Wall(){}
	
	public Wall(int row, int col, int idNum){
		this.col = col;
		this.row = row;
		this.id = "wall"+idNum;
	}
	
	public Wall(Wall wall){
		this.col = wall.getCol();
		this.row = wall.getRow();
		this.id = wall.getId();
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public String getId() {
		return id;
	}
	
	public char getSymbolId(){
		return symbolId;
	}


	@Override
	public String toString(){
		return String.format("[Wall: id='%s', col=%d, row=%d, symbol='%s']", id, col, row, symbolId);
	}

	@Override
	public Image getImage() {
		return wallImg;
	}
}
