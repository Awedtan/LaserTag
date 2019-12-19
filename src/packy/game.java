package packy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class game {

	static boolean wallsExist;
	
	static int[][] map = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
			{1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1},
			{1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1},
			{0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1},
			{1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1}
	};
	
	static int numRows = map[0].length;
	static int numCols = map.length;
	static int numWalls = 0;
	static int tileWidth = body.screenWidth/numRows;
	static int tileHeight = body.screenHeight/numCols;
	
	static Rectangle[] walls;
	
	public static void drawMap(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		int counter = 0;
		
		for(int row = 0; row < Math.min(numRows, numCols); row++) {
			for(int col = 0; col < Math.max(numRows, numCols); col++) {
				
				int x = col * tileWidth;
				int y = row * tileHeight;
				
				if(map[row][col] == 1) {
					
					walls[counter] = new Rectangle (x, y, tileWidth, tileHeight);
					counter++;
				}
			}
		}
		
		wallsExist = true;
		for(int i=0; i<walls.length; i++) 
			g2.fill(walls[i]);
	}
	
	public static void checkProjectileCollision(Rectangle wall) {
		
		if(projectile.shots[0].intersects(wall)) {
			
			projectile.alive = false;
			projectile.counter = 0;
		}
	}
	
	public static void checkPlayerCollision(Rectangle wall) {
		
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
	
	public static void checkInBound() {
		
		if(player.model.x < 0)
			player.model.x = 0;
		
		else if(player.model.x > body.screenWidth - player.model.width)
			player.model.x = body.screenWidth - player.model.width;
		
		if(player.model.y < 0)
			player.model.y = 0;
		
		else if(player.model.y > body.screenHeight - player.model.height)
			player.model.y = body.screenHeight - player.model.height;
	}
}
