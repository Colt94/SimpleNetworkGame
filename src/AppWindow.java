package freeSpace;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class AppWindow {

	private JFrame frame;
	public AppWindow(String title, Object fs) {
		
		
		if(fs instanceof FreeSpace) {
			FreeSpace f = (FreeSpace)fs;
			frame = new JFrame(title);
			
			//Create the menu bar.
			JMenuBar menuBar = new JMenuBar();

			//Build the first menu.
			JMenu menu = new JMenu("Menu");
			menuBar.add(menu);
			
			JMenuItem menuItem = new JMenuItem("Main Menu");
			menuItem.addActionListener(f);
			menu.add(menuItem);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.add(f);
			frame.setJMenuBar(menuBar);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			f.start();
		}
		else if(fs instanceof StartMenu) {
			
			StartMenu f = (StartMenu)fs;
			frame = new JFrame(title);
			frame.setLayout(new FlowLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			JButton button = new JButton("Roam alone");
			JButton button2 = new JButton("Roam with a Friend");
			JButton button3 = new JButton("Quit");
			button.setPreferredSize(new Dimension(150,30));
			button2.setPreferredSize(new Dimension(150,30));
			button3.setPreferredSize(new Dimension(150,30));
			
			button.addActionListener(f);
			button2.addActionListener(f);
			button.setActionCommand("1");
			button2.setActionCommand("2");
			JPanel panel = new JPanel(new FlowLayout());
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		    panel.setPreferredSize(new Dimension(150, 500));
		    panel.setMaximumSize(new Dimension(150, 500));
			panel.add(button);
			panel.add(button2);
			panel.add(button3);
			frame.add(panel);
			frame.add(f);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		}
		
		else if(fs instanceof RemoteFreeSpace) {
			
			RemoteFreeSpace f = (RemoteFreeSpace)fs;
			frame = new JFrame(title);
			
			//Create the menu bar.
			JMenuBar menuBar = new JMenuBar();

			//Build the first menu.
			JMenu menu = new JMenu("Menu");
			menuBar.add(menu);
			
			JMenuItem menuItem = new JMenuItem("Main Menu");
			menuItem.addActionListener(f);
			menu.add(menuItem);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.add(f);
			frame.setJMenuBar(menuBar);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			f.start();
		}
	}
	
	public JFrame getJFrame() {
		return frame;
	}

}