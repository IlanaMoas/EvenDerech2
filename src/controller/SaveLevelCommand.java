package controller;

import controller.Receiver.CommandType;

public class SaveLevelCommand implements Command{

	private CommandState state = CommandState.WAITING;
	private String fileName;

	public SaveLevelCommand(String fileName){
		this.fileName = fileName;
	}
	
	public CommandState getState(){
		return state;
	}


	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.SAVE, fileName);
		state = CommandState.DONE;
	}

}
