package packy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

public class rotate {

	static int playerCenterX;
	static int playerCenterY;
	
	public static void rotatePlayer(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		playerCenterX = body.playerWidth/2 + body.playerOffsetX;
		playerCenterY = body.playerHeight/2 + body.playerOffsetY;
		
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		int x = (int) b.getX();
		int y = (int) b.getY();
		System.out.println(x);
		System.out.println(y);
		
		//TODO: The rotation code does not work properly yet, the commented line is another version that also doesn't work
//		double angle = -(Math.atan2(playerCenterY - x, playerCenterX - y) - Math.PI / 2);
		
		double first = (x - playerCenterX) * Math.sin(90);
		double second = Math.sqrt(Math.pow(x - playerCenterX, 2) + (Math.pow(y - playerCenterY, 2)));
		
		double angle = Math.asin(first/second);
		
		if(y > playerCenterY)
			angle = -angle;
		
		g2.rotate(angle, playerCenterX, playerCenterY);
	}
	
	public static void main(String[] args) {
		
		
	}
}
