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
		
		if (key == KeyEvent.VK_UP && down == false) {
			dot.changeYDir(-1);
			up = true;
			//currentDirection = directions.UP;
		}
		if (key == KeyEvent.VK_DOWN && up == false) {
			dot.changeYDir(1);
			down = true;
		}
		if (key == KeyEvent.VK_W && down == false) {
			dot.changeYDir(-1);
			up = true;
		}	
		if (key == KeyEvent.VK_S && up == false) {
			dot.changeYDir(1);
			down = true;
		}
		
		if (key == KeyEvent.VK_RIGHT && left == false) {
			dot.changeXDir(1);
			right = true;
		}
		if (key == KeyEvent.VK_LEFT && right == false) {
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
		if (!up && !down)
			dot.stopy();
		if (!right && !left) {
			dot.stopx();
		}
	}
}