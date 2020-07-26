package freeSpace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartMenu extends Canvas implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3570418831503537534L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH * 9/16;
	private static JFrame frame;
	private JButton button;

	public StartMenu(String title) {
		
		canvasSetup();
		
		AppWindow window = new AppWindow(title, this);
		StartMenu.frame = window.getJFrame();
//		
//		button = new JButton("Roam by yourself");
//		button.addActionListener(this);
//		//button2 = new JButton("Roam by yourself");
//		button.setBounds(100,100,40, 40);
//		JPanel panel = new JPanel();
//		panel.add(button);
//		//frame.setLayout( new GridBagLayout() );
//		//frame.add(button, new GridBagConstraints());
//		StartMenu.frame.add(button);
//		StartMenu.frame.setVisible(true);
//		
	}
	
	private void canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "1") {
			StartMenu.frame.dispose();
			new FreeSpace();
		}
		else if (e.getActionCommand() == "2") {
			
			StartMenu.frame.dispose();
			Client c = new Client("","10.0.0.174",111);
			try {
				c.run();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("Waiting on other player..\n");
			int data = 0;
			try {
				data = c.dataAvailable();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("Waiting on other player..\n");
			while(data == 0) {
				try {
					data = c.dataAvailable();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			System.out.print("Waiting on other player..\n");
	        String r = c.ClientRead(); // block until both players are connected
	        System.out.print(r);
	        String[] startPositions = r.split(",");
	        RemoteFreeSpace fs = new RemoteFreeSpace(c,  Integer.parseInt(startPositions[0]), Integer.parseInt(startPositions[1]));	
	        (new Thread(new HandleDotState(fs,c,fs.d2))).start();
		}
	}
	
	
	public static void main(String[] args) {
		
		new StartMenu("Menu");
		
		//new FreeSpace();

	}


}
