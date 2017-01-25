package commands;

public interface Command {
	public enum CommandState{WAITING, RUNING, DONE};
	public void execute(Receiver rec);
}