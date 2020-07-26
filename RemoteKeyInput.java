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
		
		if (key == KeyEvent.VK_UP) {
			dot.changeYDir(-1);
			up = true;
			client.ClientWrite("cyn1");
		}
		if (key == KeyEvent.VK_DOWN) {
			dot.changeYDir(1);
			down = true;
			client.ClientWrite("cy1");
		}
		if (key == KeyEvent.VK_W) {
			dot.changeYDir(-1);
			up = true;
			client.ClientWrite("cyn1");
		}	
		if (key == KeyEvent.VK_S) {
			dot.changeYDir(1);
			down = true;
			client.ClientWrite("cy1");
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			dot.changeXDir(1);
			right = true;
			client.ClientWrite("cx1");
		}
		if (key == KeyEvent.VK_LEFT) {
			dot.changeXDir(-1);
			left = true;
			client.ClientWrite("cxn1");
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
