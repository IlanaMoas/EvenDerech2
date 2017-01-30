package model;


import java.util.ArrayList;
import java.util.Observable;

import model.data.Element;
import model.data.Level;
import model.data.Level.Direction;


public class MyModel extends Observable implements Model {

	Level level;
	
	public MyModel(){
//		this.level = level;
	}
	
	@Override
	public void moveUp() {
		level.move(level.getMainPlayer(), Direction.UP);
		// TODO Auto-generated method stub
		
		notifyObservers();
	}

	@Override
	public void moveDown() {
		level.move(level.getMainPlayer(), Direction.DOWN);
		// TODO Auto-generated method stub
		
		notifyObservers();
	}

	@Override
	public void moveRight() {
		level.move(level.getMainPlayer(), Direction.RIGHT);
		
		// TODO Auto-generated method stub
		
		
		notifyObservers();
		
	}
	
	

	@Override
	public void moveLeft() {
		level.move(level.getMainPlayer(), Direction.LEFT);
		// TODO Auto-generated method stub
		
		notifyObservers();
	}

	public Element[][] getData(){
		return level.getLevelGrid();
	}
	
	public Level getCurrentLevel(){
		return level;
	}
	
}
