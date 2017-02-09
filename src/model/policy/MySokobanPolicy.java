package model.policy;

import java.util.ArrayList;

import model.data.*;
import model.data.Level.Direction;


public class MySokobanPolicy {
	public static boolean canPlayerMove(Level level, Direction dir){
		int row = level.getMainPlayer().getRow();
		int col = level.getMainPlayer().getCol();
		ArrayList<ArrayList<Element>> levelGrid = level.getVirtualLevel();
		if(dir != Direction.UP && dir != Direction.DOWN && 
				dir != Direction.LEFT && dir != Direction.RIGHT){
			return false;
		}
		Element neighbor = null, next = null;
		switch(dir){
		case UP : 
			if((row-2) >= 0){
				neighbor = levelGrid.get(row-2).get(col);
			}
			next = levelGrid.get(row-1).get(col);
			break;
		case DOWN :
			if((row+2) < levelGrid.size()){
				neighbor = levelGrid.get(row+2).get(col);
			}
			next = levelGrid.get(row+1).get(col);
			break;
		case RIGHT :
			if((col+2) < levelGrid.get(0).size()){
				neighbor = levelGrid.get(row).get(col+2);
			}
			next = levelGrid.get(row).get(col+1);
			break;
		case LEFT :
			if((col-2) >= 0){
				neighbor =levelGrid.get(row).get(col-2);
			}
			next = levelGrid.get(row).get(col-1);
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
		ArrayList<ArrayList<Element>> grid = level.getVirtualLevel();
		int row = level.getMainPlayer().getRow();
		int col = level.getMainPlayer().getCol();
		switch (dir) {
		case UP:
			if(grid.get(row-1).get(col) != null && grid.get(row-1).get(col).getId().startsWith("box")){
				return true;
			}
			break;
		case DOWN:
			if(grid.get(row+1).get(col) != null && grid.get(row+1).get(col).getId().startsWith("box")){
				return true;
			}
			break;
		case LEFT:
			if(grid.get(row).get(col-1) != null && grid.get(row).get(col-1).getId().startsWith("box")){
				return true;
			}
			break;
		case RIGHT:
			if(grid.get(row).get(col+1) != null && grid.get(row).get(col+1).getId().startsWith("box")){
				return true;
			}
			break;
		}
		return false;
	}
	
	public static void move(Level level, Direction dir, int potentialBoxRow, int potentialBoxCol){
		if(MySokobanPolicy.canPlayerMove(level, dir)){
			if(MySokobanPolicy.isPlayerMovingBox(level, dir)){
				Box box = (Box)level.getVirtualLevel().get(potentialBoxRow).get(potentialBoxCol);
				level.move(box, dir);
			}
			level.move(level.getMainPlayer(), dir);
		}
		else{
			System.err.println("Error! this move is illegal");
		}
	}
}
