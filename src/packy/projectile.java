package packy;

import java.awt.*;

public class projectile {

	static int size = 5;//Size of projectile
	static int shotMax = 50;//Max # of projectiles on screen
	static int mousePosX;//X coord of mouse
	static int mousePosY;//Y coord of mouse
	static int shotInaccuracy = 10;
	
	static double shotSpeed = 11;//Speed of projectiles
	
	static boolean initialized;
	
	static Rectangle[] shots = new Rectangle[shotMax];
	
	static int[] shotPosX = new int[shotMax];
	static int[] shotPosY = new int[shotMax];
	static int[] shotCountX = new int[shotMax];
	static int[] shotCountY = new int[shotMax];
	static double[] shotMovX = new double[shotMax];
	static double[] shotMovY = new double[shotMax];
	static double[] shotAngle = new double[shotMax];
	static boolean[] shotAlive = new boolean[shotMax];
	
	public static int findNext(Rectangle[] shots) {
		
		for(int i=0; i<shots.length; i++) {
			if(!shotAlive[i])
				return i;
		}
		return -1;
	}
	
	public static void shoot(int startX, int startY, double angle, int shot){
		
		shotAngle[shot] = angle;
		shotPosX[shot] = startX;
		shotPosY[shot] = startY;
		
//		Note: this code will be slightly off since it's rounding
		shotMovX[shot] = -(shotSpeed * Math.cos(Math.toRadians(Math.toDegrees(angle) + Math.random()*shotInaccuracy-shotInaccuracy/2)));
		shotMovY[shot] = -(shotSpeed * Math.sin(Math.toRadians(Math.toDegrees(angle) + Math.random()*shotInaccuracy-shotInaccuracy/2)));
	}
	
	public static void move(Graphics g, int shot) {
		
		if(!shotAlive[shot]) {
			
			shotPosX[shot] = 0;
			shotPosY[shot] = 0;
		}
		
			Graphics2D g2 = (Graphics2D) g;
			shots[shot] = new Rectangle(shotPosX[shot] + shotCountX[shot], shotPosY[shot] + shotCountY[shot], projectile.size, projectile.size);
			
			g2.fill(shots[shot]);
			shotCountX[shot] += shotMovX[shot];
			shotCountY[shot] += shotMovY[shot];
	}
}