package packy;

import java.awt.*;

public class enemy {
	
	static final int STARTSPEED = 2;//Default enemy speed
	static final int STARTPOSX = 600;//Enemy start location
	static final int STARTPOSY = 550;	
	static final int VIEWRANGE = 450;
	static final int FOV = 100;
	
	static int width = 20;//Dummy dimensions
	static int height = width;
	static int centerX;//Dummy coordinates
	static int centerY;
	static int speed = STARTSPEED;
	static int wait;
	static int random;
	static double angle;
	static int[] movement = new int[]{0, 45, 90, 135, 180, 225, 270, 315, 360};
	
	static Rectangle dummy = new Rectangle(STARTPOSX, STARTPOSY, width, height);
	
	public static void draw(Graphics g) {
		//Draws the dummy sprite
		
		centerY = (height/2) + dummy.y;
		centerX = (width/2) + dummy.x;

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fill(dummy);
		g2.fillRect((dummy.x-(int)(dummy.width-dummy.width*0.2)), dummy.y, dummy.height, (int)(dummy.width/5));
	}
	
	public static void shoot(int startX, int startY, double angle, int shot){
		//Initializes a projectile at the specified coordinates
		
		if(enemyProjectile.tick[shot] == 50) {
			
			enemyProjectile.angle[shot] = angle;
			enemyProjectile.posX[shot] = startX;
			enemyProjectile.posY[shot] = startY;
			enemyProjectile.moveX[shot] = -(enemyProjectile.speed * Math.cos(Math.toRadians(Math.toDegrees(angle) + Math.random()*enemyProjectile.inaccuracy-enemyProjectile.inaccuracy/2)));
			enemyProjectile.moveY[shot] = -(enemyProjectile.speed * Math.sin(Math.toRadians(Math.toDegrees(angle) + Math.random()*enemyProjectile.inaccuracy-enemyProjectile.inaccuracy/2)));
			enemyProjectile.countX[shot] = 0;
			enemyProjectile.countY[shot] = 0;
			enemyProjectile.alive[shot] = true;
			enemyProjectile.tick[shot] = 0;
		}
		else 
			enemyProjectile.tick[shot]++;
	}
	
	public static void move() {
		//Moves the enemy model
		
		if(game.checkVisibleEnemy(dummy, player.model, VIEWRANGE, FOV)) {
			if(Math.sqrt((dummy.x-player.model.x)*(dummy.x-player.model.x) + (dummy.y-player.model.y)*(dummy.y-player.model.y)) > 200) {
				
				double angle = Math.toDegrees(-(Math.atan2(centerX - player.centerX, centerY - player.centerY) - Math.PI / 2));
				
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
						dummy.x -= speed;
						break;
					case 45:
						dummy.x -= speed;
						dummy.y -= speed/STARTSPEED;
					case 90: 
						dummy.y -= speed;
						break;
					case 135:
						dummy.x += speed/STARTSPEED;
						dummy.y -= speed;
					case 180:
						dummy.x += speed;
						break;
					case 225:
						dummy.x += speed;
						dummy.y += speed/STARTSPEED;
					case 270:
						dummy.y += speed;
						break;
					case 315:
						dummy.x -= speed/STARTSPEED;
						dummy.y += speed;
					case 360:
						dummy.x -= speed;
				}
				
				speed = STARTSPEED;
			}
		}
		else {
			
			if(wait < 0) {
				
				 random = (int)(Math.random() * 7);
				 wait = (int)(Math.random() * 50) + 10;
			}
			else 
				wait--;
			
			switch(random) {
				case 0:
					dummy.x -= speed;
					break;
				case 1:
					dummy.x -= speed;
					dummy.y -= speed/STARTSPEED;
					break;
				case 2:
					dummy.y -= speed;
					break;
				case 3:
					dummy.x += speed/STARTSPEED;
					dummy.y -= speed;
					break;
				case 4:
					dummy.x += speed;
					break;
				case 5:
					dummy.x += speed;
					dummy.y += speed/STARTSPEED;
					break;
				case 6:
					dummy.y += speed;
					break;
				case 7:
					dummy.x -= speed/STARTSPEED;
					dummy.y += speed;
					break;
			}
		}
	}
	
	public static void rotate(Graphics g) {
		//Rotates the enemy model towards the player model
		
		centerY = (height/2) + dummy.y;
		centerX = (width/2) + dummy.x;
		
		if(game.checkVisibleEnemy(dummy, player.model, VIEWRANGE, FOV))
			angle = -(Math.atan2(centerX - player.centerX, centerY - player.centerY) - Math.PI / 2);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle, centerX, centerY);
	}
	
	public static void alerted(int x, int y) {
		
		centerY = (height/2) + dummy.y;
		centerX = (width/2) + dummy.x;
		
		if(game.checkVisibleEnemy(dummy, player.model, VIEWRANGE, FOV))
			angle = -(Math.atan2(centerX - x, centerY - y) - Math.PI / 2);
	}
}
