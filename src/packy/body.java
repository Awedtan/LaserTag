package packy;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class body extends JPanel implements KeyListener, MouseListener, Runnable{
	
	Thread mainThread;
	
	int screenWidth = 1000;
	int screenHeight = 800;
	static int fps = 60;
	static body panel = new body();
	static JFrame frame;
	
	static int mouseX;
	static int mouseY;
	
	static int xPew = -10;
	static int yPew = -10;
	
	int numRows = 10;
	int numCols = 10;
	int numWalls = 0;
	int tileWidth = screenWidth/numCols;
	int tileHeight = screenHeight/numRows;
	
	Rectangle[] walls;
	
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
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				
				if(map[i][j] == 1)
					numWalls++;
			}
		}
		
		walls = new Rectangle[numWalls];
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
		player.draw(g);
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

		if(e.getKeyCode() == KeyEvent.VK_A) {
			
			player.moveLeft = true;
			player.moveRight = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			
			player.moveRight = true;
			player.moveLeft = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_W) {
			
			player.moveUp = true;
			player.moveDown = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			
			player.moveDown = true;
			player.moveUp = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A) 
			player.moveLeft = false;
		
		else if(e.getKeyCode() == KeyEvent.VK_D) 
			player.moveRight = false;
		
		else if(e.getKeyCode() == KeyEvent.VK_W) 
			player.moveUp = false;
		
		else if(e.getKeyCode() == KeyEvent.VK_S) 
			player.moveDown = false;
	}
	
	public static void main(String[] args) {
		
		frame = new JFrame();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}