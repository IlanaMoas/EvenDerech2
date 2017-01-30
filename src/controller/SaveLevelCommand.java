package controller;

import java.util.LinkedList;

import controller.Receiver.CommandType;

public class SaveLevelCommand implements Command{

	private CommandState state = CommandState.WAITING;
	private String fileName;

	public SaveLevelCommand(){
//		this.fileName = fileName;
	}
	
	public CommandState getState(){
		return state;
	}


	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.SAVE, fileName);
		state = CommandState.DONE;
	}

	@Override
	public void setParams(LinkedList<String> args) {
		fileName = args.getFirst();
	}

}
