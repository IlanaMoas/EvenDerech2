package controller;

import java.util.LinkedList;

public interface Command {
	public enum CommandType {LOAD, DISPLAY, MOVE, SAVE, EXIT };
	public enum CommandState{WAITING, RUNING, DONE};
	public void execute(Receiver rec);
	public void setParams(LinkedList<String> args);
	public CommandType getType();
}