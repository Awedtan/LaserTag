package packy;

import java.awt.*;
import java.awt.event.*;

public class player {

	static final int STARTSPEED = 4;//Default player speed
	
	static int width = 20;//Player dimensions
	static int height = width;
	static int startPosX = 100;//Player start location
	static int startPosY = 550;	
	static int centerX;//Player coordinates
	static int centerY;
	static int speed = STARTSPEED;
	static double angle;//Angle to mouse cursor
	
	static Rectangle model = new Rectangle(startPosX, startPosY, width, height);//Player model
	
	static boolean moveLeft;//Movement booleans
	static boolean moveRight;
	static boolean moveUp;
	static boolean moveDown;
	static boolean sprint;
	
	public static void draw(Graphics g) {
		//Draws the player and gun sprite
		
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(model);
		g2.fillRect((player.model.x-(int)(player.width-player.width*0.2)), player.model.y, player.height, (int)(player.width/5));
	}
	
	public static void move() {
		//Moves the player model based on keys pressed
	
		if(sprint)
			speed = (int)(STARTSPEED*2);
<<<<<<< HEAD
		else 
=======
		else {
>>>>>>> master
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
		
		Graphics2D g2 = (Graphics2D) g;
		
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
		g2.rotate(angle, centerX, centerY);
	}
}