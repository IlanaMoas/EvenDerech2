package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;


import model.MyModel;
import view.MyView;

public class MyClientHandler implements ClientHandler{

	private MyController controller = null;
	private MyView ui;
	private MyModel model;
//	private InputStream inFromClient;
//	private OutputStream outFromClient;

	private final String DISPLAY_STR = "DISPLAY";

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
		String commandStr = "";
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
			try {
				while((nextChar = inFromClient.read()) != 10){
					commandStr += (char)nextChar;
				}
				commandStr = commandStr.toUpperCase();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] commandArr = commandStr.split("\\s");
			LinkedList<String> commandAndArgsList = new LinkedList<String>();
			for(int i = 0 ; i < commandArr.length ; i++){

				commandAndArgsList.add(commandArr[i]);

			}
			if(commandArr[0] != DISPLAY_STR){
				controller.update(model, commandAndArgsList);
			}
			controller.update(ui, new LinkedList<String>().add(DISPLAY_STR));
		}

	}




	
	
}