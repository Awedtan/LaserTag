package packy;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class game {
	
	static File file = new File("map1.txt");
	static Scanner input;
	
	static char[][] map;//For square tiles, the # of columns should be 1.78 times the number of rows
						//The dimensions should also be divisors of both 1920 and 1080
						//96x54 is a good map size, any larger and it might become laggy
	
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
	
	public static boolean checkVisible(Rectangle model, Rectangle tile, int range) {
		//Checks whether a line can be drawn between two rectangles without intercepting any walls
		
		double x1 = (model.width/2) + model.x, x2 = tile.x + tile.getWidth()/2, y1 = (model.height/2) + model.y, y2 = tile.y + tile.getHeight()/2;
				
			if(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) < range) {
				
				Line2D view = new Line2D.Double();
				view.setLine(x1, y1, x2, y2);
				
					for(int i=0; i<walls.length; i++) 
						if(view.intersects(walls[i])) 
							return false;
					
				return true;
			}
		return false;
	}
	
	public static boolean checkVisible(Rectangle model, Rectangle tile, int range, int fov) {
		
		double x1 = (model.width/2) + model.x, x2 = tile.x + tile.getWidth()/2, y1 = (model.height/2) + model.y, y2 = tile.y + tile.getHeight()/2;
		double angleOfObject = -(Math.atan2(player.centerX - tile.x + tile.getWidth()/2, player.centerY - tile.y + tile.getHeight()/2) - Math.PI / 2);
				
		if(player.angle - (player.angle - angleOfObject) > player.angle - Math.toRadians(fov) && player.angle - (player.angle - angleOfObject) < player.angle + Math.toRadians(fov)) {
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
		
		if(playerProjectile.shots[shot].intersects(wall)) {
			
			playerProjectile.alive[shot] = false;
			playerProjectile.countX[shot] = 0;
			playerProjectile.countY[shot] = 0;
			playerProjectile.moveX[shot] = 0;
			playerProjectile.moveY[shot] = 0;
		}
		
		if(playerProjectile.shots[shot].intersects(enemy.dummy)) {
			
			playerProjectile.alive[shot] = false;
			playerProjectile.countX[shot] = 0;
			playerProjectile.countY[shot] = 0;
			playerProjectile.moveX[shot] = 0;
			playerProjectile.moveY[shot] = 0;
		}
	}
	
	public static void checkEnemyProjectileCollision(Rectangle wall, int shot) {
		//Checks for projectile collisions with walls
		
		if(enemyProjectile.shots[shot].intersects(wall)) {
			
			enemyProjectile.alive[shot] = false;
			enemyProjectile.countX[shot] = 0;
			enemyProjectile.countY[shot] = 0;
			enemyProjectile.moveX[shot] = 0;
			enemyProjectile.moveY[shot] = 0;
		}
		
		if(enemyProjectile.shots[shot].intersects(player.model)) {
			
			enemyProjectile.alive[shot] = false;
			enemyProjectile.countX[shot] = 0;
			enemyProjectile.countY[shot] = 0;
			enemyProjectile.moveX[shot] = 0;
			enemyProjectile.moveY[shot] = 0;
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
	
//	TODO: This isn't really necessary if all the maps are walled off
//	public static void checkInBound() {
//		
//		if(player.model.x < 0)
//			player.model.x = 0;
//		
//		else if(player.model.x > body.screenWidth - player.model.width)
//			player.model.x = body.screenWidth - player.model.width;
//		
//		if(player.model.y < 0)
//			player.model.y = 0;
//		
//		else if(player.model.y > body.screenHeight - player.model.height)
//			player.model.y = body.screenHeight - player.model.height;
//	}
}
