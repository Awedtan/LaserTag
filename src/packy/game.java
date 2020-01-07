package packy;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class game {
	
	static File file = new File("maps/map1.txt");
	static Scanner input;
	
	static char[][] map;//For square tiles, the # of columns should be 1.78 times the number of rows
						//The dimensions should also be divisors of both 1920 and 1080
						//128x72 is a good map size, any larger and it might become laggy
	
	static int numCols;//Map dimensions
	static int numRows;
	static int tileWidth;//Tile dimensions
	static int tileHeight;
	
	static boolean wallsInitialized;
	static int numWalls = 0;
	static Rectangle[] walls;
	
	static boolean tilesInitialized;
	static boolean[] tileIsVisible;
	static int numTiles = 0;
	static Rectangle[] tiles;
	
	public static void drawWalls(Graphics g) {
		//Draws walls
		
		Graphics2D g2 = (Graphics2D) g;
		int wallCount = 0;
		
		for(int row = 0; row < Math.min(numRows, numCols); row++) {
			for(int col = 0; col < Math.max(numRows, numCols); col++) {
				
				int x = col * tileWidth;
				int y = row * tileHeight;
				
				if(map[row][col] == '#') {	
					
					walls[wallCount] = new Rectangle (x, y, tileWidth, tileHeight);
					wallCount++;
				}
				
			}
		}
		
		wallsInitialized = true;
		
		for(int i=0; i<walls.length; i++) {
			g2.setColor(Color.BLACK);
			g2.fill(walls[i]);
		}
		
		
	}
	
	public static void drawVisible(Graphics g) {
		//Draws visible tiles
		
		Graphics2D g2 = (Graphics2D) g;
		int tileCount = 0;
		
		for(int row = 0; row < Math.min(numRows, numCols); row++) {
			for(int col = 0; col < Math.max(numRows, numCols); col++) {
				
				int x = col * tileWidth;
				int y = row * tileHeight;
				
				if(map[row][col] == '`') {
					
					tiles[tileCount] = new Rectangle (x, y, tileWidth, tileHeight);
					tileCount++;
				}
			}
		}
		
		tilesInitialized = true;
		
		for(int i=0; i<tiles.length; i++) {
			if(tileIsVisible[i]) {
				g2.setColor(Color.WHITE);
				g2.fill(tiles[i]);
			}
		}
	}
	
	public static void drawInvisible(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i=0; i<tiles.length; i++) {
			if(!tileIsVisible[i]) {
				g2.setColor(Color.LIGHT_GRAY);
				g2.fill(tiles[i]);
			}
		}
	}
	
	public static boolean checkVisiblePlayer(Rectangle model, Rectangle tile, double range, double fov) {
		//Checks whether a line can be drawn between the centre of two rectangles without intercepting any walls
		
		double x1 = (model.width/2) + model.x, x2 = tile.x + tile.getWidth()/2, y1 = (model.height/2) + model.y, y2 = tile.y + tile.getHeight()/2;
		double angleOfObject = -(Math.atan2(player.centerX - tile.x + tile.getWidth()/2, player.centerY - tile.y + tile.getHeight()/2) - Math.PI / 2);
				
		if(
			(
			player.angle-(player.angle-angleOfObject) > player.angle-Math.toRadians(fov) 
			||
			player.angle+(player.angle-(Math.toRadians(360 - 2 * player.FOV)+angleOfObject)) > player.angle+Math.toRadians(fov) 
			) && (
			player.angle-(player.angle-angleOfObject) < player.angle+Math.toRadians(fov) 
			)
			
		) {
			if(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) < range) {
				
				Line2D view = new Line2D.Double();
				view.setLine(x1, y1, x2, y2);
				
					for(int i=0; i<walls.length; i++) 
						if(view.intersects(walls[i])) 
							return false;
					
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkVisibleEnemy(Rectangle model, Rectangle tile, double range, double fov) {
		//Checks whether a line can be drawn between the centre of two rectangles without intercepting any walls
		
		double x1 = (model.width/2) + model.x, x2 = tile.x + tile.getWidth()/2, y1 = (model.height/2) + model.y, y2 = tile.y + tile.getHeight()/2;
		double angleOfObject = -(Math.atan2(enemy.centerX - tile.x + tile.getWidth()/2, enemy.centerY - tile.y + tile.getHeight()/2) - Math.PI / 2);
				
		if(
			(
			enemy.angle-(enemy.angle-angleOfObject) > enemy.angle-Math.toRadians(fov) 
			||
			enemy.angle+(enemy.angle-(Math.toRadians(360 - 2 * enemy.FOV)+angleOfObject)) > enemy.angle+Math.toRadians(fov) 
			) && (
			enemy.angle-(enemy.angle-angleOfObject) < enemy.angle+Math.toRadians(fov) 
			)
			
		) {
			if(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) < range) {
				
				Line2D view = new Line2D.Double();
				view.setLine(x1, y1, x2, y2);
				
					for(int i=0; i<walls.length; i++) 
						if(view.intersects(walls[i])) 
							return false;
					
				return true;
			}
		}
		return false;
	}
	
	public static void checkPlayerProjectileCollision(Rectangle wall, int shot) {
		//Checks for projectile collisions with walls
		
		if(playerProjectile.shots[shot].intersects(wall)) 
			playerProjectile.kill(shot);
		
		if(playerProjectile.shots[shot].intersects(enemy.dummy)) {
			
			playerProjectile.kill(shot);
			enemy.alerted(player.model.x, player.model.y);
		}
	}
	
	public static void checkEnemyProjectileCollision(Rectangle wall, int shot) {
		//Checks for projectile collisions with walls
		
		if(enemyProjectile.shots[shot].intersects(wall)) 
			enemyProjectile.kill(shot);
		
		if(enemyProjectile.shots[shot].intersects(player.model)) {
			
			enemyProjectile.kill(shot);
//			test();
		}
	}
	
	public static void test() {
		JOptionPane.showMessageDialog (body.frame, "your dead", "dead", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void checkPlayerCollision(Rectangle wall) {
		//Checks for player collision with walls
		
		if(player.model.intersects(wall)) {
			
			double left1 = player.model.getX();
			double right1 = player.model.getX() + player.model.getWidth();
			double top1 = player.model.getY();
			double bottom1 = player.model.getY() + player.model.getHeight();
			double left2 = wall.getX();
			double right2 = wall.getX() + wall.getWidth();
			double top2 = wall.getY();
			double bottom2 = wall.getY() + wall.getHeight();
			
			if(right1 > left2 && left1 < left2 && right1 - left2 < bottom1 - top2 && right1 - left2 < bottom2 - top1) 
				player.model.x = wall.x - player.model.width;
	        
	        else if(left1 < right2 && right1 > right2 && right2 - left1 < bottom1 - top2 && right2 - left1 < bottom2 - top1) 
	        	player.model.x = wall.x + wall.width;
	        
	        else if(bottom1 > top2 && top1 < top2) 
	        	player.model.y = wall.y - player.model.height;
	        
	        else if(top1 < bottom2 && bottom1 > bottom2) 
	        	player.model.y = wall.y + wall.height;
		}
	}
	
	public static void checkEnemyCollision(Rectangle wall) {
		//Checks for player collision with walls
		
		if(enemy.dummy.intersects(wall)) {
			
			double left1 = enemy.dummy.getX();
			double right1 = enemy.dummy.getX() + enemy.dummy.getWidth();
			double top1 = enemy.dummy.getY();
			double bottom1 = enemy.dummy.getY() + enemy.dummy.getHeight();
			double left2 = wall.getX();
			double right2 = wall.getX() + wall.getWidth();
			double top2 = wall.getY();
			double bottom2 = wall.getY() + wall.getHeight();
			
			if(right1 > left2 && left1 < left2 && right1 - left2 < bottom1 - top2 && right1 - left2 < bottom2 - top1) 
				enemy.dummy.x = wall.x - enemy.dummy.width;
	        
	        else if(left1 < right2 && right1 > right2 && right2 - left1 < bottom1 - top2 && right2 - left1 < bottom2 - top1) 
	        	enemy.dummy.x = wall.x + wall.width;
	        
	        else if(bottom1 > top2 && top1 < top2) 
	        	enemy.dummy.y = wall.y - enemy.dummy.height;
	        
	        else if(top1 < bottom2 && bottom1 > bottom2) 
	        	enemy.dummy.y = wall.y + wall.height;
		}
	}
	
	public static boolean checkInBound(Rectangle rect) {
		
		if(rect.x < -100)
			return false;
		else if(rect.x > body.screenWidth - rect.width + 100)
			return false;
		
		if(rect.y < -100)
			return false;
		else if(rect.y > body.screenHeight - rect.height + 100)
			return false;
		
		return true;
	}
}
