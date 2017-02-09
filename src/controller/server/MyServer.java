package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import controller.ClientHandler;

public class MyServer{

	private int port;
	private ClientHandler ch;
	private volatile boolean stop;
//	private Thread clientThread;
	private Socket currClient;
//	private int numOfClients = 0;

	public MyServer(int port, ClientHandler ch){
		this.port = port;
		this.ch = ch;
		stop = false;
	}

	public void runServer() throws SocketTimeoutException, IOException{
		ServerSocket server = new ServerSocket(port);
		server.setSoTimeout(1000);
		
		do{
//			try{

				currClient = server.accept();
//				currSocket = currClient;
				
				class SocketThread extends Thread{

					private Socket currClient;
					
					public void setCurrSocket(Socket currClient){
						this.currClient = currClient;
					}
					
					@Override
					public void run(){
						try{
							ch.handleClient(currClient.getInputStream(), currClient.getOutputStream());
						}catch(Exception e){
							System.err.println("Error! " + e.getMessage());;
						}


					}
				}
				SocketThread clientThread = new SocketThread();
				clientThread.setCurrSocket(currClient);
				clientThread.start();
//				new Thread(new Runnable() {
//					
//					
//					
//					@Override
//					public void run(){
//						try{
//							ch.handleClient(currClient.getInputStream(), currClient.getOutputStream());
//						}catch(Exception e){
//							System.err.println("Error! " + e.getMessage());;
//						}
//
//
//					}
//				}).start();
//				clientThread.start();

//			}catch(SocketTimeoutException e){
//				System.err.println("Error! " + e.getMessage());
//			}

		}while(!stop);
		closeClient();
		server.close();
	}

	public void closeServer() throws Exception{
		this.stop = true;
	}

	public Socket getRunningSocket(){
		return currClient;
	}

	public void closeClient() throws IOException{
		currClient.getInputStream().close();
		currClient.getOutputStream().close();
		currClient.close();
//		numOfClients = 0;
	}

}
