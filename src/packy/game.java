package packy;

import java.awt.*;
import java.io.*;
import java.util.*;

public class game {
	
	static final Color CWALL = Color.black;
	static final Color CVISIBLE = Color.lightGray;
	static final Color CINVISIBLE = Color.gray;
	
	public static MODE mode = MODE.DM;
	
	public enum MODE {
		
		DM,
		SURVIVAL,
		SPREE
	}
	
	public static int timeLimit = 60;
	public static int killLimit = 15;
	public static boolean ended = true;
	
	public static File file = new File("maps/arena");
	static Scanner input;
	
	static char[][] map;//The dimensions should be common divisors of 1920 and 1080
						//96x54 is a good map size, any larger and it might become laggy
	
	static int numCols;//Map dimensions
	static int numRows;
	static int tileWidth;//Tile dimensions
	static int tileHeight;
	
	static Color cWall = CWALL;
	static Color cVisible = CVISIBLE;
	static Color cInvisible = CINVISIBLE;
	
	static boolean wallsInitialized;
	static int numWalls = 0;
	static Rectangle[] walls;
	
	static boolean tilesInitialized;
	static boolean[] tileIsVisible;
	static int numTiles = 0;
	static Rectangle[] tiles;
	
	public static void reset() {
		
		 timeLimit = 60;
		 killLimit = 15;
		 ended = true;
		
		 file = new File("maps/arena");
		
		 numCols = 0;
		 numRows = 0;
		 tileWidth = 0;
		 tileHeight = 0;
		
		 cWall = CWALL;
		 cVisible = CVISIBLE;
		 cInvisible = CINVISIBLE;
		
		 wallsInitialized = false;
		 walls = new Rectangle[0];
		 numWalls = 0;
		
		 tilesInitialized = false;
		 tiles = new Rectangle[0];
		 numTiles = 0;
	}
	
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
			g2.setColor(cWall);
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
		
		for(int i=0; i<tiles.length; i++) 
			if(tileIsVisible[i]) {
				
				g2.setColor(cVisible);
				g2.fill(tiles[i]);
			}
	}
	
	public static void drawInvisible(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i=0; i<tiles.length; i++) {
			if(!tileIsVisible[i]) {
				g2.setColor(cInvisible);
				g2.fill(tiles[i]);
			}
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
