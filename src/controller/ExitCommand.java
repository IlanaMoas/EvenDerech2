package controller;

import java.util.LinkedList;

//import controller.Receiver.CommandType;

public class ExitCommand implements Command{

private CommandState state = CommandState.WAITING;
	
	public ExitCommand(){	}
	
	public CommandState getState(){
		return state;
	}


	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.EXIT, null);
		state = CommandState.DONE;
	}

	@Override
	public void setParams(LinkedList<String> args) {}

	@Override
	public CommandType getType() {
		return CommandType.EXIT;
	}

}
