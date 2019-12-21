package packy;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("serial")
public class body extends JPanel implements KeyListener, MouseListener, Runnable{
	//Daniel Su, Zhenyang Cai, 2D top-down shooter game
	
	Thread mainThread;
	
	static int mousePosX;//Mouse cursor coordinates
	static int mousePosY;
	static int screenWidth = 1920;//Screen dimensions
	static int screenHeight = 1080;
	static int fps = 60;
	
	static body panel = new body();
	static JFrame frame;
	
	@Override
	public void run() {
		//Runs first and forever
		
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
		//Sets up map creation

		try {
			
			game.input = new Scanner(game.file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int mapRow = 0;
		
		while(game.input.hasNext()) {
			
			String line = game.input.nextLine();
			int length = line.length();
			
			for(int i=0; i<length; i++) {
				
				game.map[mapRow][i] = line.charAt(i);
			}
			
			mapRow++;
		}
		
		for(int i=0; i<game.map.length; i++) {
			for(int j=0; j<game.map[i].length; j++) {
				
				if(game.map[i][j] == '#')
					game.numWalls++;
				else if(game.map[i][j] == '`')
					game.numTiles++;
			}
		}
		game.walls = new Rectangle[game.numWalls];
		game.tiles = new Rectangle[game.numTiles];
		game.tileIsVisible = new boolean[game.numTiles];
	}
	
	public void update() {
		//Updates object locations, checks for collisions and visibility
		
		player.move();
		game.checkInBound();
		
		if(game.wallsInitialized && projectile.initialized && game.tilesInitialized) {
			for(int i=0; i<game.walls.length; i++) {
				
				game.checkPlayerCollision(game.walls[i]);
				
				for(int j=0; j<projectile.shots.length; j++) 
					game.checkProjectileCollision(game.walls[i], j);
			}
			
			for(int i=0; i<game.numTiles; i++) {

				if(game.checkVisible(player.model, game.tiles[i])) 
					game.tileIsVisible[i] = true;
				else{
					game.tileIsVisible[i] = false;
				}
			}
		}
		
		
	}
	
	public body() {
		//Sets up the game window and other methods
		
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(new Color(200, 200, 200));
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void paintComponent(Graphics g) {
		//Draws all graphics
		
		super.paintComponent(g);
		game.drawMap(g);
		
		for(int i=0; i<projectile.shots.length; i++)
			projectile.move(g, i);
		
		projectile.initialized = true;
		game.drawWalls(g);
		
		player.rotate(g);
		//Any graphics method called after the rotate method WILL BE ROTATED
		//If it should not be rotated, put it BEFORE the rotate method
		player.draw(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Shoots a projectile, if possible, on click
		
		int shotNum = projectile.findNext(projectile.shots);
		
		if(shotNum != -1) {
			
			mousePosX = e.getX();
			mousePosY = e.getY();
			panel.repaint();
			
			projectile.shoot(player.centerX, player.centerY, player.angle, shotNum);
			
			projectile.countX[shotNum] = 0;
			projectile.countY[shotNum] = 0;
			projectile.alive[shotNum] = true;
		}
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
		//Player movement keys

		if(e.getKeyCode() == KeyEvent.VK_A) 
			player.moveLeft = true;
		
		else if(e.getKeyCode() == KeyEvent.VK_D) 
			player.moveRight = true;
		
		else if(e.getKeyCode() == KeyEvent.VK_W) 
			player.moveUp = true;
		
		else if(e.getKeyCode() == KeyEvent.VK_S) 
			player.moveDown = true;
		
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			player.sprint = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Stopping player movement
		
		if(e.getKeyCode() == KeyEvent.VK_A) 
			player.moveLeft = false;
		
		else if(e.getKeyCode() == KeyEvent.VK_D)
			player.moveRight = false;
		
		else if(e.getKeyCode() == KeyEvent.VK_W) 
			player.moveUp = false;
		
		else if(e.getKeyCode() == KeyEvent.VK_S) 
			player.moveDown = false;
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			player.sprint = false;
	}
	
	public static void main(String[] args) {
		//Creates and displays the game window
		
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
