package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.MaskFormatter;

import packy.*;

@SuppressWarnings("serial")
public class modifiers extends JPanel implements MouseListener {
    JLabel resetLabel, backLabel, saveLabel; // TODO: add load previous options?

    // All modifier labels
    JLabel playerFovLabel, playerViewrangeLabel, playerDamageLabel, playerHealthLabel, enemyFovLabel, enemyViewrangeLabel, enemyDamageLabel, enemyHealthLabel;

    // Modifier variables: player
    boolean playerFovEnabled;
    int playerFovRange, playerViewrange, playerDamage, playerHealth;

    // Modifier variables: enemy
    boolean enemyFovEnabled;
    int enemyFovRange, enemyViewrange, enemyDamage, enemyHealth;

    // Modifier inputs: player
    JCheckBox playerFovCheck;
    JFormattedTextField playerFovInput, playerViewrangeInput, playerDamageInput, playerHealthInput;

    // Modifier inputs: enemy
    JCheckBox enemyFovCheck;
    JFormattedTextField enemyFovInput, enemyViewrangeInput, enemyDamageInput, enemyHealthInput;

    Image headerImage = Toolkit.getDefaultToolkit().getImage("images/header_modifiers.png");

    Font modifierFont = new Font("Segoe UI", Font.PLAIN, 50);

    MaskFormatter numberFormatter(String format) {
        MaskFormatter numberMask = null;
        try {
            numberMask = new MaskFormatter(format);
            numberMask.setAllowsInvalid(false);
        } catch (Exception e) {
            System.err.println("bad formatter" + e.getMessage());
        }
        return numberMask;
    }

    public modifiers() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
        setFocusable(true);
        setLayout(null);

        // ------------ Menu operation labels ------------ //
        resetLabel = new JLabel("Reset to defaults", SwingConstants.CENTER);
        resetLabel.setFont(play.optionFont);
        resetLabel.setBounds(375, menu.screenHeight - 185, 500, 120);
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
                menu.switchStatePanel(body.modifiersPanel, body.playPanel);
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
                menu.switchStatePanel(body.modifiersPanel, body.playPanel);
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

        playerHealthInput = new JFormattedTextField(numberFormatter("###"));
        playerHealthInput.setFont(modifierFont);
        playerHealthInput.setBounds(360, 290, 250, 75);
        playerHealthInput.setForeground(Color.WHITE);
        playerHealthInput.setBackground(menu.backgroundColor);

        playerDamageLabel = new JLabel("Player Damage:");
        playerDamageLabel.setFont(modifierFont);
        playerDamageLabel.setBounds(50, 350, 500, 150);
        playerDamageLabel.setForeground(Color.WHITE);

        playerDamageInput = new JFormattedTextField(numberFormatter("###"));
        playerDamageInput.setFont(modifierFont);
        playerDamageInput.setBounds(400, 390, 250, 75);
        playerDamageInput.setForeground(Color.WHITE);
        playerDamageInput.setBackground(menu.backgroundColor);

        playerFovLabel = new JLabel("Player FOV:");
        playerFovLabel.setFont(modifierFont);
        playerFovLabel.setBounds(50, 450, 500, 150);
        playerFovLabel.setForeground(Color.WHITE);

        playerFovCheck = new JCheckBox();
        playerFovCheck.setSelected(true);
        playerFovCheck.setFont(modifierFont);
        playerFovCheck.setForeground(Color.WHITE);
        playerFovCheck.setBackground(menu.backgroundColor);
        playerFovCheck.setBounds(310, 470, 75, 75); // TODO: change the image to something that's actually f-ing usable

        playerViewrangeLabel = new JLabel("Player Viewrange:");
        playerViewrangeLabel.setFont(modifierFont);
        playerViewrangeLabel.setBounds(50, 550, 500, 150);
        playerViewrangeLabel.setForeground(Color.WHITE);

        playerViewrangeInput = new JFormattedTextField(numberFormatter("###"));
        playerViewrangeInput.setFont(modifierFont);
        playerViewrangeInput.setBounds(450, 590, 250, 75);
        playerViewrangeInput.setForeground(Color.WHITE);
        playerViewrangeInput.setBackground(menu.backgroundColor);

        // Enemy modifiers
        enemyHealthLabel = new JLabel("Enemy Health:");
        enemyHealthLabel.setFont(modifierFont);
        enemyHealthLabel.setBounds(body.screenWidth / 2, 250, 500, 150);
        enemyHealthLabel.setForeground(Color.WHITE);

        enemyDamageLabel = new JLabel("Enemy Damage:");
        enemyDamageLabel.setFont(modifierFont);
        enemyDamageLabel.setBounds(body.screenWidth / 2, 350, 500, 150);
        enemyDamageLabel.setForeground(Color.WHITE);

        enemyFovLabel = new JLabel("Enemy FOV:");
        enemyFovLabel.setFont(modifierFont);
        enemyFovLabel.setBounds(body.screenWidth / 2, 450, 500, 150);
        enemyFovLabel.setForeground(Color.WHITE);

        enemyViewrangeLabel = new JLabel("Enemy Viewrange:");
        enemyViewrangeLabel.setFont(modifierFont);
        enemyViewrangeLabel.setBounds(body.screenWidth / 2, 550, 500, 150);
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
        add(playerHealthInput);
        add(playerDamageInput);
        add(playerFovCheck);
        add(playerViewrangeInput);
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
}