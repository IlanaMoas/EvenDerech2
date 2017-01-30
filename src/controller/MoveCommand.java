package controller;

import java.util.LinkedList;

import controller.Command.CommandType;


public class MoveCommand implements Command{

	private CommandState state = CommandState.WAITING;
	private String dirStr;

	public MoveCommand(){}

	public CommandState getState(){
		return state;
	}


	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.MOVE, dirStr);
		state = CommandState.DONE;
	}

	@Override
	public void setParams(LinkedList<String> args) {
		dirStr = args.getFirst();
	}
	
	@Override
	public CommandType getType() {
		return CommandType.MOVE;
	}

}
