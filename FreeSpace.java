package freeSpace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;


import javax.swing.JFrame;



public class FreeSpace extends Canvas implements Runnable, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4137078459986768911L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH * 9/16;
	public String windowType = "game";
	public boolean running = false;
	private Thread gameThread;
	private JFrame frame;
	private Dot d,d2;
	
	public FreeSpace() {
		
		int x = FreeSpace.HEIGHT / 2;
		d = new Dot(Color.white,x + 40,x,true);
		//d2 = new Dot(Color.green,x,x,true);
		canvasSetup();
		AppWindow w = new AppWindow("FreeSpace", this);
		this.frame = w.getJFrame();
		this.addKeyListener(new KeyInputs(d, false));
		//this.addKeyListener(new KeyInputs(d2, false));
		this.setFocusable(true);
	}
	
	private void canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		
	}
	
	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
		running = true;
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}
			if(running) draw();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.print("FPS: " + frames);
				frames = 0;
			}
		}
		
	}

	private void draw() {
		
		BufferStrategy buffer = this.getBufferStrategy();
		
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		drawBackground(g);
		d.draw(g);
		//d2.draw(g);
		g.dispose();
		buffer.show();
	}
	
	public void stop() {
		try {
			gameThread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void drawBackground(Graphics g) {
		// black background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.white);
//		Graphics2D g2d = (Graphics2D) g;
//		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10},0);
//		g2d.setStroke(dashed);
//		g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
		
	}

	private void update() {
		d.update();
		//d2.update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		running = false;
		this.frame.dispose();
		new StartMenu("Menu");
		
	}

}
