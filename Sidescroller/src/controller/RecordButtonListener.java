package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RecordButtonListener implements ActionListener {
	private Controller c = Controller.getSingleton();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.c.getConnected()) {
			this.c.setTextOfInstruction("Connecting Speech...");
		}
		this.c.record();
	}
}