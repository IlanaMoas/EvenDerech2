package controller;

import java.util.LinkedList;



public class DisplayLevelCommand extends Command{

	private CommandState state = CommandState.WAITING;
	
	
	public DisplayLevelCommand(){	}
	
	public CommandState getState(){
		return state;
	}


	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.DISPLAY, null);
		state = CommandState.DONE;
	}
	

	@Override
	public void setParams(LinkedList<String> args) {}

	@Override
	public CommandType getType() {
		return CommandType.DISPLAY;
	}

	

}
