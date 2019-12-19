package packy;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class body extends JPanel implements KeyListener, MouseListener, Runnable{
	
	Thread mainThread;
	
	static int screenWidth = 1920;
	static int screenHeight = 1080;
	//The screen dimensions may need to be adjusted for different screens
	static int fps = 60;
	static body panel = new body();
	static JFrame frame;
	
	static int mouseX;
	static int mouseY;
	
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
		
		for(int i=0; i<game.map.length; i++) {
			for(int j=0; j<game.map[i].length; j++) {
				
				if(game.map[i][j] == 1)
					game.numWalls++;
			}
		}
		game.walls = new Rectangle[game.numWalls];
	}
	
	public void update() {
		
		player.move();
		game.checkInBound();
		
		if(game.wallsExist && projectile.exists)
			for(int i=0; i<game.walls.length; i++) {
				
				game.checkProjectileCollision(game.walls[i]);
				game.checkPlayerCollision(game.walls[i]);
			}
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
		game.drawMap(g);
		projectile.move(g);
		projectile.exists = true;
		
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
		
		projectile.mousePosX = e.getX();
		projectile.mousePosY = e.getY();
		panel.repaint();
			
		projectile.shoot(player.centerX, player.centerY,player.angle);
		projectile.alive = true;
//		projectile.counter = 0;
		projectile.projCountX = 0;
		projectile.projCountY = 0;
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
