package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class modifiers extends JPanel implements KeyListener, MouseListener {
    JLabel resetLabel, backLabel, saveLabel; // TODO: add load previous options?

    // All modifier labels
    JLabel bulletSpeedLabel, playerFovLabel, playerDamageLabel, playerHealthLabel, enemyFOVLabel, enemyDamageLabel, enemyHealthLabe, viewDistanceLabel;

    Image headerImage = Toolkit.getDefaultToolkit().getImage("images/header_modifiers.png");

    Font modifierFont = new Font("Segoe UI", Font.PLAIN, 50);

    public modifiers() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
        setLayout(null);

        // ------------ Menu operation labels ------------ //
        resetLabel = new JLabel("Reset to defaults", SwingConstants.CENTER);
        resetLabel.setFont(play.optionFont);
        resetLabel.setBounds(375, menu.screenHeight - 185, 500, 120 );
        resetLabel.setForeground(Color.WHITE);
        resetLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: reset all fields to default values
            }
        });
        resetLabel.setBorder(menu.defaultBorder);

        backLabel = new JLabel("< Back", SwingConstants.CENTER);
        backLabel.setFont(play.optionFont);
        backLabel.setBounds(50, menu.screenHeight - 185, 300, 120);
        backLabel.setForeground(Color.WHITE);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(menu.modifiersPanel, menu.playPanel);
            }
        });
        backLabel.setBorder(menu.defaultBorder);
        
        saveLabel = new JLabel("Save", SwingConstants.CENTER);
        saveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        saveLabel.setBounds(menu.screenWidth - 300, menu.screenHeight - 200, 250, 150);
        saveLabel.setForeground(Color.WHITE);
        saveLabel.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: save values in text field
            }
        });
        saveLabel.setBorder(menu.defaultBorder);

        add(backLabel);
        add(resetLabel);
        add(saveLabel);

        // ------------ Modifier Labels ------------ //
        playerHealthLabel = new JLabel("Player Health");
        playerHealthLabel.setFont(modifierFont);
        playerHealthLabel.setBounds(menu.screenWidth - 300, menu.screenHeight - 200, 250, 150);// TODO: adjust positions of all of these
        playerHealthLabel.setForeground(Color.WHITE);

        playerDamageLabel = new JLabel("Player Damage");
        playerDamageLabel.setFont(modifierFont);
        playerDamageLabel.setBounds(menu.screenWidth - 300, menu.screenHeight - 200, 250, 150);
        playerDamageLabel.setForeground(Color.WHITE);

        playerFovLabel = new JLabel("Player FOV");
        playerFovLabel.setFont(modifierFont);
        playerFovLabel.setBounds(menu.screenWidth - 300, menu.screenHeight - 200, 250, 150);
        playerFovLabel.setForeground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(menu.backgroundColor);
        g.fillRect(0, 0, menu.screenWidth, menu.screenHeight);   
        
        g.drawImage(headerImage, 50, 50, 1000, 158, this);
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