package packy;

import java.awt.*;

public class projectile {

	static int size = 5;//Size of projectile
	static int max = 100;//Max # of projectiles on screen
	static int inaccuracy = 5;//Inaccuracy of shots
	
	static double speed = 30;//Speed of projectiles
	
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
	
	public static void shoot(int startX, int startY, double angle, int shot){
		//Creates a projectile at the specified coordinates
		
		projectile.angle[shot] = angle;
		posX[shot] = startX;
		posY[shot] = startY;
		
		moveX[shot] = -(speed * Math.cos(Math.toRadians(Math.toDegrees(angle) + Math.random()*inaccuracy-inaccuracy/2)));
		moveY[shot] = -(speed * Math.sin(Math.toRadians(Math.toDegrees(angle) + Math.random()*inaccuracy-inaccuracy/2)));
	}
	
	public static void move(Graphics g, int shot) {
		//Moves the specified projectile
		
		if(!alive[shot]) {
			
			posX[shot] = 0;
			posY[shot] = 0;
		}
		
			Graphics2D g2 = (Graphics2D) g;
			shots[shot] = new Rectangle(posX[shot] + countX[shot], posY[shot] + countY[shot], projectile.size, projectile.size);
			
			g2.setColor(Color.BLACK);
			g2.fill(shots[shot]);
			countX[shot] += moveX[shot];
			countY[shot] += moveY[shot];
	}
}
