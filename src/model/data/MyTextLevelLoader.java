package model.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyTextLevelLoader implements LevelLoader {

	private final int NONE = 32;
	private final int WALL = 35;
	private final int BOX = 64;
	private final int PLAYER = 65;
	private final int BOX_DEST = 111;
	private final int NEW_LINE = 10;
	private int boxCounter = 0;
	private int destCounter = 0;
	private int wallCounter = 0;
	private int playerCounter = 0;
	private int rowCounter = 0;
	private int colCounter = 0;


	public Level loadLevel(InputStream is) {
		try {
			calcDimentions(is);

		} catch (IOException readException1) {
			System.err.println("Error reading stream when calculating dimentions.");
			readException1.printStackTrace();
		}

		try {
			((FileInputStream)is).getChannel().position(0);
		} catch (IOException resetException) {
			System.err.println("Error in stream reset.");
			resetException.printStackTrace();
		}
		try {
			ArrayList<ArrayList<Element>> board = createGrid(is);
			Level level = new Level();
			//			level.setLevelName();
			level.setVirtualLevel(board);

			return level;
		}catch (IOException readException2) {
			System.err.println("Error reading stream when creating grid level.");
			readException2.printStackTrace();
		}

		finally{
			try{
				is.close();
			}catch(IOException closeException){
				System.err.println("Couldn't close stream.");
				closeException.printStackTrace();
			}
		}

		System.err.println("Couldn't creat level. please load a valid text file.");
		return null;


	}



	public void calcDimentions(InputStream is) throws IOException{

		int next = is.read();
		int prevColCounter = 0;
		while(next != -1){

			if(next == NEW_LINE){
				colCounter = 0;
				rowCounter++;
				if(rowCounter > 1 && prevColCounter != colCounter){
					System.err.println("Error! Format wrong. Level shold be a 2D array");
				}
				prevColCounter = colCounter;
			}
			else{
				colCounter++;
			}
			next = is.read();
		}
		rowCounter++;
	}

	public ArrayList<ArrayList<Element>> createGrid(InputStream is) throws IOException{
		int next;
		ArrayList<ArrayList<Element>> board = new ArrayList<ArrayList<Element>>();//Element[rowCounter][colCounter];
		ArrayList<Element> row;
		for (int i = 0; i < rowCounter; i++) {
			row = new ArrayList<Element>();
			for (int j = 0; j < colCounter+1; j++) {
				next = is.read();
				switch(next){
				case WALL:
//					board[i][j] = new Wall(i, j, wallCounter);
					row.add(new Wall(i, j, wallCounter));
					wallCounter++;
					break;
				case BOX:
//					board[i][j] = new Box(i, j, boxCounter);
					row.add(new Box(i, j, boxCounter));
					boxCounter++;
					break;
				case PLAYER:
//					board[i][j] = new Player(i, j, playerCounter);
					row.add(new Player(i, j, playerCounter));
					playerCounter++;
					break;
				case BOX_DEST:
//					board[i][j] = new BoxEndPoint(i, j, destCounter);
					row.add(new BoxEndPoint(i, j, destCounter));
					break;
				case NONE:
//					board[i][j] = null;
					row.add(null);
					break;
				case NEW_LINE:
					break;
				}

			}
			board.add(row);
//			row.clear();
		}
		return board;
	}

}
