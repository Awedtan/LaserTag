package packy;

import java.awt.*;
import java.awt.event.*;

public class player {

	static int width = 20;
	static int height = width;
	static int startPosX = 300;
	static int startPosY = 300;
	static int speed = 3;
	static int centerX;
	static int centerY;
	
	static Rectangle model = new Rectangle(startPosX, startPosY, width, height);
	
	static boolean moveLeft;
	static boolean moveRight;
	static boolean moveUp;
	static boolean moveDown;
	
	public static void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(player.model.x, player.model.y, player.width, player.height);
		g2.fillRect((player.model.x-(int)(player.width-player.width*0.2)), player.model.y, player.height, (int)(player.width/5));
	}
	
	public static void move() {
		
		if(moveLeft)
			
			model.x -= speed;
		else if(moveRight)
			
			model.x += speed;
		
		if(moveUp)
			
			model.y -= speed;
		else if(moveDown)
			
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
		});
		
		double angle = -(Math.atan2(centerX - body.mouseX, centerY - body.mouseY) - Math.PI / 2);
		
		g2.rotate(angle, centerX, centerY);
	}
	
	public static void shoot(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(body.xPew, body.yPew, 10, 10);
	}
}