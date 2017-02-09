package controller;

import java.util.Observer;

import controller.server.MyServer;

public interface Controller extends Observer{
	public void start();
	public void stop();
	public void insertCommand(Command c);
	public void startServer(MyServer server);
	public void stopServer(MyServer server);
}
