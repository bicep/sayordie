package multiplayer;
import java.io.*;
import java.net.*;


public class GameServer {
	private ServerSocket servSock;
	private Socket client;
	private PrintWriter pw;

	public GameServer (int port) {
		try {
			this.servSock = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acceptClientLoop() {
		while (true) {
			Socket c;
			try {
				c = this.servSock.accept();
				ClientThread th = new ClientThread(c, this);
				th.start();
				System.out.println("Just accepted a client. Going to the next iteration");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/*
	public void broadcastMessage(String message, ClientThread sender) {
//		for each client but the sender, send message
		
	}
	*/
}