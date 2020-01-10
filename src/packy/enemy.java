package packy;

import java.awt.*;
import java.awt.geom.Line2D;

public class enemy {
	
	static final int MAX = 1;//# of enemies
	static final int STARTSPEED = 3;//Default enemy speed
	static final int VIEWRANGE = 500;//Length of enemy vision
	static final int SHOOTRANGE = 150;//When the distance to the player is smaller than this, enemies stop moving
	static final int FOV = 90;//Range of enemy vision, behaves like player fov
	
	static int width = 20;//Enemy dimensions
	static int height = width;
	static int speed = STARTSPEED;
	static Color color = Color.red;
	
	static int[] movement = new int[]{0, 45, 90, 135, 180, 225, 270, 315, 360};
	
//	static int[] STARTPOSX = new int[] {100, 120, 300, 350, 200, 500, 1200, 1000, 1000, 1200, 1700, 1700, 950, 1000, 1800};//<- This needs to be initialized manually for now
//	static int[] STARTPOSY = new int[] {100, 900, 400, 900, 120, 500, 350, 400, 900, 500, 900, 100, 200, 800, 900};//<- Ditto
	static int[] STARTPOSX = new int[] {1000};
	static int[] STARTPOSY = new int[] {600};
	static int[] centerX = new int[MAX];
	static int [] centerY = new int[MAX];
	static int[] wait = new int[MAX];
	static int[] angleWait = new int[MAX];
	static int[] direction = new int[MAX];
	static int[] random = new int[MAX];
	static int[] lastX = new int[MAX];
	static int[] lastY = new int[MAX];
	static double[] angle = new double[MAX];
	static boolean[] alive = new boolean[MAX];
	static boolean[] turnRight = new boolean[MAX];
	static Rectangle[] enemies = new Rectangle[MAX];
	
	public static void initialize(int enemy) {
		
		enemies[enemy] = new Rectangle(STARTPOSX[enemy], STARTPOSY[enemy], width, height);
		alive[enemy] = true;
	}
	
	public static void kill(int enemy) {
		
		alive[enemy] = false;
		centerX[enemy] = -100;
		centerY[enemy] = -100;
		lastX[enemy] = 0;
		lastY[enemy] = 0;
	}
	
	public static void draw(Graphics g, int enemy) {
		//Draws the enemies[enemy] sprite
		
		centerY[enemy] = (height/2) + enemies[enemy].y;
		centerX[enemy] = (height/2) + enemies[enemy].x;

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(enemies[enemy]);
		g2.fillRect((enemies[enemy].x-(int)(enemies[enemy].width-enemies[enemy].width*0.2)), enemies[enemy].y, enemies[enemy].height, (int)(enemies[enemy].width/5));
	}
	
	public static void shoot(int startX, int startY, double angle, int shot, int enemy){
		//Initializes a projectile at the specified coordinates
		
		if(enemyProjectile.tick[shot] == 30) {
			
			enemyProjectile.angle[shot] = angle;
			enemyProjectile.posX[shot] = startX;
			enemyProjectile.posY[shot] = startY;
			enemyProjectile.moveX[shot] = -(enemyProjectile.speed * Math.cos(Math.toRadians(Math.toDegrees(angle) + Math.random()*enemyProjectile.INACCURACY-enemyProjectile.INACCURACY/2)));
			enemyProjectile.moveY[shot] = -(enemyProjectile.speed * Math.sin(Math.toRadians(Math.toDegrees(angle) + Math.random()*enemyProjectile.INACCURACY-enemyProjectile.INACCURACY/2)));
			enemyProjectile.countX[shot] = 0;
			enemyProjectile.countY[shot] = 0;
			enemyProjectile.alive[shot] = true;
			enemyProjectile.tick[shot] = 0;
		}
		else 
			enemyProjectile.tick[shot]++;
		
		lastX[enemy] = player.model.x;
		lastY[enemy] = player.model.y;
	}
	
	public static void move(int enemy) {
		//Moves the enemy model
		
		if(checkVisible(enemies[enemy], player.model, VIEWRANGE, FOV, enemy)) {
			if(Math.sqrt((enemies[enemy].x-player.model.x)*(enemies[enemy].x-player.model.x) + (enemies[enemy].y-player.model.y)*(enemies[enemy].y-player.model.y)) > SHOOTRANGE) {
				
				double angle = Math.toDegrees(-(Math.atan2(centerX[enemy] - player.centerX, centerY[enemy] - player.centerY) - Math.PI / 2));
				
				if(angle < 0)
					angle = 270 + (90 + angle);
				
				double distance = Math.abs(movement[0] - angle);
				int count = 0;
				
				for(int c = 1; c < movement.length; c++){
					
					double cdistance = Math.abs(movement[c] - angle);
					
					if(cdistance < distance){
						
						count = c;
						distance = cdistance;
					}
				}
				
				int direction = movement[count];
				
				if(direction == 45 || direction == 135 || direction == 225 || direction == 315) 
					speed /= 1.2;
	
				switch (direction) {
				case 0:
					enemies[enemy].x -= speed;
					break;
				case 45:
					enemies[enemy].x -= speed;
					enemies[enemy].y -= speed/STARTSPEED;
				case 90: 
					enemies[enemy].y -= speed;
					break;
				case 135:
					enemies[enemy].x += speed/STARTSPEED;
					enemies[enemy].y -= speed;
				case 180:
					enemies[enemy].x += speed;
					break;
				case 225:
					enemies[enemy].x += speed;
					enemies[enemy].y += speed/STARTSPEED;
				case 270:
					enemies[enemy].y += speed;
					break;
				case 315:
					enemies[enemy].x -= speed/STARTSPEED;
					enemies[enemy].y += speed;
				case 360:
					enemies[enemy].x -= speed;
				}
				
				speed = STARTSPEED;
			}
		}
		else {
			
			if(wait[enemy] < 0) {
				
				 random[enemy] = (int)(Math.random() * 12);
				 wait[enemy] = (int)(Math.random() * 200);
			}
			else 
				wait[enemy]--;
			
			switch(random[enemy]) {
			case 0:
				enemies[enemy].x -= speed;
				break;
			case 1:
				enemies[enemy].x -= speed/1.5;
				enemies[enemy].y -= speed/1.5;
				break;
			case 2:
				enemies[enemy].y -= speed;
				break;
			case 3:
				enemies[enemy].x += speed;
				enemies[enemy].y -= speed/1.5;
				break;
			case 4:
				enemies[enemy].x += speed;
				break;
			case 5:
				enemies[enemy].x += speed/1.2;
				enemies[enemy].y += speed/1.2;
				break;
			case 6:
				enemies[enemy].y += speed;
				break;
			case 7:
				enemies[enemy].x -= speed/1.5;
				enemies[enemy].y += speed/1.5;
				break;
			case 8:
			case 9:
			case 10:
			case 11:
				if(centerX[enemy] != lastX[enemy] && centerY[enemy] != lastY[enemy]) {
					
					double angle= 0;
					
					if(wait[enemy] == 1)
						angle = Math.toDegrees(-(Math.atan2(centerX[enemy] - lastX[enemy], centerY[enemy] - lastY[enemy]) - Math.PI / 2));
					
					if(angle < 0)
						angle = 270 + (90 + angle);
					
					double distance = Math.abs(movement[0] - angle);
					int count = 0;
					
					for(int c = 1; c < movement.length; c++){
						
						double cdistance = Math.abs(movement[c] - angle);
						
						if(cdistance < distance){
							
							count = c;
							distance = cdistance;
						}
					}
					
					direction[enemy] = movement[count];
					
					if(direction[enemy] == 45 || direction[enemy] == 135 || direction[enemy] == 225 || direction[enemy] == 315) 
						speed /= 1.2;
		
					switch (direction[enemy]) {
					case 0:
						enemies[enemy].x -= speed;
						break;
					case 45:
						enemies[enemy].x -= speed;
						enemies[enemy].y -= speed/STARTSPEED;
					case 90: 
						enemies[enemy].y -= speed;
						break;
					case 135:
						enemies[enemy].x += speed/STARTSPEED;
						enemies[enemy].y -= speed;
					case 180:
						enemies[enemy].x += speed;
						break;
					case 225:
						enemies[enemy].x += speed;
						enemies[enemy].y += speed/STARTSPEED;
					case 270:
						enemies[enemy].y += speed;
						break;
					case 315:
						enemies[enemy].x -= speed/STARTSPEED;
						enemies[enemy].y += speed;
					case 360:
						enemies[enemy].x -= speed;
					}
					
					speed = STARTSPEED;
				}
				else {
					
					wait[enemy] = -1;
				}
			}
		}
	}
	
	public static void rotate(Graphics g, int enemy) {
		//Rotates the enemy model towards the player model
		
		centerY[enemy] = (height/2) + enemies[enemy].y;
		centerX[enemy] = (width/2) + enemies[enemy].x;
		
		if(checkVisible(enemies[enemy], player.model, VIEWRANGE, FOV, enemy))
			angle[enemy] = -(Math.atan2(centerX[enemy] - player.centerX, centerY[enemy] - player.centerY) - Math.PI / 2);
		else {
			
			switch(random[enemy]) {
			case 0:
				angle[enemy] = 0;
				break;
			case 1:
				angle[enemy] = Math.toRadians(45);
				break;
			case 2:
				angle[enemy] = Math.toRadians(90);
				break;
			case 3:
				angle[enemy] = Math.toRadians(135);
				break;
			case 4:
				angle[enemy] = Math.toRadians(180);
				break;
			case 5:
				angle[enemy] = Math.toRadians(225);
				break;
			case 6:
				angle[enemy] = Math.toRadians(270);
				break;
			case 7:
				angle[enemy] = Math.toRadians(315);
				break;
			case 8:
			case 9:
			case 10:
			case 11:
				switch (direction[enemy]) {
				case 0:
					angle[enemy] = Math.toRadians(0);
					break;
				case 45:
					angle[enemy] = Math.toRadians(45);
				case 90: 
					angle[enemy] = Math.toRadians(90);
					break;
				case 135:
					angle[enemy] = Math.toRadians(135);
				case 180:
					angle[enemy] = Math.toRadians(180);
					break;
				case 225:
					angle[enemy] = Math.toRadians(225);
				case 270:
					angle[enemy] = Math.toRadians(270);
					break;
				case 315:
					angle[enemy] = Math.toRadians(315);
				case 360:
					angle[enemy] = Math.toRadians(0);
				}
				break;
			}
		}
		
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle[enemy], centerX[enemy], centerY[enemy]);
	}
	
	public static boolean checkVisible(Rectangle model, Rectangle tile, double range, double fov, int num) {
		//Checks whether a line can be drawn between the centre of two rectangles without intercepting any walls
		
		double x1 = (model.width/2) + model.x, x2 = tile.x + tile.getWidth()/2, y1 = (model.height/2) + model.y, y2 = tile.y + tile.getHeight()/2;
		double angleOfObject = -(Math.atan2(enemy.centerX[num] - tile.x + tile.getWidth()/2, enemy.centerY[num] - tile.y + tile.getHeight()/2) - Math.PI / 2);
				
		if(
			(
			enemy.angle[num]-(enemy.angle[num]-angleOfObject) > enemy.angle[num]-Math.toRadians(fov) 
			||
			enemy.angle[num]+(enemy.angle[num]-(Math.toRadians(360 - 2 * enemy.FOV)+angleOfObject)) > enemy.angle[num]+Math.toRadians(fov) 
			) && (
			enemy.angle[num]-(enemy.angle[num]-angleOfObject) < enemy.angle[num]+Math.toRadians(fov) 
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
	
	public static void checkCollision(Rectangle rect, int num) {
		//Checks for player collision with rectangles
		
		if(enemy.enemies[num].intersects(rect)) {
			
			enemy.wait[num] = -1;
			double left1 = enemy.enemies[num].getX();
			double right1 = enemy.enemies[num].getX() + enemy.enemies[num].getWidth();
			double top1 = enemy.enemies[num].getY();
			double bottom1 = enemy.enemies[num].getY() + enemy.enemies[num].getHeight();
			double left2 = rect.getX();
			double right2 = rect.getX() + rect.getWidth();
			double top2 = rect.getY();
			double bottom2 = rect.getY() + rect.getHeight();
			
			if(right1 > left2 && left1 < left2 && right1 - left2 < bottom1 - top2 && right1 - left2 < bottom2 - top1) 
				enemy.enemies[num].x = rect.x - enemy.enemies[num].width;
	        
	        else if(left1 < right2 && right1 > right2 && right2 - left1 < bottom1 - top2 && right2 - left1 < bottom2 - top1) 
	        	enemy.enemies[num].x = rect.x + rect.width;
	        
	        else if(bottom1 > top2 && top1 < top2) 
	        	enemy.enemies[num].y = rect.y - enemy.enemies[num].height;
	        
	        else if(top1 < bottom2 && bottom1 > bottom2) 
	        	enemy.enemies[num].y = rect.y + rect.height;
		}
	}
	
	public static void checkProjectileCollision(Rectangle wall, int shot) {
		//Checks for projectile collisions with walls
		
		if(enemyProjectile.shots[shot].intersects(wall)) 
			enemyProjectile.kill(shot);
		
		if(enemyProjectile.shots[shot].intersects(player.model)) {
			
			enemyProjectile.kill(shot);
			test();
		}
	}
	
	public static void test() {
		
		player.color = Color.red;
		game.cWall = Color.red;
	}
}