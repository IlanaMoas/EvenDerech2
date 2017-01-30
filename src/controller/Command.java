package controller;

import java.util.LinkedList;

public interface Command {
	public enum CommandState{WAITING, RUNING, DONE};
	public void execute(Receiver rec);
	public void setParams(LinkedList<String> args);
}