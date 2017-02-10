package model.data;

import java.io.Serializable;

import javafx.scene.image.Image;

public interface Element extends Serializable{
	public int getCol();
	public int getRow();
	public String getId();
	public char getSymbolId();
	public String toString();
	public Image getImage();

}
