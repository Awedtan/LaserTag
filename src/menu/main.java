package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class main extends JPanel implements KeyListener, MouseListener {
    JLabel playLabel, exitLabel;

    public main() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
		setLayout(null);
        playLabel = new JLabel("Play");
        playLabel.setFont(menu.menuFont);
        playLabel.setBounds(50, 450, 250, 120);
        playLabel.setForeground(Color.WHITE);
        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: open play menu
            }
        });
        exitLabel = new JLabel("Exit");
        exitLabel.setFont(menu.menuFont);
        exitLabel.setBounds(50, 700, 250, 120);
        exitLabel.setForeground(Color.WHITE);
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.menuState = menu.STATE.EXIT;
                menu.frame.add(menu.exitPanel);
                menu.frame.remove(menu.menuPanel);
                menu.frame.validate();
                menu.frame.pack();
                repaint();
            }
        });

        add(playLabel);
        add(exitLabel);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, menu.screenWidth, menu.screenHeight);

        g.drawImage(menu.gameLogo, 50, 100, (2 * menu.screenWidth/3 - 50), ((2 * menu.screenWidth/3 - 50) / 6), this);
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