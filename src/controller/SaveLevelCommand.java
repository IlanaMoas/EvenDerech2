package controller;

import java.util.LinkedList;

//import controller.Command.CommandType;


public class SaveLevelCommand extends Command{

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
	
	@Override
	public CommandType getType() {
		return CommandType.SAVE;
	}
	
}
