package packy;

import java.awt.*;

public class playerProjectile {

	static int width = 5;//Size of projectile
	static int height = 60;
	static int max = 100;//Max # of projectiles on screen
	static int inaccuracy = 5;//Inaccuracy of shots
	static Color color = Color.blue;
	static double speed = 30;//Speed of projectiles
	static int damage = 5;//Amount of damage that each player projectile does
	
	static boolean initialized;
	
	static Image image;
	
	static int[] posX = new int[max];//Projectile information
	static int[] posY = new int[max];
	static int[] countX = new int[max];
	static int[] countY = new int[max];
	static double[] moveX = new double[max];
	static double[] moveY = new double[max];
	static double[] angle = new double[max];
	static boolean[] alive = new boolean[max];
	static Rectangle[] shots = new Rectangle[max];//The projectiles
	
	public static int findNext(Rectangle[] shots) {
		//Finds the next inactive/dead slot in the projectile array
		
		for(int i=0; i<shots.length; i++) 
			if(!alive[i])
				return i;
		
		return -1;
	}
	
	public static void kill(int shot) {
		
		playerProjectile.alive[shot] = false;
		posX[shot] = -20;
		posY[shot] = -20;
		countX[shot] = -20;
		countY[shot] = -20;
		moveX[shot] = 0;
		moveY[shot] = 0;
	}
	
	public static void move(Graphics g, int shot) {
		//Moves the specified projectile
		
			Graphics2D g2 = (Graphics2D) g;
			shots[shot] = new Rectangle(posX[shot] + countX[shot], posY[shot] + countY[shot], width, height);

			g2.setColor(color);
			g2.fill(shots[shot]);
			g2.drawImage(image, posX[shot] + countX[shot] - width/2, posY[shot] + countY[shot], null);

			countX[shot] += moveX[shot];
			countY[shot] += moveY[shot];
	}
	
	public static void rotate(Graphics g, int shot) {

		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(angle[shot]-Math.toRadians(90), posX[shot] + countX[shot], posY[shot] + countY[shot]);
	}
}
