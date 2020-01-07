package packy;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class menu extends JPanel implements KeyListener, MouseListener{
	
	// TODO: right now this runs as an isolated menu, figure out how to integrate it with the actual game (and also add menus to that)
	// use my knowledge, i beg you: https://www.youtube.com/watch?v=FZWX5WoGW00

	//the necessary variables
	int screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());// Screen dimensions TODO: duplicate code
    int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());

	Image gameLogo = Toolkit.getDefaultToolkit().getImage("images/logo_resized.png");

	Font menuFont = new Font("Segoe UI", Font.PLAIN, 70);
	Color menuColor = new Color(255, 255, 255);

	// Main menu variables
	JLabel playLabel, exitLabel;
	
	// Exit confirmation label
	JLabel confirmAsk, confirmYes, confirmNo;
	
	enum STATE {
		MAIN,
		PLAY,
		EXIT
	}
	
	static STATE menuState = STATE.MAIN;

	// JPanel method, throw everything in here TODO: states don't work since components are not being added before repainting

	public menu() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setLayout(null);

		// Draws menu items based on the current state of the menu
		switch (menuState) {
			case MAIN:
				playLabel = new JLabel("Play");
				playLabel.setFont(menuFont);
				playLabel.setBounds(50, 450, 250, 120);
				playLabel.setForeground(menuColor);
				playLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO: open play menu
					}
				});
				exitLabel = new JLabel("Exit");
				exitLabel.setFont(menuFont);
				exitLabel.setBounds(50, 700, 250, 120);
				exitLabel.setForeground(menuColor);
				exitLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						menuState = STATE.EXIT;
						removeAll();
						revalidate();
						repaint();
					}
				});
		
				add(playLabel);
				add(exitLabel);
				break;

			case PLAY:
				break;
				
			case EXIT:
				confirmAsk = new JLabel("Are you sure you want to quit?", SwingConstants.CENTER);
				confirmAsk.setFont(menuFont);
				confirmAsk.setBounds(screenWidth / 4, 350, screenWidth / 2, 120);
				confirmAsk.setForeground(menuColor);

				confirmYes = new JLabel("Yes", SwingConstants.CENTER);
				confirmYes.setFont(menuFont);
				confirmYes.setBounds(screenWidth / 4 + 150, 600, 150, 120);
				confirmYes.setForeground(menuColor);
				confirmYes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
				});

				confirmNo = new JLabel("No", SwingConstants.CENTER);
				confirmNo.setFont(menuFont);
				confirmNo.setBounds(3 * screenWidth / 4 - 300, 600, 150, 120);
				confirmNo.setForeground(menuColor);
				confirmNo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						menuState = STATE.MAIN;
						removeAll();
						revalidate();
						repaint();
					}
				});

				add(confirmAsk);
				add(confirmYes);
				add(confirmNo);
				break;
		
			default:
				break;
		}

		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, screenWidth, screenHeight);

		if (menuState == STATE.MAIN) {
			g.drawImage(gameLogo, 50, 100, (2 * screenWidth/3 - 50), ((2 * screenWidth/3 - 50) / 6), this);
		}
	}

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
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
		menu myPanel = new menu();
		frame.add(myPanel);
		//so you can actually get keyboard input
		// frame.addKeyListener(myPanel);
		//so you can actually get mouse input
		// frame.addMouseListener(myPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
        frame.setResizable(false);
		frame.setVisible(true);
	}
}