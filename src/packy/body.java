package packy;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

import menu.*;

@SuppressWarnings({ "static-access", "serial" })
public class body extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{
	//Daniel Su, Zhenyang Cai, 2D top-down shooter game
	
	Thread mainThread;
	
	static int mousePosX;//Mouse cursor coordinates
	static int mousePosY;
	// Sets screenWidth and screenHeight to the dimensions of the screen
	public static int screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());//Screen dimensions
	public static int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	static int fps = 60;
	
	public static body panel = new body();
	public static JFrame frame;

	// Menu elements
	public static main menuPanel;
	public static play playPanel;
	public static exit exitPanel;
	public static modifiers modifiersPanel;

	// ------------ HUD Elements ------------ //
	static JLabel scoreLabel, timeLabel;
	public static int startTime;
	static int elapsedTime;
	static int respawnDelay = 5;
	static int respawnCount;

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
			playerProjectile.image = Toolkit.getDefaultToolkit().getImage("images\\bluelaser_v2.png");
			enemyProjectile.image = Toolkit.getDefaultToolkit().getImage("images\\redlaser.png");
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
				if(line.charAt(i) != ' ')
					game.map[mapRow][(i+1)/2] = line.charAt(i);
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
		
    	game.started = false;
	}
	
	public void update() {
		//Updates object locations, checks for collisions, visibility, lots of stuff
		
		if(!game.ended)
			elapsedTime = (int) ((new Date()).getTime() - startTime)/1000;
		timeLabel.setText("Time: " + elapsedTime);
		
		if(game.mode == game.mode.DM && elapsedTime >= game.timeLimit) {
			
			game.ended = true;
			player.kill();
		}
		else if(game.mode == game.mode.SPREE && player.score >= game.killLimit) {
			
			game.ended = true;
			player.kill();
		}

		if(game.wallsInitialized && playerProjectile.initialized && enemyProjectile.initialized && game.tilesInitialized) {
			
			player.move();
			
			for(int k=0; k<enemy.MAX; k++) 
				if(enemy.alive[k])
					enemy.move(k);
			
			for(int i=0; i<game.walls.length; i++) {
				
				player.checkCollision(game.walls[i]);
				
				for(int k=0; k<enemy.MAX; k++) 
					if(enemy.alive[k]) {

						enemy.checkCollision(game.walls[i], k);

						for(int l=0; l<enemy.MAX; l++) 
							if(k != l)
								enemy.checkCollision(enemy.enemies[l], k);
					}
				
				for(int j=0; j<playerProjectile.shots.length; j++) {
					if(playerProjectile.alive[j])
						playerProjectile.checkWallCollision(game.walls[i], j);
				
					for(int k=0; k<enemy.MAX; k++) 
						if(enemy.alive[k])
							playerProjectile.checkEnemyCollision(k, j);
				}
														
				for(int j=0; j<enemyProjectile.shots.length; j++) 
					if(enemyProjectile.alive[j])
						enemyProjectile.checkCollision(game.walls[i], j);
			}
			
			for(int k=0; k<enemy.MAX; k++) {
				for(int l=0; l<enemy.MAX; l++)
					if(k != l && enemy.alive[k])
						enemy.checkCollision(enemy.enemies[k], l);
				
				if(enemy.alive[k])
					player.checkCollision(enemy.enemies[k]);
			}
			
			for(int i=0; i<playerProjectile.shots.length; i++) 
				if(!game.checkInBound(playerProjectile.shots[i])) 
					playerProjectile.kill(i);
			
			for(int i=0; i<enemyProjectile.shots.length; i++) 
				if(!game.checkInBound(enemyProjectile.shots[i])) 
					enemyProjectile.kill(i);
			
			for(int i=0; i<game.numTiles; i++) 
				if(player.FOVENABLED)
					if(player.checkVisible(player.model, game.tiles[i], player.VIEWRANGE, player.FOV))
						game.tileIsVisible[i] = true;
					else
						game.tileIsVisible[i] = false;
				else
					game.tileIsVisible[i] = true;
			
			for(int k=0; k<enemy.MAX; k++) 
				if(enemy.checkVisible(enemy.enemies[k], player.model, enemy.VIEWRANGE, enemy.FOV, k) && enemyProjectile.findNext(enemyProjectile.shots) != -1 && enemy.alive[k]) 
					enemy.shoot(enemy.centerX[k], enemy.centerY[k], enemy.angle[k], enemyProjectile.findNext(enemyProjectile.shots), k);
			
		}
	}
	
	public body() {
		//Sets up the game window and other methods
		setLayout(null);
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(new Color(200, 200, 200));
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(new KeyListener() {
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

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		mainThread = new Thread(this);
		mainThread.start();

		// Adds a label in the top left containing the current score
		scoreLabel = new JLabel("Score: " + Integer.toString(player.score), SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        scoreLabel.setBounds(10, 10, 250, 50);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setOpaque(true);
		scoreLabel.setBackground(new Color(128, 128, 128, 192));
		scoreLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		
		add(scoreLabel);

		timeLabel = new JLabel("Time: " + elapsedTime, SwingConstants.CENTER);
		timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		timeLabel.setBounds(screenWidth-260, 10, 250, 50);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setOpaque(true);
		timeLabel.setBackground(new Color(128, 128, 128, 192));
		timeLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		
		add(timeLabel);
	}
	
	public void paintComponent(Graphics g) {
		//Draws all graphics
		
		super.paintComponent(g);
		game.drawVisible(g);
		
		for(int i=0; i<playerProjectile.shots.length; i++) {
			Graphics gLaser = g.create();
			playerProjectile.rotate(gLaser,  i);
			playerProjectile.move(gLaser, i);
			gLaser.dispose();
		}



		for(int i=0; i<enemyProjectile.shots.length; i++) {

			Graphics gLaser = g.create();
			enemyProjectile.rotate(gLaser,  i);
			enemyProjectile.move(gLaser, i);
			gLaser.dispose();
		}
		
		playerProjectile.initialized = true;
		enemyProjectile.initialized = true;
		
		game.drawWalls(g);
		
		for(int i=0; i<enemy.MAX; i++) {
			if(enemy.alive[i]) {
				
				Graphics gEnemy = g.create();
				enemy.rotate(gEnemy, i);
				enemy.draw(gEnemy, i);
				gEnemy.dispose();
			}
		}
		
		game.drawInvisible(g);
		
		Graphics gPlayer = g.create();
		player.rotate(gPlayer);
		player.draw(gPlayer);
		gPlayer.dispose();
		
		game.drawWalls(g);
		
		if(player.alive) {
			
			g.setColor(Color.black);
			g.drawString(Integer.toString(player.health), player.model.x, player.model.y - player.model.height);
		}
		else if(!game.ended) {
			// Draws a game over message with a background and border when the player dies in this mode TODO: make this more efficient
			
			g.setFont(new Font("Segoe UI", Font.PLAIN, 30));
			FontMetrics fm = g.getFontMetrics();
			String deathMessage;
			respawnCount++;
			
			if(respawnCount/60 >= respawnDelay) {
				
				player.canRespawn = true;
				deathMessage = "Click anywhere on the map to respawn...";	
			}
			else {
				
				player.canRespawn = false;
				deathMessage = "You died! Respawning in " + (respawnDelay - respawnCount/60) + " seconds...";
			}
			
			Rectangle2D textRect = fm.getStringBounds(deathMessage, g);
			g.setColor(Color.white);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2 - 5, 
				screenHeight / 2 - fm.getAscent() / 2 - 5, 
				(int) textRect.getWidth() + 10, 
				(int) textRect.getHeight() + 10
			);
			g.setColor(Color.BLACK);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2, 
				screenHeight / 2 - fm.getAscent() / 2, 
				(int) textRect.getWidth(), 
				(int) textRect.getHeight()	
			);
			g.setColor(Color.white);
			g.drawString(deathMessage, screenWidth / 2 - (int) textRect.getWidth() / 2, screenHeight / 2 + fm.getAscent() / 2);
		}
		else if(game.mode == game.mode.SURVIVAL){
			// Draws a game over message with a background and border when the player dies in this mode
			
			g.setFont(new Font("Segoe UI", Font.PLAIN, 30));
			FontMetrics fm = g.getFontMetrics();
			String permaDeathMessage = "GAME OVER! You survived for " + elapsedTime + " seconds and defeated " + player.score + " enemies before dying...";
			Rectangle2D textRect = fm.getStringBounds(permaDeathMessage, g);
			g.setColor(Color.white);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2 - 5, 
				screenHeight / 2 - fm.getAscent() / 2 - 5, 
				(int) textRect.getWidth() + 10, 
				(int) textRect.getHeight() + 10
			);
			g.setColor(Color.BLACK);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2, 
				screenHeight / 2 - fm.getAscent() / 2, 
				(int) textRect.getWidth(), 
				(int) textRect.getHeight()	
			);
			g.setColor(Color.white);
			g.drawString(permaDeathMessage, screenWidth / 2 - (int) textRect.getWidth() / 2, screenHeight / 2 + fm.getAscent() / 2);
			
			// TODO: Figure out how to draw "play again" and "exit" boxes with mouseListeners efficiently
			
		}
		else if(game.mode == game.mode.DM) {
			
			// Draws a game over message with a background and border when the player dies in this mode
			
			g.setFont(new Font("Segoe UI", Font.PLAIN, 30));
			FontMetrics fm = g.getFontMetrics();
			String permaDeathMessage = "GAME OVER! You defeated " + player.score + " enemies before time ran out...";
			Rectangle2D textRect = fm.getStringBounds(permaDeathMessage, g);
			g.setColor(Color.white);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2 - 5, 
				screenHeight / 2 - fm.getAscent() / 2 - 5, 
				(int) textRect.getWidth() + 10, 
				(int) textRect.getHeight() + 10
			);
			g.setColor(Color.BLACK);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2, 
				screenHeight / 2 - fm.getAscent() / 2, 
				(int) textRect.getWidth(), 
				(int) textRect.getHeight()	
			);
			g.setColor(Color.white);
			g.drawString(permaDeathMessage, screenWidth / 2 - (int) textRect.getWidth() / 2, screenHeight / 2 + fm.getAscent() / 2);
			
			// TODO: Figure out how to draw "play again" and "exit" boxes with mouseListeners efficiently
		}
		else if(game.mode == game.mode.SPREE) {
			
			// Draws a game over message with a background and border when the player dies in this mode
			
			g.setFont(new Font("Segoe UI", Font.PLAIN, 30));
			FontMetrics fm = g.getFontMetrics();
			String permaDeathMessage = "GAME OVER! You defeated " + game.killLimit + " enemies in " + elapsedTime + " seconds...";
			Rectangle2D textRect = fm.getStringBounds(permaDeathMessage, g);
			g.setColor(Color.white);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2 - 5, 
				screenHeight / 2 - fm.getAscent() / 2 - 5, 
				(int) textRect.getWidth() + 10, 
				(int) textRect.getHeight() + 10
			);
			g.setColor(Color.BLACK);
			
			g.fillRect(
				screenWidth / 2 - (int) textRect.getWidth() / 2, 
				screenHeight / 2 - fm.getAscent() / 2, 
				(int) textRect.getWidth(), 
				(int) textRect.getHeight()	
			);
			g.setColor(Color.white);
			g.drawString(permaDeathMessage, screenWidth / 2 - (int) textRect.getWidth() / 2, screenHeight / 2 + fm.getAscent() / 2);
			
			// TODO: Figure out how to draw "play again" and "exit" boxes with mouseListeners efficiently
		}

				
		
		game.cWall = game.CWALL;
		player.color = Color.blue;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Shoots a projectile, if possible, on click
		
		mousePosX = e.getX();
		mousePosY = e.getY();
		
		if(player.alive && playerProjectile.findNext(playerProjectile.shots) != -1) {
			
			panel.repaint();
			player.shoot(player.centerX, player.centerY, player.angle, playerProjectile.findNext(playerProjectile.shots));
		}
		else if(!player.alive && !game.ended && player.canRespawn) 
			player.respawn(mousePosX, mousePosY);
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
		
		frame = new JFrame("Lazer Tag");
		menuPanel = new main();
		playPanel = new play();
		exitPanel = new exit();
		modifiersPanel = new modifiers();
		frame.setUndecorated(true);
		frame.add(menuPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
