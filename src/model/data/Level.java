package model.data;

import java.io.Serializable;
import java.util.ArrayList;


public class Level implements Serializable	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Direction {UP, DOWN, LEFT, RIGHT};

	private Element virtualLevel[][];
	private ArrayList<Box> boxes;
	private ArrayList<BoxEndPoint> dests;
	private ArrayList<Player> players;
	private ArrayList<Box> boxesInDest;
	private Player mainPlayer;
	private int score = 0;
	private int numOfRows = 0;
	private int numOfCols = 0;


	public Level(){
		boxes = new ArrayList<Box>();
		dests = new ArrayList<BoxEndPoint>();
		players = new ArrayList<Player>();
		boxesInDest = new ArrayList<Box>();
	}

	public Level(Level level){
		numOfRows = level.getLevelGrid().length;
		numOfCols = level.getLevelGrid()[0].length;
		virtualLevel = new Element[numOfRows][numOfCols];
		for(int i = 0 ; i < numOfRows ; i++){
			for(int j = 0 ; j < numOfCols ; j++){
				//				System.out.println("i,j: " + i + " ," + j);
				this.virtualLevel[i][j] = level.getLevelGrid()[i][j];
			}
		}
		setBoxes(level.getBoxes());
		setDests(level.getDests());
		setBoxesInDest(level.boxesInDest);
		setPlayers(level.getPlayers());


	}

	public void setLevelGrid(Element levelGrid[][]){
		numOfCols = levelGrid[0].length;
		numOfRows = levelGrid.length;


		virtualLevel = new Element[numOfRows][numOfCols];
		for(int i = 0 ; i < numOfRows ; i++){
			for(int j = 0 ; j < numOfCols ; j++){
				virtualLevel[i][j] = levelGrid[i][j];
				if(virtualLevel[i][j] != null){
					if(virtualLevel[i][j].getId().startsWith("box")){
						boxes.add((Box)virtualLevel[i][j]);
					}
					if(virtualLevel[i][j].getId().startsWith("dest")){
						dests.add((BoxEndPoint)virtualLevel[i][j]);
					}
					if(virtualLevel[i][j].getId().startsWith("player")){
						players.add((Player)virtualLevel[i][j]);
						if(players.size() == 1){
							mainPlayer = players.get(0);
						}
					}
				}
			}
		}
	}

	public Element[][] getLevelGrid(){
		return virtualLevel;
	}

	public int getNumOfRows(){
		return numOfRows;
	}

	public int getNumOfCols(){
		return numOfCols;
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
			virtualLevel[row-1][col] = elem;
			nextNeighbor = virtualLevel[row-2][col];
			break;
		case DOWN:
			elem.moveDown();
			virtualLevel[row+1][col] = elem;
			nextNeighbor = virtualLevel[row+2][col];
			break;
		case LEFT:
			elem.moveLeft();
			virtualLevel[row][col-1] = elem;
			nextNeighbor = virtualLevel[row][col-2];
			break;
		case RIGHT:
			elem.moveRight();
			virtualLevel[row][col+1] = elem;
			nextNeighbor = virtualLevel[row][col+2];
			break;
		}
		if(elem.getId().startsWith("box")){
			updateBoxesInDest((Box)elem, nextNeighbor);
		}
		virtualLevel[row][col] = null;
		for(BoxEndPoint dest : dests){
			if(dest.getRow() == row && dest.getCol() == col){
				virtualLevel[row][col] = dest;
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
}




