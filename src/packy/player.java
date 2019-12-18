package packy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class player {

	public static void move() {
		
		if(body.left)
			
			body.playerOffsetX -= body.playerSpeed;
		else if(body.right)
			
			body.playerOffsetX += body.playerSpeed;
		
		if(body.up)
			body
			.playerOffsetY -= body.playerSpeed;
		else if(body.down)
			
			body.playerOffsetY += body.playerSpeed;
	}
	
	public static void rotate(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		body.playerCenterY = (body.playerHeight/2) + body.playerOffsetY;
		body.playerCenterX = (body.playerWidth/2) + body.playerOffsetX;
		
		body.panel.addMouseMotionListener(new MouseAdapter() {
		    public void mouseMoved(MouseEvent e) {
		        body.mouseX = (int) e.getX();
		        body.mouseY = (int) e.getY();
		    }
		});
		
		double angle = -(Math.atan2(body.playerCenterX - body.mouseX, body.playerCenterY - body.mouseY) - Math.PI / 2);
		
		g2.rotate(angle, body.playerCenterX, body.playerCenterY);
	}
	
	public static void shoot(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(body.xPew, body.yPew, 10, 10);
	}
}