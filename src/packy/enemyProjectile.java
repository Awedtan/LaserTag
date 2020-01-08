package packy;

import java.awt.*;

public class enemyProjectile {

	static final int SIZE = 5;//Size of projectile
	static final int MAX = 100;//Max # of projectiles on screen
	static final int INACCURACY = 5;//Inaccuracy of shots
	static Color color = Color.red;
	static double speed = 15;//Speed of projectiles
	
	static boolean initialized;
	
	static Rectangle[] shots = new Rectangle[MAX];//The projectiles
	
	static int[] posX = new int[MAX];//Projectile information
	static int[] posY = new int[MAX];
	static int[] countX = new int[MAX];
	static int[] countY = new int[MAX];
	static int[] tick = new int[MAX];
	static double[] moveX = new double[MAX];
	static double[] moveY = new double[MAX];
	static double[] angle = new double[MAX];
	static boolean[] alive = new boolean[MAX];
	
	public static int findNext(Rectangle[] shots) {
		//Finds the next inactive/dead slot in the projectile array
		
		for(int i=0; i<shots.length; i++) 
			if(!alive[i])
				return i;
		
		return -1;
	}
	
	public static void kill(int shot) {
		
		alive[shot] = false;
		countX[shot] = -10;
		countY[shot] = -10;
		moveX[shot] = -10;
		moveY[shot] = -10;
	}
	
	public static void move(Graphics g, int shot) {
		//Moves the specified projectile
		
		if(!alive[shot]) {
			
			posX[shot] = -10;
			posY[shot] = -10;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		shots[shot] = new Rectangle(posX[shot] + countX[shot], posY[shot] + countY[shot], SIZE, SIZE);
		
		g2.setColor(color);
		g2.fill(shots[shot]);
		countX[shot] += moveX[shot];
		countY[shot] += moveY[shot];
	}
}
