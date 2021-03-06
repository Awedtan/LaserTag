package packy;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class player {

	static int STARTSPEED = 4;//Default player speed
	static int STARTPOSX = 100;//Player start location
	static int STARTPOSY = 550;	
	static int HEALTHMAX = 50;
	
	public static boolean FOVENABLED;
	public static int VIEWRANGE = 500;
	public static int FOV = 50;//In degrees, this value is half the FOV, therefore 45 = 90 FOV, 90 = 180 FOV
	public static int health = HEALTHMAX;// Player health

	static int width = 20;//Player dimensions
	static int height = width;
	static Color color = Color.blue;
	
	static int centerX;//Player coordinates
	static int centerY;
	static int speed = STARTSPEED;
	static double angle;//Angle to mouse cursor
	static int score = 0;// Player score
	
	static Rectangle model = new Rectangle(STARTPOSX, STARTPOSY, width, height);//Player model
	
	static boolean moveLeft;//Movement booleans
	static boolean moveRight;
	static boolean moveUp;
	static boolean moveDown;
	static boolean sprint;
	public static boolean alive = true;
	static boolean canRespawn;
	
	public static void reset() {

		score = 0;
		moveLeft = false;
		moveRight = false;
		moveUp = false;
		moveDown = false;
		sprint = false;
		canRespawn = true;
		alive = true;
	}
	
	public static void damage() {
		health -= enemyProjectile.DAMAGE;

		// handle player deaths in terms of scoreboard and respawning
		if (health <= 0) {

		}
	}

	public static void draw(Graphics g) {
		//Draws the player and gun sprite
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(model);
		g2.fillRect((model.x-(int)(width-width*0.2)), model.y, height, (int)(width/5));
	}
	
	public static void hit() {
		
		color = Color.red;
		game.cWall = Color.red;
		health -= enemyProjectile.DAMAGE;
		
		if(health <= 0) 
			kill();
	}
	
	public static void kill() {
				
		model.x = -30;
		model.y = -30;
		alive = false;
		
		if(game.mode == game.MODE.SURVIVAL)
			game.ended = true;
	}
	
	public static void respawn(int x, int y) {
		
		body.respawnCount = 0;
		model.x = x;
		model.y = y;
		health = HEALTHMAX;
		moveUp = false;
		moveDown = false;
		moveLeft = false;
		moveRight = false;
		alive = true;
	}
	
	public static void shoot(int startX, int startY, double angle, int shot){
		//Initializes a projectile at the specified coordinates
		
		playerProjectile.angle[shot] = angle;
		playerProjectile.posX[shot] = startX;
		playerProjectile.posY[shot] = startY;
		
		playerProjectile.moveX[shot] = -(playerProjectile.SPEED * Math.cos(Math.toRadians(Math.toDegrees(angle) + Math.random()*playerProjectile.INACCURACY-playerProjectile.INACCURACY/2)));
		playerProjectile.moveY[shot] = -(playerProjectile.SPEED * Math.sin(Math.toRadians(Math.toDegrees(angle) + Math.random()*playerProjectile.INACCURACY-playerProjectile.INACCURACY/2)));
		
		playerProjectile.countX[shot] = 0;
		playerProjectile.countY[shot] = 0;
		playerProjectile.alive[shot] = true;
	}
	
	public static void move() {
		//Moves the player model based on keys pressed
	
		if(sprint)
			speed = (int)(STARTSPEED*1.5);
		else 
			speed = STARTSPEED;
		
		if((moveLeft || moveRight) && (moveUp || moveDown))
			speed /= 1.2;
		
		if(moveLeft && !moveRight) 
				model.x -= speed;
		else if(moveRight && !moveLeft) 
				model.x += speed;
		
		if(moveUp && !moveDown) 
				model.y -= speed;
		else if(moveDown && !moveUp) 
				model.y += speed;
	}
	
	public static void rotate(Graphics g) {
		//Rotates the player model towards the mouse cursor
		
		centerY = (height/2) + model.y;
		centerX = (width/2) + model.x;
		
		body.panel.addMouseMotionListener(new MouseAdapter() {
			
		    public void mouseMoved(MouseEvent e) {
		    	
		        body.mousePosX = (int) e.getX();
		        body.mousePosY = (int) e.getY();
		    }
		    public void mouseDragged(MouseEvent e) {
		    	
		    	body.mousePosX = (int) e.getX();
		        body.mousePosY = (int) e.getY();
		    }
		});
		
		angle = -(Math.atan2(centerX - body.mousePosX, centerY - body.mousePosY) - Math.PI / 2);
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle, centerX, centerY);
	}
	
	public static boolean checkVisible(Rectangle model, Rectangle tile, double range, double fov) {
		//Checks whether a line can be drawn between the centre of two rectangles without intercepting any walls
		
		double x1 = (model.width/2) + model.x, x2 = tile.x + tile.getWidth()/2, y1 = (model.height/2) + model.y, y2 = tile.y + tile.getHeight()/2;
		double angleOfObject = -(Math.atan2(centerX - tile.x + tile.getWidth()/2, centerY - tile.y + tile.getHeight()/2) - Math.PI / 2);
				
		if(
			(
			angle-(angle-angleOfObject) > angle-Math.toRadians(fov) 
			||
			angle+(angle-(Math.toRadians(360 - 2 * FOV)+angleOfObject)) > angle+Math.toRadians(fov) 
			) && (
			angle-(angle-angleOfObject) < angle+Math.toRadians(fov) 
			||
			Math.toRadians(-90 - (270 - Math.toDegrees(angleOfObject))) > angle-Math.toRadians(fov)
			)
		) {
			if(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) < range) {
				
				Line2D view = new Line2D.Double();
				view.setLine(x1, y1, x2, y2);
				
					for(int i=0; i<game.walls.length; i++) 
						if(view.intersects(game.walls[i])) 
							return false;
				return true;
			}
		}
		return false;
	}
	
	public static void checkCollision(Rectangle rect) {
		//Checks for player collision with rectangles

		if(model.intersects(rect)) {

			double left1 = model.getX();
			double right1 = model.getX() + model.getWidth();
			double top1 = model.getY();
			double bottom1 = model.getY() + model.getHeight();
			double left2 = rect.getX();
			double right2 = rect.getX() + rect.getWidth();
			double top2 = rect.getY();
			double bottom2 = rect.getY() + rect.getHeight();
			
			if(right1 > left2 && left1 < left2 && right1 - left2 < bottom1 - top2 && right1 - left2 < bottom2 - top1) 
				model.x = rect.x - model.width;
	        
	        else if(left1 < right2 && right1 > right2 && right2 - left1 < bottom1 - top2 && right2 - left1 < bottom2 - top1) 
	        	model.x = rect.x + rect.width;
	        
	        else if(bottom1 > top2 && top1 < top2) 
	        	model.y = rect.y - model.height;
	        
	        else if(top1 < bottom2 && bottom1 > bottom2) 
	        	model.y = rect.y + rect.height;
		}
	}
}