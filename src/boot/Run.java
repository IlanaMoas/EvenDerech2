package boot;

import Client.ClientMain;
import controller.MyClientHandler;
import controller.MyController;
import controller.server.MyServer;
import model.MyModel;
import view.MainWindow;
import view.*;

public class Run {
	private static final String ip = "127.0.0.1";//"193.106.52.98";
	
	public static void main(String args[]){
		MainWindowController ui = new MainWindowController();
		MyModel model = new MyModel();
		MyController controller = new MyController(ui, model);
		ui.addObserver(controller);
		model.addObserver(controller);
		controller.start();

		MyClientHandler ch = new MyClientHandler(controller);
		if(args.length > 0){
			MyServer server = new MyServer(Integer.parseInt(args[1]), ch);
			try {
				server.runServer();
				String[] clientArgs = new String[2];
				clientArgs[0] = ip;
				clientArgs[1] = args[1];
				ClientMain.main(clientArgs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		runGUI();
	}
	
	private static void runGUI(){
		Thread GUIThread = new Thread(){
			@Override
			public void run(){
				MainWindow.main(null);
			}
		};
		GUIThread.start();
	}
}
