package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class exit extends JPanel implements KeyListener, MouseListener{
    JLabel confirmAsk, confirmYes, confirmNo;

    public exit() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
		setLayout(null);
        confirmAsk = new JLabel("Are you sure you want to exit?", SwingConstants.CENTER);
        confirmAsk.setFont(new Font("Segoe UI", Font.PLAIN, 70));
        confirmAsk.setBounds(menu.screenWidth / 4, 350, menu.screenWidth / 2, 120);
        confirmAsk.setForeground(Color.WHITE);

        confirmYes = new JLabel("Yes", SwingConstants.CENTER);
        confirmYes.setFont(menu.menuFont);
        confirmYes.setBounds(menu.screenWidth / 4 + 150, 600, 150, 120);
        confirmYes.setForeground(Color.WHITE);
        confirmYes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        confirmNo = new JLabel("No", SwingConstants.CENTER);
        confirmNo.setFont(menu.menuFont);
        confirmNo.setBounds(3 * menu.screenWidth / 4 - 300, 600, 150, 120);
        confirmNo.setForeground(Color.WHITE);
        confirmNo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(menu.exitPanel, menu.menuPanel);
            }
        });

        add(confirmAsk);
        add(confirmYes);
        add(confirmNo);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(menu.backgroundColor);
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