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
	private ServerSocket myServer;
	//	private int numOfClients = 0;

	public MyServer(int port, ClientHandler ch){
		this.port = port;
		this.ch = ch;
		stop = false;
	}

	public void runServer() throws SocketTimeoutException, IOException{

		ServerSocket myServer = new ServerSocket(port);
//		myServer.setSoTimeout(1000);

		class ServerThread extends Thread{
			private ServerSocket server;
			private int numOfClients = 0;
			public void setSerever(ServerSocket server){
				this.server = server;
			}
			
//			public void setNumOfClients(int n){
//				numOfClients = n;
//			}

			@Override
			public void run(){
				do{
					if(numOfClients == 0){
						try{
//							this.server.setSoTimeout(10000);
							currClient = this.server.accept();
							this.numOfClients = 1;
							//				currSocket = currClient;

							//					class SocketThread extends Thread{
							//
							//						private Socket currClient;
							//
							//						public void setCurrSocket(Socket currClient){
							//							this.currClient = currClient;
							//						}
							//
							//						@Override
							//						public void run(){
							try{
								ch.handleClient(currClient.getInputStream(), currClient.getOutputStream());
							}catch(Exception e){
								System.err.println("Error! " + e.getMessage());
							}


						}catch(Exception e){
							e.printStackTrace();
						}
					}
					//											}
					//					SocketThread clientThread = new SocketThread();
					//					clientThread.setCurrSocket(currClient);
					//					clientThread.start();
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
			}
		};
		ServerThread t = new ServerThread();
		t.setSerever(myServer);
		t.start();
		//		closeClient();
		//		server.close();
	}

	public void closeServer() throws Exception{
		this.stop = true;
		closeClient();
		myServer.close();

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
