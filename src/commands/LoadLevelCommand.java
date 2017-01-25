package commands;

import commands.Receiver.CommandType;

public class LoadLevelCommand implements Command{

	private CommandState state = CommandState.WAITING;
	private String fileName;

	public LoadLevelCommand(String fileName){
		this.fileName = fileName;
	}
	
	public CommandState getState(){
		return state;
	}

	
	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.LOAD, fileName);
		state = CommandState.DONE;
	}
}
