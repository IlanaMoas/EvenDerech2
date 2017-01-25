package commands;

import commands.Receiver.CommandType;

public class MoveCommand implements Command{

	private CommandState state = CommandState.WAITING;
	private String dirStr;

	public MoveCommand(String direction){	
		this.dirStr = direction;
	}

	public CommandState getState(){
		return state;
	}


	public void execute(Receiver rec) {
		state = CommandState.RUNING;
		rec.action(CommandType.MOVE, dirStr);
		state = CommandState.DONE;
	}

}
