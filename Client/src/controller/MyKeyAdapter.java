package controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MyKeyAdapter extends KeyAdapter {
	
	private Controller c;
	private boolean [] barray;
	
	public MyKeyAdapter() {
		this.c = Controller.getSingleton();
		this.barray = new boolean[6];
		this.resetBooleans();
	}
	
	public void resetBooleans() {
		for (int i = 0; i<this.barray.length; i++) {
			this.barray[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent kp) {
		
		
		if (kp.getKeyCode() == KeyEvent.VK_RIGHT && this.c.getPlayer().getMoveableRight() == true) {
			this.c.getPlayer().setDirection(2);
			this.c.getPlayer().setJumpRight(true);
			this.resetBooleans();
			this.barray[1] = true;
			//We get the client late so make sure we retrieve it from controller
			Controller.getSingleton().getClient().sendJSONToServer(this.barray);
		}
		if (kp.getKeyCode() == KeyEvent.VK_LEFT && this.c.getPlayer().getMoveableLeft() == true) {
			this.c.getPlayer().setDirection(3);
			this.c.getPlayer().setJumpRight(false);
			this.resetBooleans();
			this.barray[0] = true;
			this.c.getClient().sendJSONToServer(this.barray);
		}
		
		if (kp.getKeyCode() == KeyEvent.VK_SPACE) {
			this.c.getPlayer().checkJump();
			this.resetBooleans();
			this.barray[2] = true;
			Controller.getSingleton().getClient().sendJSONToServer(this.barray);
		}
		
	}
	
	public void keyReleased(KeyEvent kp) {
		if (kp.getKeyCode() == KeyEvent.VK_LEFT || kp.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			
			this.resetBooleans();
			if (this.c.getPlayer().getDirection() == 2) {
				this.c.getPlayer().setCurrentSprite(this.c.getPlayer().getStillRightSprite());// if direction is right
				this.resetBooleans();
				this.barray[4] = true;
				this.c.getClient().sendJSONToServer(this.barray);
			}
			if (this.c.getPlayer().getDirection() == 3) {
				this.c.getPlayer().setCurrentSprite(this.c.getPlayer().getStillLeftSprite());
				this.resetBooleans();
				this.barray[3] = true;
				this.c.getClient().sendJSONToServer(this.barray);
			}
			this.c.getPlayer().setDirection(0); // set still image	
		}
		
	}
	
}