package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class main extends JPanel implements KeyListener, MouseListener {
    JLabel playLabel, exitLabel;

    public main() {
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
                menu.frame.removeAll();
                
                repaint();
            }
        });

        add(playLabel);
        add(exitLabel);
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