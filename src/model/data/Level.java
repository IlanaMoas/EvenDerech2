package model.data;

import java.beans.XMLDecoder;
import java.io.Serializable;
import java.util.ArrayList;

import model.policy.LevelToString;



public class Level implements Serializable	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Direction {UP, DOWN, LEFT, RIGHT};

//	private Element virtualLevel[][];
	private ArrayList<ArrayList<Element>> virtualLevel = new ArrayList<ArrayList<Element>>();
	private ArrayList<Box> boxes = new ArrayList<Box>();
	private ArrayList<BoxEndPoint> dests = new ArrayList<BoxEndPoint>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Box> boxesInDest = new ArrayList<Box>();
	private Player mainPlayer;
	private int score = 0;
	private int numOfRows = 0;
	private int numOfCols = 0;


	public Level(){	}

	public Level(Level level){
		numOfRows = level.getVirtualLevel().size();
		numOfCols = level.getVirtualLevel().get(0).size();
//		virtualLevel = new Element[numOfRows][numOfCols];
//		for(int i = 0 ; i < numOfRows ; i++){
//			for(int j = 0 ; j < numOfCols ; j++){
//				//				System.out.println("i,j: " + i + " ," + j);
//				this.virtualLevel[i][j] = level.getVirtualLevel()[i][j];
//			}
//		}
		setVirtualLevel(level.getVirtualLevel());
		setBoxes(level.getBoxes());
		setDests(level.getDests());
		setBoxesInDest(level.boxesInDest);
		setPlayers(level.getPlayers());


	}
//	
//	public Level(XMLDecoder decoder){
//		
//	}

	public void setVirtualLevel(ArrayList<ArrayList<Element>> levelGrid){
		numOfCols = levelGrid.get(0).size();
		numOfRows = levelGrid.size();

		
//		virtualLevel = new Element[numOfRows][numOfCols];
		ArrayList<Element> row ;
		for(int i = 0 ; i < numOfRows ; i++){
			row = new ArrayList<Element>();
			for(int j = 0 ; j < numOfCols ; j++){
				Element currElement = (Element)levelGrid.get(i).get(j);
				row.add(currElement);
//				virtualLevel[i][j] = (Element)levelGrid[i][j];
				
				if(currElement != null){
					if(currElement.getId().startsWith("box")){
						boxes.add((Box)currElement);
					}
					if(currElement.getId().startsWith("dest")){
						dests.add((BoxEndPoint)currElement);
					}
					if(currElement.getId().startsWith("player")){
						players.add((Player)currElement);
						if(players.size() == 1){
							mainPlayer = players.get(0);
						}
					}
				}
				
//				else{
//					
//				}
			}
			virtualLevel.add(row);
//			row.clear();
		}
	}

	public ArrayList<ArrayList<Element>> getVirtualLevel(){
		return virtualLevel;
	}

	public int getNumOfRows(){
		return numOfRows;
	}
	
	public void setNumOfRows(int numOfRows){
		this.numOfRows = numOfRows;
	}

	public int getNumOfCols(){
		return numOfCols;
	}
	
	public void setNumOfCols(int numOfCols){
		this.numOfCols = numOfCols;
	}

	public ArrayList<Box> getBoxes(){
		return boxes;
	}

	public void setBoxes(ArrayList<Box> boxesArr){
		this.boxes = new ArrayList<Box>();
		for(Box box : boxesArr){
			this.boxes.add(box);
		}
	}

	public ArrayList<BoxEndPoint> getDests(){
		return dests;
	}

	public void setDests(ArrayList<BoxEndPoint> destsArr){
		this.dests = new ArrayList<BoxEndPoint>();
		for(BoxEndPoint dest : destsArr){
			this.dests.add(dest);
		}
	}

	public ArrayList<Box> getBoxesInDest(){
		return boxesInDest;
	}

	public void setBoxesInDest(ArrayList<Box> boxesInDestArr){
		this.boxesInDest = new ArrayList<Box>();
		for(Box box : boxesInDestArr){
			this.boxesInDest.add(box);
		}
	}



	public ArrayList<Player> getPlayers(){
		return players;
	}

	public void setPlayers(ArrayList<Player> playersArr){
		this.mainPlayer = playersArr.get(0);
		this.players = new ArrayList<Player>();
		for(Player player : playersArr){
			this.players.add(player);
		}
	}

	public void setScore(int newScore){
		score = newScore;
	}

	public int getScore(){
		return score;
	}

	public Player getMainPlayer(){
		return mainPlayer;
	}

	public void setMainPlayer(Player player){
		mainPlayer = player;
	}

	public int getNumOfBoxesInDest(){
		return boxes.size() - dests.size();
	}

	public void move(MovingElement elem, Direction dir){
		int row = elem.getRow(), col = elem.getCol();
		Element nextNeighbor = null;
		switch(dir){
		case UP:
			elem.moveUp();
			virtualLevel.get(row-1).set(col, elem);
			nextNeighbor = virtualLevel.get(row-2).get(col);
			break;
		case DOWN:
			elem.moveDown();
			virtualLevel.get(row+1).set(col, elem);
			nextNeighbor = virtualLevel.get(row+2).get(col);
			break;
		case LEFT:
			elem.moveLeft();
			virtualLevel.get(row).set(col-1, elem);
			nextNeighbor = virtualLevel.get(row).get(col-2);
			break;
		case RIGHT:
			elem.moveRight();
			virtualLevel.get(row).set(col+1, elem);
			nextNeighbor = virtualLevel.get(row).get(col+2);
			break;
		}
		if(elem.getId().startsWith("box")){
			updateBoxesInDest((Box)elem, nextNeighbor);
		}
		virtualLevel.get(row).set(col, null);
		for(BoxEndPoint dest : dests){
			if(dest.getRow() == row && dest.getCol() == col){
				virtualLevel.get(row).set(col, dest);
			}
		}

	}


	public void updateBoxesInDest(Box elem, Element nextNeighbor) {

		if(!boxesInDest.contains(elem)){
			for(BoxEndPoint boxDest : dests){
				if(elem.getRow() == boxDest.getRow() && 
						elem.getCol() == boxDest.getCol()){
					boxesInDest.add((Box)elem);
					score += 10;
				}
			}
		}
		else if(nextNeighbor != null && !nextNeighbor.getId().startsWith("Wall") && !nextNeighbor.getId().startsWith("Box")){
			for(Box box : boxesInDest){
				if(box.getRow() == elem.getRow() && box.getCol() == elem.getCol()){
					if(!(nextNeighbor.getId().startsWith("dest"))){
						score -= 10;
						boxesInDest.remove(box);
					}

					break;
				}
			}
		}

	}
	
	@Override
	public String toString(){
		return String.format("[Level: virtualLevel='%s' ,numOfRows=%d, numOfCols=%d, score=%d]", LevelToString.getToString(this), numOfRows, numOfCols, score);
	}
}




