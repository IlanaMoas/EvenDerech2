package controller;

import java.util.LinkedList;

public abstract class Command {
	public enum CommandType {LOAD, DISPLAY, MOVE, SAVE, EXIT };
	public enum CommandState{WAITING, RUNING, DONE};
	public abstract void execute(Receiver rec);
	public abstract void setParams(LinkedList<String> args);
	public abstract CommandType getType();
	public static CommandType getCommandTypeByStr(String typeStr){
		switch(typeStr){
		case("LOAD"):
			return CommandType.LOAD;
		case("SAVE"):
			return CommandType.SAVE;
		case("DISPLAY"):
			return CommandType.DISPLAY;
		case("MOVE"):
			return CommandType.MOVE;
		case("EXIT"):
			return CommandType.EXIT;
		}
		return null;
	}
}