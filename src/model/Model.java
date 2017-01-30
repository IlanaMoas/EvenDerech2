package model;

import model.data.*;


public interface Model {
	public void moveUp();
	public void moveDown();
	public void moveRight();
	public void moveLeft();
	public Element[][] getData();
}
