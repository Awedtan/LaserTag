package packy;

import java.awt.*;

public class enemy {
	
	static final int MAX = 15;//# of enemies
	static final int STARTSPEED = 3;//Default enemy speed
	static final int VIEWRANGE = 500;//Length of enemy vision
	static final int SHOOTRANGE = 150;//When the distance to the player is smaller than this, enemies stop moving
	static final int FOV = 80;//Range of enemy vision, behaves like player fov
	
	static int width = 20;//Enemy dimensions
	static int height = width;
	static int speed = STARTSPEED;
	static int[] movement = new int[]{0, 45, 90, 135, 180, 225, 270, 315, 360};
	
	static int[] STARTPOSX = new int[] {100, 120, 300, 350, 200, 500, 1200, 1000, 1000, 1200, 1700, 1700, 950, 1000, 1800};//<- This needs to be initialized manually for now
	static int[] STARTPOSY = new int[] {100, 900, 400, 900, 120, 500, 350, 400, 900, 500, 900, 100, 200, 800, 900};//<- Ditto
	static int[] centerX = new int[MAX];
	static int [] centerY = new int[MAX];
	static int[] wait = new int[MAX];
	static int[] random = new int[MAX];
	static int[] lastX = new int[MAX];
	static int[] lastY = new int[MAX];
	static double[] angle = new double[MAX];
	static boolean[] alive = new boolean[MAX];
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
		g2.setColor(Color.BLACK);
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
		
		if(game.checkVisibleEnemy(enemies[enemy], player.model, VIEWRANGE, FOV, enemy)) {
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
				
				 random[enemy] = (int)(Math.random() * 10);
				 wait[enemy] = (int)(Math.random() * 480);
			}
			else 
				wait[enemy]--;
			
			switch(random[enemy]) {
				case 0:
					enemies[enemy].x -= speed;
					break;
				case 1:
					enemies[enemy].x -= speed;
					enemies[enemy].y -= speed/STARTSPEED;
					break;
				case 2:
					enemies[enemy].y -= speed;
					break;
				case 3:
					enemies[enemy].x += speed/STARTSPEED;
					enemies[enemy].y -= speed;
					break;
				case 4:
					enemies[enemy].x += speed;
					break;
				case 5:
					enemies[enemy].x += speed;
					enemies[enemy].y += speed/STARTSPEED;
					break;
				case 6:
					enemies[enemy].y += speed;
					break;
				case 7:
					enemies[enemy].x -= speed/STARTSPEED;
					enemies[enemy].y += speed;
					break;
				case 8:
				case 9:
				case 10:
					double angle = Math.toDegrees(-(Math.atan2(centerX[enemy] - lastX[enemy], centerY[enemy] - lastY[enemy]) - Math.PI / 2));
					
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
	}
	
	public static void rotate(Graphics g, int enemy) {
		//Rotates the enemy model towards the player model
		
		centerY[enemy] = (height/2) + enemies[enemy].y;
		centerX[enemy] = (width/2) + enemies[enemy].x;
		
		if(game.checkVisibleEnemy(enemies[enemy], player.model, VIEWRANGE, FOV, enemy))
			angle[enemy] = -(Math.atan2(centerX[enemy] - player.centerX, centerY[enemy] - player.centerY) - Math.PI / 2);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle[enemy], centerX[enemy], centerY[enemy]);
	}
}
