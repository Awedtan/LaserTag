package packy;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

// TODO: add proper method comments (if we're supposed to) (this goes for every file)
// TODO: a lot of this code is duplicate from the body.java file so which to remove from????
// TODO: this code might be poopy, make sure to review

public class menu extends JPanel implements KeyListener, MouseListener {

    // List of necessary variables
    static int screenWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());// Screen dimensions TODO: duplicate? delete? send help
	static int screenHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    static JPanel menuPanel, playPanel, optionPanel;
    static ImageIcon gameLogo = new ImageIcon("images/logo_v2.png");
    static JLabel playLabel, optionLabel, exitLabel;

    // Main menu variables

    // Game menu variables
    
    public static void mainMenu() {

    }

    public static void startGame() {
            
    }

    public static void exitConfirm() {
        // TODO: using normal dialogs should be enough but idk
    }

    public menu () {
        setPreferredSize(new Dimension(screenWidth, screenHeight));

        setBackground(Color.DARK_GRAY);
        
    }

    public void paintComponent(Graphics g) {
        // Draws menu items
        super.paintComponent(g);
    }
    
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame();

        menuPanel = new JPanel();

        menuFrame.setUndecorated(true);
        menuFrame.add(menuPanel);
        menuFrame.pack();
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		menuFrame.setResizable(false);
		menuFrame.setVisible(true); 
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}