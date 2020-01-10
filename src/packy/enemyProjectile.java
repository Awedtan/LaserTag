package packy;

import java.awt.*;

public class enemyProjectile {

	static int size = 5;//size of projectile
	static int max = 100;//max # of projectiles on screen
	static int inaccuracy = 5;//innacuracy of shots
	static Color color = Color.red;
	static double speed = 20;//Speed of projectiles
	
	static boolean initialized;
	
	static Rectangle[] shots = new Rectangle[max];//The projectiles
	
	static int[] posX = new int[max];//Projectile information
	static int[] posY = new int[max];
	static int[] countX = new int[max];
	static int[] countY = new int[max];
	static int[] tick = new int[max];
	static double[] moveX = new double[max];
	static double[] moveY = new double[max];
	static double[] angle = new double[max];
	static boolean[] alive = new boolean[max];
	
	static Image image;
	
	public static int findNext(Rectangle[] shots) {
		//Finds the next inactive/dead slot in the projectile array
		
		for(int i=0; i<shots.length; i++) 
			if(!alive[i])
				return i;
		
		return -1;
	}
	
	public static void kill(int shot) {
		
		alive[shot] = false;
		countX[shot] = -1000;
		countY[shot] = -1000;
		moveX[shot] = 0;
		moveY[shot] = 0;
	}
	
	public static void move(Graphics g, int shot) {
		//Moves the specified projectile
		
		Graphics2D g2 = (Graphics2D) g;
		shots[shot] = new Rectangle(posX[shot] + countX[shot], posY[shot] + countY[shot], size, size);
		
		g2.setColor(game.cVisible);
		g2.fill(shots[shot]);
		g2.drawImage(image, posX[shot] + countX[shot], posY[shot] + countY[shot] - size/2, null);
		
		countX[shot] += moveX[shot];
		countY[shot] += moveY[shot];
	}
	
	public static void rotate(Graphics g, int shot) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(angle[shot]-Math.toRadians(90), posX[shot] + countX[shot], posY[shot] + countY[shot]);
	}
}
