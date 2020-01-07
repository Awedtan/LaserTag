package packy;

import java.awt.*;

public class enemy {
	
	static final int STARTSPEED = 2;//Default enemy speed
	static final int STARTPOSX = 600;//Enemy start location
	static final int STARTPOSY = 550;	
	static final int VIEWRANGE = 400;
	static final int FOV = 90;
	
	static int width = 20;//Dummy dimensions
	static int height = width;
	
	static int centerX;//Dummy coordinates
	static int centerY;
	static int speed = STARTSPEED;
	static double angle;
	static int[] movement = new int[]{0, 45, 90, 135, 180, 225, 270, 315, 360};
	
	static Rectangle dummy = new Rectangle(enemy.STARTPOSX, enemy.STARTPOSY, enemy.width, enemy.height);
	
	public static void draw(Graphics g) {
		//Draws the dummy sprite
		
		centerY = (height/2) + dummy.y;
		centerX = (width/2) + dummy.x;

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fill(dummy);
	}
	
	public static void shoot(int startX, int startY, double angle, int shot){
		//Initializes a projectile at the specified coordinates
		
		enemyProjectile.angle[shot] = angle;
		enemyProjectile.posX[shot] = startX;
		enemyProjectile.posY[shot] = startY;
		
		enemyProjectile.moveX[shot] = -(enemyProjectile.speed * Math.cos(Math.toRadians(Math.toDegrees(angle) + Math.random()*enemyProjectile.inaccuracy-enemyProjectile.inaccuracy/2)));
		enemyProjectile.moveY[shot] = -(enemyProjectile.speed * Math.sin(Math.toRadians(Math.toDegrees(angle) + Math.random()*enemyProjectile.inaccuracy-enemyProjectile.inaccuracy/2)));
		
		enemyProjectile.countX[shot] = 0;
		enemyProjectile.countY[shot] = 0;
		enemyProjectile.alive[shot] = true;
	}
	
	public static void move(Graphics g) {
		//Moves the enemy model
		
		if(game.checkVisible(enemy.dummy, player.model, enemy.VIEWRANGE, 360)) {
			if(Math.sqrt((enemy.dummy.x-player.model.x)*(enemy.dummy.x-player.model.x) + (enemy.dummy.y-player.model.y)*(enemy.dummy.y-player.model.y)) > 200) {
				
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
	}
	
	public static void rotate(Graphics g) {
		//Rotates the enemy model towards the player model
		
		centerY = (height/2) + dummy.y;
		centerX = (width/2) + dummy.x;
		
		if(game.checkVisible(enemy.dummy, player.model, enemy.VIEWRANGE, 360))
			angle = -(Math.atan2(centerX - player.centerX, centerY - player.centerY) - Math.PI / 2);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle, centerX, centerY);
	}
}
