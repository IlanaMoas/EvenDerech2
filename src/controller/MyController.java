package controller;

//import java.io.InputStream;
//import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

import controller.Command.CommandType;
import controller.server.MyServer;
import model.Model;
import view.View;

public class MyController implements Controller {
	//	private final int MOVE_UP = 1;
	//	private final int MOVE_DOWN = 2;
	//	private final int MOVE_LEFT = 3;
	//	private final int MOVE_RIGHT = 4;

	private View ui;
	private Model model;
	private Receiver rec;
	private LinkedBlockingQueue<Command> commandsBlockingQueue;
	private HashMap<String, Command> commandsMap;
	private Thread manageCommandQueue;
	private volatile boolean stop;
	private Thread serverThread;

	public MyController(View ui, Model model){
		this.ui = ui;
		this.model = model;
		this.rec = new Receiver();
		this.stop = false;

		commandsBlockingQueue = new LinkedBlockingQueue<Command>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};


		commandsMap = new HashMap<String, Command>();

		commandsMap.put("LOAD", new LoadLevelCommand());
		commandsMap.put("SAVE", new SaveLevelCommand());
		commandsMap.put("DISPLAY", new DisplayLevelCommand());
		commandsMap.put("MOVE", new MoveCommand());
		commandsMap.put("EXIT", new ExitCommand());


	}

	@Override
	public void update(Observable o, Object args) {
		@SuppressWarnings("unchecked")
		LinkedList<String> commandArgs = (LinkedList<String>)args;
		String commandKey = commandArgs.removeFirst();
		Command currCommand = commandsMap.get(commandKey);

		if(o == model){
			ui.displayData(args);
		}
		else if(o == ui){
			//		if(o == model){
			//			
			//			ui.displayData(args);

			currCommand.setParams(commandArgs);
			//			//		}
			//			//		else if(o == ui){
			insertCommand(currCommand);
			//		}
			//		else if(o == ui){
			//			
		}


	}

	@Override
	public void start() {

		//		manageCommandQueue = new Thread();
		manageCommandQueue = new Thread(){
			public void run() {
				while(!stop){
					Command currCommand = null;
					try {
						currCommand = commandsBlockingQueue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(currCommand != null){
						if(currCommand.getType() == CommandType.EXIT){
							callStop();
						}
						currCommand.execute(rec);
					}
				}


			}
		};
		manageCommandQueue.start();
	}

	private void callStop(){
		stop();
	}

	public void stop(){
		this.stop = true;
	}

	public void insertCommand(Command command){
		if(command != null){
			try {
				commandsBlockingQueue.put(command);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void startServer(MyServer server) {
		//		serverThread = new Thread(){

		class ServerThread extends Thread{

			private MyServer server;

			public void setServer(MyServer server){
				this.server = server;
			}

			public void run() { 
				try{
					server.runServer();
				}catch (Exception e){
					System.err.println("Error! " + e.getMessage());
				}
			}
		};

		serverThread = new ServerThread();
		((ServerThread)serverThread).setServer(server);
		serverThread.start();
	}

	@Override
	public void stopServer(MyServer server) {
		try {
			server.closeServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
