package client;

import controller.Controller;
import view.GameWindow;
import view.StartPanel;


/**
 * Represents game window on which game panel and game-play take place.
 * @author Roger
 *
 */
public class ClientGameWindow extends GameWindow{
	
	/**
	 * Constructor for game window. Initializes fields, creates window and adds JComponents to the window.
	 */
	public ClientGameWindow() {
		super();
	} 

	public static void main(String[] args) {
		ClientGameWindow gw = new ClientGameWindow();
		//Indicate to controller that this is server
		Controller.getSingleton().setServerFlag(false);
		new StartPanel(gw);
	}
}