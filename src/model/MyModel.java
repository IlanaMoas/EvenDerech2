package model;


//import java.io.InputStream;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.Observable;

import model.data.Element;
import model.data.Level;
import model.data.Level.Direction;


public class MyModel extends Observable implements Model {

	private Level level;
//	private LinkedList<String> args;
	
	public MyModel(){
//		this.level = level;
//		args = new LinkedList<String>();
	}
	
	@Override
	public void moveUp() {
		level.move(level.getMainPlayer(), Direction.UP);
//		addArgs("Move", "up");
//		notifyObservers(args);
//		args.clear();
		notifyObservers(level.getVirtualLevel());
	}

	@Override
	public void moveDown() {
		level.move(level.getMainPlayer(), Direction.DOWN);
//		addArgs("Move", "down");
//		notifyObservers(args);
////		args.clear();
		notifyObservers(level.getVirtualLevel());
	}

	@Override
	public void moveRight() {
		level.move(level.getMainPlayer(), Direction.RIGHT);
//		addArgs("Move", "right");
//		notifyObservers(args);
//		args.clear();
		notifyObservers(getData());
		
	}
	
	

	@Override
	public void moveLeft() {
		level.move(level.getMainPlayer(), Direction.LEFT);
//		addArgs("Move", "left");
//		notifyObservers(args);
//		args.clear();
		notifyObservers(getData());
		
	}
	
//	public void load(InputStream in){
//		
//	}

	public ArrayList<ArrayList<Element>> getData(){
		return level.getVirtualLevel();
	}
	
	public int getScore(){
		return level.getScore();
	}
	
	public Level getCurrentLevel(){
		return level;
	}
	
//	private void addArgs(String command, String commandArg){
//		args.add(command);
//		args.add(commandArg);
//	}
	
}
