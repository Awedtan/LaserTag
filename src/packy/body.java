package packy;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class body extends JPanel implements KeyListener, MouseListener, MouseMotionListener{
	
	static int playerWidth = 50;
	static int playerHeight = 50;
	static int playerOffsetX = 300;
	static int playerOffsetY = 300;
	static int playerSpeed = 10;
	
	static int playerCenterX;
	static int playerCenterY;
	static int mouseX;
	static int mouseY;
	
	static int xPew = -10;
	static int yPew = -10;
	
	static int fps = 60;
	static body panel = new body();
	static JFrame frame;
	
	public body() {
		
		setPreferredSize(new Dimension(1000, 800));
		setBackground(new Color(200, 200, 200));
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		shoot.shootLaser(g);
		rotate.rotatePlayer(g);
		drawPlayer(g);
	}
	
	public void drawPlayer(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(playerOffsetX, playerOffsetY, playerWidth, playerHeight);
		g2.fillRect((playerOffsetX-40), playerOffsetY, playerHeight, playerWidth/5);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xPew = e.getX();
		yPew = e.getY();
		panel.repaint();
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			
			playerOffsetY -= playerSpeed;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			
			playerOffsetX -= playerSpeed;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			
			playerOffsetY += playerSpeed;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			
			playerOffsetX += playerSpeed;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
	private static void delay (int fps){
		
		try{
			
		    Thread.sleep (fps);
		}
		catch (InterruptedException e) {
		}
	}
	
	public static void main(String[] args) {
		
		frame = new JFrame();
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		while(true) {
			
			delay(1000/fps);
			panel.repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}