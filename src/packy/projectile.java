package packy;

import java.awt.*;

public class projectile {

	static int size = 5;
	static int mousePosX;
	static int mousePosY;
	static int projSpeed = 4;
	static int projPosX;
	static int projPosY;
	static int projMovX;
	static int projMovY;
	static int projCountX = 0;
	static int projCountY = 0;
	static double projAngle;
	static int counter = 0;
	
	static boolean exists;
	static boolean alive;
	
	static Rectangle[] shots = new Rectangle[1];
	
	public static void shoot(int startX, int startY, double angle){
		projAngle = angle;
		projPosX = startX;
		projPosY = startY;
		
//		Note: this code will be slightly off since it's truncating instead of rounding or leaving it as an int
		projMovX = (int) -Math.round(projSpeed * Math.cos(angle));
		projMovY = (int) -Math.round(projSpeed * Math.sin(angle));
		
//		if (mousePosY > startY) {
//			projMovX = -projMovX;
//			projMovY = -projMovY;
//		}
	}
	
	public static void move(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		shots[0] = new Rectangle(projPosX + projCountX, projPosY + projCountY, projectile.size, projectile.size);
		
		if(alive)
			g2.fill(shots[0]);
		projCountX += projMovX;
		projCountY += projMovY;
	}
}