package menu;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class menu extends JPanel implements KeyListener, MouseListener{
	
	// TODO: right now this runs as an isolated menu, figure out how to integrate it with the actual game (and also add game menu to that)
	// use my knowledge, i beg you: https://www.youtube.com/watch?v=FZWX5WoGW00

	//the necessary variables
	static int screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());// Screen dimensions
    static int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());

	static Image gameLogo = Toolkit.getDefaultToolkit().getImage("images/logo_resized.png");

	static Font menuFont = new Font("Segoe UI", Font.PLAIN, 70);

	static JFrame frame;

	static main menuPanel;
	static play playPanel;
	static exit exitPanel;
	
	enum STATE {
		MAIN,
		PLAY,
		EXIT
	}
	
	static STATE menuState = STATE.MAIN;

	// public static void addStatePanel() {
	// 	// TODO: write method to add the current panel to frame based on menuState
		
	// }


	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	// TODO: if this can be integrated with the body.java, the main function in either this or that needs to go
	public static void main(String[] args) {
		//makes a brand new JFrame
        frame = new JFrame();
        frame.setUndecorated(true);
		menuPanel = new main();
		playPanel = new play();
		exitPanel = new exit();
		frame.add(menuPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
		frame.setVisible(true);
	}
}