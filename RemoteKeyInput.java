package freeSpace;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RemoteKeyInput extends KeyAdapter {
	
	private Dot dot;
	
	private boolean up = false;
	private boolean down = false;
	private boolean right = false;
	private boolean left = false;
	private Client client;
			
	public RemoteKeyInput(Dot d, Client client) {
		this.dot = d;
		this.client = client;
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
			client.ClientWrite("stopUp");
		}
		if (key == KeyEvent.VK_DOWN) {
			down = false;
			client.ClientWrite("stopDown");
		}
		if (key == KeyEvent.VK_W) {
			up = false;
			client.ClientWrite("stopUp");
		}	
		if (key == KeyEvent.VK_S) {
			down = false;
			client.ClientWrite("stopDown");
		}
		if (key == KeyEvent.VK_RIGHT) {
			right = false;
			client.ClientWrite("stopRight");
		}
		if (key == KeyEvent.VK_LEFT) {
			left = false;
			client.ClientWrite("stopLeft");
		}
		if (!up && !down)
			dot.stopy();
		if (!right && !left) {
			dot.stopx();
		}
	}
}
