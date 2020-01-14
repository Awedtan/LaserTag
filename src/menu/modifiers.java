package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class modifiers extends JPanel implements KeyListener, MouseListener {
    JLabel resetLabel, backLabel, saveLabel; // TODO: add load previous options?

    // All modifier labels
    JLabel playerFovLabel, playerViewrangeLabel, playerDamageLabel, playerHealthLabel, enemyFovLabel, enemyViewrangeLabel, enemyDamageLabel, enemyHealthLabel;
    JTextField playerFovField, playerViewrangeField, playerDamageField, playerHealthField, enemyFovField, enemyViewrangeField, enemyDamageField, enemyHealthField;

    // Modifier variables: player
    boolean playerFovEnabled;
    int playerFovRange, playerViewrange, playerDamage, playerHealth;

    // Modifier variables: enemy
    boolean enemyFovEnabled;
    int enemyFovRange, enemyViewrange, enemyDamage, enemyHealth;

    // Modifier inputs: player
    JCheckBox playerFovCheck;
    JTextField playerFovInput, playerViewrangeInput, playerDamageInput, playerHealthInput;

    // Modifier inputs: enemy
    JCheckBox enemyFovCheck;
    JTextField enemyFovInput, enemyViewrangeInput, enemyDamageInput, enemyHealthInput;

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
                menu.switchStatePanel(menu.modifiersPanel, menu.playPanel);
            }
        });
        saveLabel.setBorder(menu.defaultBorder);

        add(backLabel);
        add(resetLabel);
        add(saveLabel);

        // ------------ Modifier Labels ------------ //
        // Player modifiers
        playerHealthLabel = new JLabel("Player Health:");
        playerHealthLabel.setFont(modifierFont);
        playerHealthLabel.setBounds(50, 250, 500, 150);
        playerHealthLabel.setForeground(Color.WHITE);

        playerDamageLabel = new JLabel("Player Damage:");
        playerDamageLabel.setFont(modifierFont);
        playerDamageLabel.setBounds(50, 350, 500, 150);
        playerDamageLabel.setForeground(Color.WHITE);

        playerFovLabel = new JLabel("Player FOV:");
        playerFovLabel.setFont(modifierFont);
        playerFovLabel.setBounds(50, 450, 500, 150);
        playerFovLabel.setForeground(Color.WHITE);

        playerViewrangeLabel = new JLabel("Player Viewrange:");
        playerViewrangeLabel.setFont(modifierFont);
        playerViewrangeLabel.setBounds(50, 550, 500, 150);
        playerViewrangeLabel.setForeground(Color.WHITE);

        // Enemy modiviers
        enemyHealthLabel = new JLabel("Enemy Health:");
        enemyHealthLabel.setFont(modifierFont);
        enemyHealthLabel.setBounds(900, 250, 500, 150);
        enemyHealthLabel.setForeground(Color.WHITE);

        enemyDamageLabel = new JLabel("Enemy Damage:");
        enemyDamageLabel.setFont(modifierFont);
        enemyDamageLabel.setBounds(900, 350, 500, 150);
        enemyDamageLabel.setForeground(Color.WHITE);

        enemyFovLabel = new JLabel("Enemy FOV:");
        enemyFovLabel.setFont(modifierFont);
        enemyFovLabel.setBounds(900, 450, 500, 150);
        enemyFovLabel.setForeground(Color.WHITE);

        enemyViewrangeLabel = new JLabel("Enemy Viewrange:");
        enemyViewrangeLabel.setFont(modifierFont);
        enemyViewrangeLabel.setBounds(900, 550, 500, 150);
        enemyViewrangeLabel.setForeground(Color.WHITE);

        add(playerHealthLabel);
        add(playerDamageLabel);
        add(playerFovLabel);
        add(playerViewrangeLabel);
        add(enemyHealthLabel);
        add(enemyDamageLabel);
        add(enemyFovLabel);
        add(enemyViewrangeLabel);

        // ------------ Modifier boxes ------------ //

    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(menu.backgroundColor);
        g.fillRect(0, 0, menu.screenWidth, menu.screenHeight);   
        
        g.drawImage(headerImage, 50, 50, 1000, 160, this);
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