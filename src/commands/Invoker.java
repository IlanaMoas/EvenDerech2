package commands;

import java.util.Scanner;

public class Invoker {
	
	public static void main(String[] args) {
		System.out.println("Welcome to Sokoban Online");
		Receiver rec = new Receiver();
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("Please enter command");
			String command[] = scanner.nextLine().split(" ");
			
			Command actualCommand = null;
			switch(command[0]){
			case("Load"):
				actualCommand = new LoadLevelCommand(command[1]);
				actualCommand.execute(rec);
				break;
			case("Display"):
				actualCommand = new DisplayLevelCommand();
				actualCommand.execute(rec);
				break;
			case("Move"):
				actualCommand = new MoveCommand(command[1]);
				actualCommand.execute(rec);
				break;
			case("Save"):
				actualCommand = new SaveLevelCommand(command[1]);
				actualCommand.execute(rec);
				break;
			case("Exit"):
				scanner.close();
				actualCommand = new ExitCommand();
				actualCommand.execute(rec);
				
				break;
			
			default:
				System.err.println("Errot! command not found.");
				break;
			}
		}
	}
}
