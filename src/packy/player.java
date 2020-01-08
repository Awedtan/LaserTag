package packy;

import java.awt.*;
import java.awt.event.*;

public class player {

	static final int STARTSPEED = 4;//Default player speed
	static final int STARTPOSX = 100;//Player start location
	static final int STARTPOSY = 550;	
	static final int VIEWRANGE = 500;
	static final int FOV = 50;//In degrees, this value is half the FOV, therefore 45 = 90 FOV, 90 = 180 FOV
	
	static int width = 20;//Player dimensions
	static int height = width;
	
	static int centerX;//Player coordinates
	static int centerY;
	static int speed = STARTSPEED;
	static double angle;//Angle to mouse cursor
	
	static Rectangle model = new Rectangle(STARTPOSX, STARTPOSY, width, height);//Player model
	
	static boolean moveLeft;//Movement booleans
	static boolean moveRight;
	static boolean moveUp;
	static boolean moveDown;
	static boolean sprint;
	
	public static void draw(Graphics g) {
		//Draws the player and gun sprite
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fill(model);
		g2.fillRect((model.x-(int)(width-width*0.2)), model.y, height, (int)(width/5));
	}
	
	public static void shoot(int startX, int startY, double angle, int shot){
		//Initializes a projectile at the specified coordinates
		
		playerProjectile.angle[shot] = angle;
		playerProjectile.posX[shot] = startX;
		playerProjectile.posY[shot] = startY;
		
		playerProjectile.moveX[shot] = -(playerProjectile.speed * Math.cos(Math.toRadians(Math.toDegrees(angle) + Math.random()*playerProjectile.inaccuracy-playerProjectile.inaccuracy/2)));
		playerProjectile.moveY[shot] = -(playerProjectile.speed * Math.sin(Math.toRadians(Math.toDegrees(angle) + Math.random()*playerProjectile.inaccuracy-playerProjectile.inaccuracy/2)));
		
		playerProjectile.countX[shot] = 0;
		playerProjectile.countY[shot] = 0;
		playerProjectile.alive[shot] = true;
	}
	
	public static void move() {
		//Moves the player model based on keys pressed
	
		if(sprint)
			speed = (int)(STARTSPEED*1.5);
		else 
			speed = STARTSPEED;
		
		if((moveLeft || moveRight) && (moveUp || moveDown))
			speed /= 1.2;
		
		if(moveLeft && !moveRight) 
				model.x -= speed;
		else if(moveRight && !moveLeft) 
				model.x += speed;
		
		if(moveUp && !moveDown) 
				model.y -= speed;
		else if(moveDown && !moveUp) 
				model.y += speed;
	}
	
	public static void rotate(Graphics g) {
		//Rotates the player model towards the mouse cursor
		
		centerY = (height/2) + model.y;
		centerX = (width/2) + model.x;
		
		body.panel.addMouseMotionListener(new MouseAdapter() {
			
		    public void mouseMoved(MouseEvent e) {
		    	
		        body.mousePosX = (int) e.getX();
		        body.mousePosY = (int) e.getY();
		    }
		    public void mouseDragged(MouseEvent e) {
		    	
		    	body.mousePosX = (int) e.getX();
		        body.mousePosY = (int) e.getY();
		    }
		});
		
		angle = -(Math.atan2(centerX - body.mousePosX, centerY - body.mousePosY) - Math.PI / 2);
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle, centerX, centerY);
	}
}