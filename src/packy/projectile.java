package packy;

import java.awt.*;

public class projectile {

	static int size = 5;
	static int mousePosX;
	static int mousePosY;
	static int projSpeed = 4;
	static int projPosX;
	static int projPosY;
	static double projAngle;
	static int counter = 0;
	
	static boolean exists;
	static boolean alive;
	
	static Rectangle[] shots = new Rectangle[1];
	
	public static void shoot(int startX, int startY, double angle){
		
		projAngle = angle;
		projPosX = startX;
		projPosY = startY;
	}
	
	public static void move(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		shots[0] = new Rectangle(projPosX+counter, projPosY, projectile.size, projectile.size);
		
		if(alive)
			g2.fill(shots[0]);
		counter += projSpeed;
	}
}