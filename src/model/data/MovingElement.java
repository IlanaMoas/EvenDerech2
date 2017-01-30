package model.data;


public interface MovingElement extends Element 
{
	public void moveLeft();
	public void moveRight();
	public void moveUp();
	public void moveDown();
	public String getId();
}
