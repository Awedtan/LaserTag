package packy;

import java.awt.*;
import java.awt.event.*;

public class player {

	static final int STARTSPEED = 3;
	
	static int width = 20;
	static int height = width;
	static int startPosX = 50;
	static int startPosY = 500;	
	static int centerX;
	static int centerY;
	static int speed = STARTSPEED;
	static double angle;
	
	static Rectangle model = new Rectangle(startPosX, startPosY, width, height);
	
	static boolean moveLeft;
	static boolean moveRight;
	static boolean moveUp;
	static boolean moveDown;
	static boolean sprint;
	
	public static void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(player.model.x, player.model.y, player.width, player.height);
		g2.fillRect((player.model.x-(int)(player.width-player.width*0.2)), player.model.y, player.height, (int)(player.width/5));
	}
	
	public static void move() {
	
		if(sprint)
			speed = (int)(2*STARTSPEED);
		else {
			speed = STARTSPEED;
		}
		
		if(moveLeft && !moveRight)
			if(moveUp || moveDown) 
				model.x -= speed/1.5;
				
			else 
				model.x -= speed;
		
		else if(moveRight && !moveLeft)
			if(moveUp || moveDown) 
				model.x += speed/1.5;
				
			else 
				model.x += speed;
		
		if(moveUp && !moveDown)
			if(moveLeft || moveRight) 
				model.y -= speed/1.5;
				
			else 
				model.y -= speed;
		
		else if(moveDown && !moveUp)
			if(moveLeft || moveRight) 
				model.y += speed/1.5;
				
			else 
				model.y += speed;
	}
	
	public static void rotate(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		centerY = (height/2) + model.y;
		centerX = (width/2) + model.x;
		
		body.panel.addMouseMotionListener(new MouseAdapter() {
			
		    public void mouseMoved(MouseEvent e) {
		    	
		        body.mouseX = (int) e.getX();
		        body.mouseY = (int) e.getY();
		    }
		    public void mouseDragged(MouseEvent e) {
		    	
		    	body.mouseX = (int) e.getX();
		        body.mouseY = (int) e.getY();
		    }
		});
		
		angle = -(Math.atan2(centerX - body.mouseX, centerY - body.mouseY) - Math.PI / 2);
		g2.rotate(angle, centerX, centerY);
	}
}