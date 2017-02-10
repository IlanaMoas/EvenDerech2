package controller;

import java.util.LinkedList;


public class LoadLevelCommand extends Command{

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
	
	@Override
	public CommandType getType() {
		return CommandType.LOAD;
	}
	
}
