package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import packy.*;

@SuppressWarnings("serial")
public class main extends JPanel implements MouseListener {
    JLabel playLabel, exitLabel;

    static Image gameLogo = Toolkit.getDefaultToolkit().getImage("images/logo_resized.png");

    public main() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
        setFocusable(true);
        setLayout(null);

        playLabel = new JLabel("Play");
        playLabel.setFont(menu.menuFont);
        playLabel.setBounds(50, 450, 300, 120);
        playLabel.setForeground(Color.WHITE);
        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(body.menuPanel, body.playPanel);
            }
        });
        exitLabel = new JLabel("Exit");
        exitLabel.setFont(menu.menuFont);
        exitLabel.setBounds(50, 700, 250, 120);
        exitLabel.setForeground(Color.WHITE);
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(body.menuPanel, body.exitPanel);
            }
        });

        add(playLabel);
        add(exitLabel);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(menu.backgroundColor);
		g.fillRect(0, 0, menu.screenWidth, menu.screenHeight);

        g.drawImage(gameLogo, 50, 50, (2 * menu.screenWidth/3 - 50), ((2 * menu.screenWidth/3 - 50) / 6), this);
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
}