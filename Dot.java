package freeSpace;

import java.awt.Color;
import java.awt.Graphics;

public class Dot {
	
	public static final int SIZE = 16;
	private int x,y;
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
		
		xVelocity = 0;
		yVelocity = 0;
		this.color = c;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x, y, SIZE, SIZE);	
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
	
	public void stop() {
		xVelocity = 0;
		yVelocity = 0;
	}
	
}
