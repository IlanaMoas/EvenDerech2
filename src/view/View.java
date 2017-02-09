package view;

import controller.Command.CommandType;
import model.data.Element;


public interface View {
	public void displayData(Object data);
	public CommandType getUserCommand();
}
