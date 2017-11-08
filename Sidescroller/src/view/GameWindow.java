package view;

import javax.swing.JFrame;

import controller.Controller;
import multiplayer.GameServer;

public class GameWindow extends JFrame{
	
	private GamePanel gp;
	
	public GameWindow() {
		this.gp = new GamePanel();
		add(gp);
		setSize(1024, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} 

	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
		GameServer s;
		s = new GameServer(1664);
		Controller.getSingleton().setServer(s);
		s.acceptClientLoop();
	}
}

