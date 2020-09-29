package freeSpace;

import java.awt.Color;
import java.awt.Graphics;

public class Dot {
	
	public static final int SIZE = 16;
	private int x,y;
	private boolean moving;
	private Client client;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private int xVelocity, yVelocity;
	private int speed = 2;
	private Color color;
	boolean controlledDot;
	
	public Dot(Color c, int startX, int startY, boolean controlledDot) {
		
		//x = FreeSpace.WIDTH / 2 - SIZE / 2;
		//y = FreeSpace.HEIGHT / 2 - SIZE / 2;
		this.controlledDot = controlledDot;
		x = startX;
		y = startY;
		moving = false;
		xVelocity = 0;
		yVelocity = 0;
		this.color = c;
	}
	
	public Dot(Color c, int startX, int startY, boolean controlledDot, Client client) { // remote dot
		
		//x = FreeSpace.WIDTH / 2 - SIZE / 2;
		//y = FreeSpace.HEIGHT / 2 - SIZE / 2;
		this.controlledDot = controlledDot;
		x = startX;
		y = startY;
		moving = false;
		xVelocity = 0;
		yVelocity = 0;
		this.color = c;
		this.client = client;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		//g.fillRect(x, y, SIZE, SIZE);
		g.fillOval(x, y, SIZE, SIZE);
		if(moving) {
			client.ClientWrite(Integer.toString(x) + ',' + Integer.toString(y));
		}
	}
	
	public void changeXDir(int dir) {
		xVelocity = dir * speed;
	}
	
	public void changeYDir(int dir) {
		yVelocity = dir * speed;
	}
	
	public void update() {
		x += xVelocity * speed;
		y += yVelocity * speed;
		//System.out.print(x);
		
		if (x + SIZE >= FreeSpace.WIDTH) x = 0;
		else if (x <= 0) x = FreeSpace.WIDTH - SIZE;
		if (y + SIZE >= FreeSpace.HEIGHT) y = 0;
		else if (y <= 0) y = FreeSpace.HEIGHT - SIZE;
	}
	
	public void stopx() {
		xVelocity = 0;
		//yVelocity = 0;
	}
	
	public void stopy() {
		yVelocity = 0;
	}
}
