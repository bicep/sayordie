package multiplayer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
	private String ip;
	private int port;
	private Socket connection;
	private BufferedReader br;
	private PrintWriter pw;

	public GameClient (String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		try {
			this.connection = new Socket(this.ip, this.port);
			this.br = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
			this.pw = new PrintWriter(this.connection.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void sendJSONToServer(boolean [] barray) {
		WriteJSONThread th = new WriteJSONThread(this.pw, barray);
		th.run();
	}
	
	/*
	public void readFromServer() {
		String msg = "";
		try {
			//this keeps waiting until there's a message
			while((msg = this.br.readLine()) != null)
			{
				System.out.println("Message received from server: " + msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public void closeConnection() {
		try {
			this.pw.close();
			this.br.close();
			this.connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
