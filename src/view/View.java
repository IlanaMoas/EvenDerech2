package view;

import model.data.Element;


public interface View {
	public void displayData(Element[][] data);
	public int getUserCommand();
}
