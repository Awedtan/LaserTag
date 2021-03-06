package menu;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import packy.*;

@SuppressWarnings("serial")
public class menu extends JPanel{
	//the necessary variables
	static int screenWidth = body.screenWidth;
    static int screenHeight = body.screenHeight;
	
	static Font menuFont = new Font("Segoe UI", Font.PLAIN, 80);
	static Color backgroundColor = new Color(20, 20, 20);
	static Border defaultBorder = BorderFactory.createLineBorder(Color.WHITE, 5);

	static JFrame frame;

	/**
	 * Removes the specified panel and replaces it with a new one 
	 * @param panelRemove The panel to be removed (current panel)
	 * @param panelAdd The panel to be added
	 */
	public static void switchStatePanel(Component panelRemove, Component panelAdd) {
		body.frame.add(panelAdd);
		body.frame.remove(panelRemove);
		body.frame.revalidate();
		body.frame.pack();
		body.frame.repaint();
	}
}