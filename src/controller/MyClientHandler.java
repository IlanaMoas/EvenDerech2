package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;


import model.MyModel;
import view.*;

public class MyClientHandler implements ClientHandler{

	private MyController controller = null;
//	private MainWindowController ui;
//	private MyModel model;
	//	private InputStream inFromClient;
	//	private OutputStream outFromClient;

//	private final String DISPLAY_STR = "DISPLAY";

	public MyClientHandler(MyController controller){
		this.controller = controller;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		//		ui = new MyView();
		//		model = new MyModel();

		String welcome = "Welcome to Sokoban Online\n";
		String enterCommand = "please enter a command\n";
		int nextChar = 0;
		String commandStrArr = "";
		try {
			outToClient.write(welcome.getBytes());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			try {
				outToClient.write(enterCommand.getBytes());
				outToClient.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nextChar = 0;
			commandStrArr = "";
			try {
				while((nextChar = inFromClient.read()) != 10){
					commandStrArr += (char)nextChar;
				}
				commandStrArr = commandStrArr.toUpperCase();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] commandArr = commandStrArr.split("\\s");
//			LinkedList<String> argsList = new LinkedList<String>();
			String commandStr = commandArr[0];
//			for(int i = 1 ; i < commandArr.length ; i++){
//
//				argsList.add(commandArr[i]);
//
//			}
//			if(commandArr[0] == "DISPLAY"){
//				controller.update(null, commandAndArgsList);
//			}
//			LinkedList<String> tmp = new LinkedList<String>();
//			tmp.add(DISPLAY_STR);
//			controller.update(ui, tmp);
			String arg = commandArr.length > 1 ? commandArr[1] : null;
			controller.getReciever().action(Command.getCommandTypeByStr(commandStr), arg);
		}

	}






}