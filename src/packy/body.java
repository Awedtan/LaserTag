package packy;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class body extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{
	
	Thread mainThread;
	
	static boolean left;
	static boolean right;
	static boolean up;
	static boolean down;
	
	static int fps = 60;
	static body panel = new body();
	static JFrame frame;
	
	static int playerWidth = 20;
	static int playerHeight = playerWidth;
	static int playerOffsetX = 300;
	static int playerOffsetY = 300;
	static int playerSpeed = 5;
	
	static int playerCenterX;
	static int playerCenterY;
	static int mouseX;
	static int mouseY;
	
	static int xPew = -10;
	static int yPew = -10;
	
	int screenWidth = 1000;
	int screenHeight = 800;
	
	int[][] map = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 1, 0, 1, 1, 0, 0, 0, 1},
			{0, 0, 0, 0, 1, 1, 0, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		};
	
	int numRows = 10;
	int numCols = 10;
	int tileWidth = screenWidth/numCols;
	int tileHeight = screenHeight/numRows;
	
	@Override
	public void run() {
		
		initialize();
		
		while(true) {
			
			update();
			this.repaint();
			
			try {
				
				Thread.sleep(1000/fps);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void initialize() {
		
		
	}
	
	public void update() {
		
		player.move();
	}
	
	public body() {
		
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(new Color(200, 200, 200));
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		player.shoot(g);
		drawMap(g);
		
		player.rotate(g);
		//Any graphics method called after the rotate method WILL BE ROTATED
		//If it should not be rotated, put it BEFORE the rotate method
		drawPlayer(g);
	}
	
	public void drawMap(Graphics g) {
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				int x = col * tileWidth;
				int y = row * tileHeight;
				if(map[row][col] == 1) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, tileWidth, tileHeight);
				}
			}
		}
	}
	
	public void drawPlayer(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(playerOffsetX, playerOffsetY, playerWidth, playerHeight);
		g2.fillRect((playerOffsetX-(int)(playerWidth-playerWidth*0.2)), playerOffsetY, playerHeight, playerWidth/5);
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
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			left = true;
			right = false;
		}else if(key == KeyEvent.VK_D) {
			right = true;
			left = false;
		}else if(key == KeyEvent.VK_W) {
			up = true;
			down = false;
		}else if(key == KeyEvent.VK_S) {
			down = true;
			up = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			left = false;
		}else if(key == KeyEvent.VK_D) {
			right = false;
		}else if(key == KeyEvent.VK_W) {
			up = false;
		}else if(key == KeyEvent.VK_S) {
			down = false;
		}
	}
	
	
	
	public static void main(String[] args) {
		
		frame = new JFrame();
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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