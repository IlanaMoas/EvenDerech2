package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer{

	private int port;
	private ClientHandler ch;
	private volatile boolean stop;
	private Thread serverThread;
	
	public MyServer(int port, ClientHandler ch){
		this.port = port;
		this.ch = ch;
		stop = false;
	}
	
	public void runServer() throws Exception{
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(1000);
		while(!stop){
			try{
				Socket currClient = server.accept();
				serverThread = new Thread(){
					public void run(){
						try{
//							ch.handleClient(currClient.getInputStream(), currClient.getOutputStream());
						}catch(Exception e){
							
						}
					}
				};
				serverThread.start();
			}catch(Exception e){
				
			}
		}
	}
	
}
