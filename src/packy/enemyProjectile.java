package packy;

import java.awt.*;

public class enemyProjectile {

	static int size = 5;//Size of projectile
	static int max = 100;//Max # of projectiles on screen
	static int inaccuracy = 5;//Inaccuracy of shots
	
	static double speed = 20;//Speed of projectiles
	
	static boolean initialized;
	
	static Rectangle[] shots = new Rectangle[max];//The projectiles
	
	static int[] posX = new int[max];//Projectile information
	static int[] posY = new int[max];
	static int[] countX = new int[max];
	static int[] countY = new int[max];
	static double[] moveX = new double[max];
	static double[] moveY = new double[max];
	static double[] angle = new double[max];
	static boolean[] alive = new boolean[max];
	
	public static int findNext(Rectangle[] shots) {
		//Finds the next inactive/dead slot in the projectile array
		
		for(int i=0; i<shots.length; i++) 
			if(!alive[i])
				return i;
		
		return -1;
	}
	
	public static void kill(int shot) {
		
		enemyProjectile.alive[shot] = false;
		enemyProjectile.countX[shot] = -10;
		enemyProjectile.countY[shot] = -10;
		enemyProjectile.moveX[shot] = -10;
		enemyProjectile.moveY[shot] = -10;
	}
	
	public static void move(Graphics g, int shot) {
		//Moves the specified projectile
		
		if(!alive[shot]) {
			
			posX[shot] = -10;
			posY[shot] = -10;
		}
		
			Graphics2D g2 = (Graphics2D) g;
			shots[shot] = new Rectangle(posX[shot] + countX[shot], posY[shot] + countY[shot], enemyProjectile.size, enemyProjectile.size);
			
			g2.setColor(Color.BLACK);
			g2.fill(shots[shot]);
			countX[shot] += moveX[shot];
			countY[shot] += moveY[shot];
	}
}
