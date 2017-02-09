package view;

import java.util.Observable;

import controller.Command.CommandType;
import model.data.Element;



public class MyView extends Observable implements View{

	private CommandType currUserCommand = null;
	
	public MyView(){
		
	}


	public CommandType getUserCommand() {
		return currUserCommand;
	}
	
	public void setUserCommand(CommandType commandType){
		currUserCommand = commandType;
	}

	@Override
	public void displayData(Object data) {
		
	}
	
	

}
