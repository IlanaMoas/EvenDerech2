package model.data;

import javafx.scene.image.Image;

public class BoxEndPoint implements Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int col;
	private int row;
	private String id;
	private final char symbolId = 'o';
	private Image destImg = new Image("/resources/BoxEndPoint.jpeg");
	//	private boolean isOccupied;

	public BoxEndPoint(){}
	
	public BoxEndPoint(int row, int col, int idNum){
		this.col = col;
		this.row = row;
		this.id = "dest"+idNum;
		//		isOccupied = false;
	}
	
	public BoxEndPoint(BoxEndPoint boxEndPoint){
		this.col = boxEndPoint.getCol();
		this.row = boxEndPoint.getRow();
		this.id = boxEndPoint.getId();
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
		return String.format("[BoxEndPoint: id='%s', col=%d, row=%d, symbol='%s']", id, col, row, symbolId);
	}

	@Override
	public Image getImage() {
		return destImg;
	}
}
