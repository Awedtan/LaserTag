package packy;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class body extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{
	//Daniel Su, Zhenyang Cai, 2D top-down shooter game
	
	Thread mainThread;
	
	static int mousePosX;//Mouse cursor coordinates
	static int mousePosY;
	// Sets screenWidth and screenHeight to the dimensions of the screen
	static int screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());//Screen dimensions
	static int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	static int fps = 120;
	
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
		
		game.numCols = Integer.parseInt(game.input.nextLine());
		game.numRows = Integer.parseInt(game.input.nextLine());
		
		game.map  = new char[game.numRows][game.numCols];
		
		game.tileWidth = screenWidth/game.numCols;
		game.tileHeight = screenHeight/game.numRows;
		
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
		
		for(int i=0; i<enemy.MAX; i++)
			enemy.initialize(i);
	}
	
	public void update() {
		//Updates object locations, checks for collisions and visibility
		
		if(game.wallsInitialized && playerProjectile.initialized && enemyProjectile.initialized && game.tilesInitialized) {
			
			player.move();
			
			for(int k=0; k<enemy.MAX; k++) {
				
				enemy.move(k);
			
				for(int i=0; i<game.walls.length; i++) {
					
					game.checkPlayerCollision(game.walls[i]);
					game.checkEnemyCollision(game.walls[i], k);
					
					for(int j=0; j<playerProjectile.shots.length; j++) 
						if(playerProjectile.alive[j])
							game.checkPlayerProjectileCollision(game.walls[i], j, k);
					
					for(int j=0; j<enemyProjectile.shots.length; j++) 
						if(enemyProjectile.alive[j])
							game.checkEnemyProjectileCollision(game.walls[i], j);
				}
				
				for(int i=0; i<playerProjectile.shots.length; i++) 
					if(!game.checkInBound(playerProjectile.shots[i])) 
						playerProjectile.kill(i);
				
				for(int i=0; i<enemyProjectile.shots.length; i++) 
					if(!game.checkInBound(enemyProjectile.shots[i])) 
						enemyProjectile.kill(i);
				
				for(int i=0; i<game.numTiles; i++) 
//					if(game.checkVisiblePlayer(player.model, game.tiles[i], player.VIEWRANGE, player.FOV))
						game.tileIsVisible[i] = true;
//					else
//						game.tileIsVisible[i] = false;
				
				if(game.checkVisibleEnemy(enemy.enemies[k], player.model, enemy.VIEWRANGE, enemy.FOV, k) && enemyProjectile.findNext(enemyProjectile.shots) != -1) 
					enemy.shoot(enemy.centerX[k], enemy.centerY[k], enemy.angle[k], enemyProjectile.findNext(enemyProjectile.shots));
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
		
		
		Graphics gPlayer = g.create();
		super.paintComponent(g);
		game.drawVisible(g);
		
		for(int i=0; i<playerProjectile.shots.length; i++)
			playerProjectile.move(g, i);
		
		for(int i=0; i<enemyProjectile.shots.length; i++)
			enemyProjectile.move(g, i);
		
		playerProjectile.initialized = true;
		enemyProjectile.initialized = true;
		
		game.drawWalls(g);
		
		for(int i=0; i<enemy.MAX; i++) {
			
			Graphics gEnemy = g.create();
			enemy.rotate(gEnemy, i);
			enemy.draw(gEnemy, i);
			gEnemy.dispose();
		}
		
		game.drawInvisible(g);
		
		player.rotate(gPlayer);
		player.draw(gPlayer);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Shoots a projectile, if possible, on click
		
		if(playerProjectile.findNext(playerProjectile.shots) != -1) {
			mousePosX = e.getX();
			mousePosY = e.getY();
			panel.repaint();
			player.shoot(player.centerX, player.centerY, player.angle, playerProjectile.findNext(playerProjectile.shots));
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
	public void mouseDragged(MouseEvent e) {
		//TODO: Fix this
		if(playerProjectile.findNext(playerProjectile.shots) != -1) {
			mousePosX = e.getX();
			mousePosY = e.getY();
			panel.repaint();
			player.shoot(player.centerX, player.centerY, player.angle, playerProjectile.findNext(playerProjectile.shots));
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
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
