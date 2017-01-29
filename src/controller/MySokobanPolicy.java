package controller;

import model.Box;
import model.Element;
import model.Level;
import model.Level.Direction;

public class MySokobanPolicy {
	public static boolean canPlayerMove(Level level, Direction dir){
		int row = level.getMainPlayer().getRow();
		int col = level.getMainPlayer().getCol();
		Element levelGrid[][] = level.getLevelGrid();
		if(dir != Direction.UP && dir != Direction.DOWN && 
				dir != Direction.LEFT && dir != Direction.RIGHT){
			return false;
		}
		Element neighbor = null, next = null;
		switch(dir){
		case UP : 
			if((row-2) >= 0){
				neighbor =levelGrid[row-2][col];
			}
			next = levelGrid[row-1][col];
			break;
		case DOWN :
			if((row+2) < levelGrid.length){
				neighbor =levelGrid[row+2][col];
			}
			next = levelGrid[row+1][col];
			break;
		case RIGHT :
			if((col+2) >= 0){
				neighbor =levelGrid[row][col+2];
			}
			next = levelGrid[row][col+1];
			break;
		case LEFT :
			if((col-2) < levelGrid[0].length){
				neighbor =levelGrid[row][col-2];
			}
			next = levelGrid[row][col-1];
			break;
			
		default:
			System.err.println("Error mving player. Direction is illegal.");

		}
		return checkCellsStatus(next, neighbor);
	}
	
	private static boolean checkCellsStatus(Element neighborCell, Element nextNeighborCell){
		if(neighborCell != null && (neighborCell.getId().startsWith("wall") || 
				(neighborCell.getId().startsWith("box") && nextNeighborCell != null &&
				!nextNeighborCell.getId().startsWith("dest")))){
			return false;
		}
		return true;
	}
	
	public static boolean isPlayerMovingBox(Level level, Direction dir){
		Element grid[][] = level.getLevelGrid();
		int row = level.getMainPlayer().getRow();
		int col = level.getMainPlayer().getCol();
		switch (dir) {
		case UP:
			if(grid[row-1][col] != null && grid[row-1][col].getId().startsWith("box")){
				return true;
			}
			break;
		case DOWN:
			if(grid[row+1][col] != null && grid[row+1][col].getId().startsWith("box")){
				return true;
			}
			break;
		case LEFT:
			if(grid[row][col-1] != null && grid[row][col-1].getId().startsWith("box")){
				return true;
			}
			break;
		case RIGHT:
			if(grid[row][col+1] != null && grid[row][col+1].getId().startsWith("box")){
				return true;
			}
			break;
		}
		return false;
	}
	
	public static void move(Level level, Direction dir, int potentialBoxRow, int potentialBoxCol){
		if(MySokobanPolicy.canPlayerMove(level, dir)){
			if(MySokobanPolicy.isPlayerMovingBox(level, dir)){
				Box box = (Box)level.getLevelGrid()[potentialBoxRow][potentialBoxCol];
				level.move(box, dir);
			}
			level.move(level.getMainPlayer(), dir);
		}
		else{
			System.err.println("Error! this move is illegal");
		}
	}
}
