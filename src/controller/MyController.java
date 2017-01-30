package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

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
	private boolean stop = false;

	public MyController(View ui, Model model){
		this.ui = ui;
		this.model = model;
		this.rec = new Receiver();
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
		//		if(o == ui){
		String commandKey = commandArgs.removeFirst();
		Command currCommand = commandsMap.get(commandKey);
		currCommand.setParams(commandArgs);

		insertCommand(currCommand);


	}

	@Override
	public void start() {
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
						currCommand.execute(rec);
					}
				}
			}
		};
		manageCommandQueue.start();
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

}
