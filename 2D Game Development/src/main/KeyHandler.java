package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, leftPressed, downPressed, rightPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// When a key is pressed it generate a Virtual key codes (VK) this is also the same concept used for the computers to read which key are pressed
		// this variable in initialize here takes the codes (not in binary) and stores them
		int code = e.getKeyCode();
		
		// if the Virtual key code that's stored within the 'code variable' for that certain instance corresponds to 'W' 'A' 'S' or 'D' this if and else statement are used
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
		
		
	}

	// if this is commented out - the update method within the Player class that's called within the update method within the GamePanel that's inside a loop
	// in short, the method above will get repeated so many times it will make the player move on its own
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}
	 
}
