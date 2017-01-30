package controller;

import java.util.LinkedList;

import controller.Receiver.CommandType;

public class LoadLevelCommand implements Command{

	private CommandState state = CommandState.WAITING;
	private String fileName;

	public LoadLevelCommand(){	}
	
	public CommandState getState(){
		return state;
	}

	
	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.LOAD, fileName);
		state = CommandState.DONE;
	}

	@Override
	public void setParams(LinkedList<String> args) {
		fileName = args.getFirst();
	}
}
