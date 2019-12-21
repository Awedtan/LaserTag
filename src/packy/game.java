package packy;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

public class game {
	
	static File file = new File("map2.txt");
	static Scanner input;
	
	static char[][] map = new char[30][40];
	
	static int numRows = map.length;//Map dimensions
	static int numCols = map[0].length;
	static int tileWidth = body.screenWidth/numCols;//Tile dimensions
	static int tileHeight = body.screenHeight/numRows;
	
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
		
		for(int i=0; i<walls.length; i++) 
			g2.fill(walls[i]);
		
		
	}
	
	public static void drawMap(Graphics g) {
		//Draws all other tiles
		
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
				g2.setColor(Color.BLACK);
			}
		}
	}
	
	public static boolean checkVisible(Rectangle model, Rectangle tile) {
		//Checks whether the center of the selected model has a direct line of vision with the center of the selected tile
		
		Line2D view = new Line2D.Double();
		view.setLine((model.width/2) + model.x, (model.height/2) + model.y, tile.x + tile.getWidth()/2, tile.y + tile.getHeight()/2);
		
		for(int i=0; i<walls.length; i++) 
			if(view.intersects(walls[i])) 
				return false;
		
		return true;
	}
	
	public static void checkProjectileCollision(Rectangle wall, int shot) {
		//Checks for projectile collisions with walls
		
		if(projectile.shots[shot].intersects(wall)) {
		
			projectile.alive[shot] = false;
			projectile.countX[shot] = 0;
			projectile.countY[shot] = 0;
			projectile.moveX[shot] = 0;
			projectile.moveY[shot] = 0;
		}
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
