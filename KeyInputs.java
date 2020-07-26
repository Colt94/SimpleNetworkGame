package freeSpace;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputs extends KeyAdapter{
	
	private Dot dot;
	
	private boolean up = false;
	private boolean down = false;
	private boolean right = false;
	private boolean left = false;
	
	boolean remote;
			
	public KeyInputs(Dot d, boolean remote) {
		this.dot = d;
		this.remote = remote;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			dot.changeYDir(-1);
			up = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			dot.changeYDir(1);
			down = true;
		}
		if (key == KeyEvent.VK_W) {
			dot.changeYDir(-1);
			up = true;
		}	
		if (key == KeyEvent.VK_S) {
			dot.changeYDir(1);
			down = true;
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			dot.changeXDir(1);
			right = true;
		}
		if (key == KeyEvent.VK_LEFT) {
			dot.changeXDir(-1);
			left = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			up = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (key == KeyEvent.VK_W) {
			up = false;
		}	
		if (key == KeyEvent.VK_S) {
			down = false;
		}
		if (key == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (key == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (!up && !down && !right && !left)
			dot.stop();
	}
}