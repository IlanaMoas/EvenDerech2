package model;

import model.data.*;
import java.util.ArrayList;


public interface Model {
	public void moveUp();
	public void moveDown();
	public void moveRight();
	public void moveLeft();
	public int getScore();
	public ArrayList<ArrayList<Element>> getData();
}
