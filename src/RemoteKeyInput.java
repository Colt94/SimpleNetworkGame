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
			client.ClientWrite("cyn1");
			up = true;
			//currentDirection = directions.UP;
		}
		if (key == KeyEvent.VK_DOWN && up == false) {
			dot.changeYDir(1);
			client.ClientWrite("cy1");
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
			client.ClientWrite("cx1");
			right = true;
		}
		if (key == KeyEvent.VK_LEFT && right == false) {
			dot.changeXDir(-1);
			client.ClientWrite("cxn1");
			left = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
			up = false;
			//client.ClientWrite("stopUp");
		}
		if (key == KeyEvent.VK_DOWN) {
			down = false;
			//client.ClientWrite("stopDown");
		}
		if (key == KeyEvent.VK_W) {
			up = false;
			//client.ClientWrite("stopUp");
		}	
		if (key == KeyEvent.VK_S) {
			down = false;
			//client.ClientWrite("stopDown");
		}
		if (key == KeyEvent.VK_RIGHT) {
			right = false;
			//client.ClientWrite("stopRight");
		}
		if (key == KeyEvent.VK_LEFT) {
			left = false;
			//client.ClientWrite("stopLeft");
		}
		if (!up && !down) {
			dot.stopy();
			System.out.print("Stopping y\n");
			client.ClientWrite("stopY");
			client.ClientWrite("y" + Integer.toString(dot.getY()));
		}
		if (!right && !left) {
			dot.stopx();
			System.out.print("Stopping x\n");
			client.ClientWrite("stopX");
			client.ClientWrite("x" + Integer.toString(dot.getX()));
		}
	}
}
