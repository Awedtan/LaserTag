package packy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class rotate {
	
	public static void rotatePlayer(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		body.playerCenterY = (body.playerHeight/2) + body.playerOffsetY;
		body.playerCenterX = (body.playerWidth/2) + body.playerOffsetX;
		
		body.panel.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        body.mouseX = (int) e.getX();
		        body.mouseY = (int) e.getY();
		    }
		});
		
		double angle = -(Math.atan2(body.playerCenterX - body.mouseX, body.playerCenterY - body.mouseY) - Math.PI / 2);
		
		g2.rotate(angle, body.playerCenterX, body.playerCenterY);
	}
}