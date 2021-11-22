package freeSpace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class RemoteFreeSpace extends Canvas implements Runnable, ActionListener {
	
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
	public Dot d1, d2;
	private Client client;
	public int dot1Start, dot2Start;
	
	public RemoteFreeSpace(Client client, int dotStart1, int dotStart2) {
		
		this.dot1Start = dotStart1;
		this.dot2Start = dotStart2;
		this.client = client;
		int x = FreeSpace.HEIGHT / 2;
		int y = FreeSpace.HEIGHT - 100;
		d1 = new Dot(Color.white,dot1Start,dot1Start,true,this.client); // my dot
		d2 = new Dot(Color.green,dot2Start,dot2Start,false); // other player's dot
		canvasSetup();
		AppWindow w = new AppWindow("FreeSpace", this);
		this.frame = w.getJFrame();
		this.addKeyListener(new RemoteKeyInput(d1, client));
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
				//System.out.print("FPS: " + frames);
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
		d1.draw(g);
		d2.draw(g);
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
		d1.update();
		d2.update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		running = false;
		this.frame.dispose();
		new StartMenu("Menu");
		
	}

}
