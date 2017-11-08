
package multiplayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import controller.Controller;

public class ReadKeyThread extends Thread {
	
	private BufferedReader incomingJSON;
	private GameClient gameClient;
	private boolean barray[];
	private Controller controller;
	
	public ReadKeyThread (GameClient gc, BufferedReader br) {
		this.incomingJSON = br;
		this.gameClient = gc;
		this.barray = new boolean[6];
		this.resetBooleans();
		this.controller = Controller.getSingleton();
	}
	
	public void resetBooleans() {
		for (int i = 0; i<this.barray.length; i++) {
			this.barray[i] = false;
		}
	}

	@Override
	public void run()
	{
		String json = "";
		try {
			while(true)
			{
				if ((json = this.incomingJSON.readLine()) != null) {
					System.out.println("I received the following JSON: " + json);
					// parseJSON, and sets the booleans
					parseJSONToSetBool(json);
					controller.updatePlayer2Movement(this.barray);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Connection was closed!");
	}
	
	public void parseJSONToSetBool(String json) {
		        
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject keyObject = reader.readObject();
		reader.close();
		
		//now grab the boolean values we need
		this.barray[0] = keyObject.getBoolean("leftPressed");
		this.barray[1] = keyObject.getBoolean("rightPressed");
		this.barray[2] = keyObject.getBoolean("jumpPressed");
		this.barray[3] = keyObject.getBoolean("leftReleased");
		this.barray[4] = keyObject.getBoolean("rightReleased");
		this.barray[5] = keyObject.getBoolean("jumpReleased");
		
	}
}