package packy;

import java.awt.*;

public class enemy {
	
	static final int STARTSPEED = 3;//Default player speed
	static final int STARTPOSX = 600;//Player start location
	static final int STARTPOSY = 550;	
	static final int VIEWRANGE = 200;
	static final int FOV = 90;
	
	static int width = 20;//Dummy dimensions
	static int height = width;
	
	static int centerX;//Dummy coordinates
	static int centerY;
	static int speed = STARTSPEED;
	static double angle;

	static Rectangle dummy = new Rectangle(enemy.STARTPOSX, enemy.STARTPOSY, enemy.width, enemy.height);
	
	public static void draw(Graphics g) {
		//Draws the dummy sprite
		
		centerY = (height/2) + dummy.y;
		centerX = (width/2) + dummy.x;
		
		angle = -(Math.atan2(centerX - player.centerX, centerY - player.centerY) - Math.PI / 2);
		
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
}
