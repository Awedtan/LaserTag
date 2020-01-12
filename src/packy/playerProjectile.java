package packy;

import java.awt.*;

public class playerProjectile {

	static int size = 5;//Size of projectile
	static int max = 100;//Max # of projectiles on screen
	static int inaccuracy = 5;//Inaccuracy of shots
	static Color color = Color.blue;
	static double speed = 15;//Speed of projectiles
	static int damage = 5;// amount of damage that each player projectile does
	
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
		
		playerProjectile.alive[shot] = false;
		playerProjectile.countX[shot] = -10;
		playerProjectile.countY[shot] = -10;
		playerProjectile.moveX[shot] = -10;
		playerProjectile.moveY[shot] = -10;
	}
	
	public static void move(Graphics g, int shot) {
		//Moves the specified projectile
		
		if(!alive[shot]) {
			
			posX[shot] = -10;
			posY[shot] = -10;
		}
		
			Graphics2D g2 = (Graphics2D) g;
			shots[shot] = new Rectangle(posX[shot] + countX[shot], posY[shot] + countY[shot], size, size);
			
			g2.setColor(color);
			g2.fill(shots[shot]);
			countX[shot] += moveX[shot];
			countY[shot] += moveY[shot];
	}
}
