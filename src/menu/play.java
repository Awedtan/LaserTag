package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class play extends JPanel implements KeyListener, MouseListener{
    JLabel titleLabel, enemiesLabel, mapLabel, mutatorsLabel, startLabel;
    
    static int numberEnemies;
    static String mapName;

    public play() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
        setLayout(null);
        
        titleLabel = new JLabel("Play");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 100));
        titleLabel.setBounds(50, 50, 250, 150);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(menu.menuPanel, menu.playPanel, menu.STATE.MAIN);
            }
        });

        add(titleLabel);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, menu.screenWidth, menu.screenHeight);   
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