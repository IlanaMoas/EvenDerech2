package controller;

import java.util.LinkedList;

import controller.Receiver.CommandType;


public class DisplayLevelCommand implements Command{

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






}
