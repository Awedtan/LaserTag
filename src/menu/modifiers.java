package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import packy.*;

@SuppressWarnings("serial")
public class modifiers extends JPanel implements MouseListener {
    JLabel resetLabel, backLabel, saveLabel; // TODO: add load previous options?

    ImageIcon checkTrue = new ImageIcon("images/checkbox_true.png");
    ImageIcon checkFalse = new ImageIcon("images/checkbox_false.png");

    // All modifier labels
    JLabel playerFovLabel, playerViewRangeLabel, playerDamageLabel, playerHealthLabel, enemyFovLabel, enemyViewRangeLabel, enemyDamageLabel, enemyHealthLabel;

    // Modifier variables: player
    static boolean playerFovEnabled = true;
    static int playerFovRange, playerViewRange, playerDamage, playerHealth;

    // Modifier variables: enemy
    static int enemyFovRange, enemyViewRange, enemyDamage, enemyHealth;

    // Modifier inputs: player
    static JCheckBox playerFovCheck;
    static JFormattedTextField playerFovInput, playerViewRangeInput, playerDamageInput, playerHealthInput;

    // Modifier inputs: enemy
    static JCheckBox enemyFovCheck;
    static JFormattedTextField enemyFovInput, enemyViewRangeInput, enemyDamageInput, enemyHealthInput;

    Image headerImage = Toolkit.getDefaultToolkit().getImage("images/header_modifiers.png");

    Font modifierFont = new Font("Segoe UI", Font.PLAIN, 50);

    public static void setDefault() {
        playerHealth = packy.defaultValues.playerHealth;
        playerDamage = packy.defaultValues.playerDamage;
        playerFovRange = packy.defaultValues.playerFovRange;
        playerViewRange = packy.defaultValues.playerViewRange;

        playerHealthInput.setValue(packy.defaultValues.playerHealth);
        playerDamageInput.setValue(packy.defaultValues.playerDamage);
        playerFovCheck.setSelected(true);
        playerFovInput.setValue(packy.defaultValues.playerFovRange);
        playerFovInput.setEditable(true);
        playerFovInput.setBackground(Color.WHITE);
        playerFovInput.setForeground(Color.BLACK);
        playerViewRangeInput.setValue(packy.defaultValues.playerViewRange);

        enemyHealth = packy.defaultValues.enemyHealth;
        enemyDamage = packy.defaultValues.enemyDamage;
        enemyFovRange = packy.defaultValues.enemyFovRange;
        enemyViewRange = packy.defaultValues.enemyViewRange;

        enemyHealthInput.setValue(packy.defaultValues.enemyHealth);
        enemyDamageInput.setValue(packy.defaultValues.enemyDamage);
        // enemyFovCheck.setSelected(true);
        enemyFovInput.setValue(packy.defaultValues.enemyFovRange);
        enemyFovInput.setEditable(true);
        enemyFovInput.setBackground(Color.WHITE);
        enemyFovInput.setForeground(Color.BLACK);
        enemyViewRangeInput.setValue(packy.defaultValues.enemyViewRange);
    }

    public static void parseInputs() {
        try {
        	
            playerHealth = Integer.parseInt(playerHealthInput.getText().replace(",", ""));
            playerDamage = Integer.parseInt(playerDamageInput.getText().replace(",", ""));
            playerFovEnabled = playerFovCheck.isSelected();
            playerFovRange = Integer.parseInt(playerFovInput.getText().replace(",", ""));
            playerViewRange = Integer.parseInt(playerViewRangeInput.getText().replace(",", ""));

            enemyHealth = Integer.parseInt(enemyHealthInput.getText().replace(",", ""));
            enemyDamage = Integer.parseInt(enemyDamageInput.getText().replace(",", ""));
            // enemyFovEnabled = enemyFovCheck.isSelected();
            enemyFovRange = Integer.parseInt(enemyFovInput.getText().replace(",", ""));
            enemyViewRange = Integer.parseInt(enemyViewRangeInput.getText().replace(",", ""));
        } catch (Exception e) {
            //TODO: Show red text that say no no when inputs poopy (shouldn't happen anymore but better safe than sorry)
        }
    }

    public modifiers() {
        setPreferredSize(new Dimension(body.screenWidth, body.screenHeight));
        setFocusable(true);
        setLayout(null);

        // ------------ Menu operation labels ------------ //
        resetLabel = new JLabel("Reset to defaults", SwingConstants.CENTER);
        resetLabel.setFont(play.optionFont);
        resetLabel.setBounds(375, body.screenHeight - 185, 500, 120);
        resetLabel.setForeground(Color.WHITE);
        resetLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDefault();
            }
        });
        resetLabel.setBorder(menu.defaultBorder);

        backLabel = new JLabel("< Back", SwingConstants.CENTER);
        backLabel.setFont(play.optionFont);
        backLabel.setBounds(50, body.screenHeight - 185, 300, 120);
        backLabel.setForeground(Color.WHITE);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	parseInputs();
                menu.switchStatePanel(body.modifiersPanel, body.playPanel);
            }
        });
        backLabel.setBorder(menu.defaultBorder);
        
        saveLabel = new JLabel("Save", SwingConstants.CENTER);
        saveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        saveLabel.setBounds(body.screenWidth - 300, body.screenHeight - 200, 250, 150);
        saveLabel.setForeground(Color.WHITE);
        saveLabel.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: save values in text field
                parseInputs();
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

        playerHealthInput = new JFormattedTextField();
        playerHealthInput.setFont(modifierFont);
        playerHealthInput.setBounds(500, 290, 250, 75);
        playerHealthInput.setForeground(Color.BLACK);
        playerHealthInput.setBackground(Color.WHITE);

        playerDamageLabel = new JLabel("Player Damage:");
        playerDamageLabel.setFont(modifierFont);
        playerDamageLabel.setBounds(50, 350, 500, 150);
        playerDamageLabel.setForeground(Color.WHITE);

        playerDamageInput = new JFormattedTextField();
        playerDamageInput.setFont(modifierFont);
        playerDamageInput.setBounds(500, 390, 250, 75);
        playerDamageInput.setForeground(Color.BLACK);
        playerDamageInput.setBackground(Color.WHITE);

        playerFovLabel = new JLabel("Player FOV:");
        playerFovLabel.setFont(modifierFont);
        playerFovLabel.setBounds(50, 450, 500, 150);
        playerFovLabel.setForeground(Color.WHITE);

        playerFovCheck = new JCheckBox(checkFalse, true);
        playerFovCheck.setSelected(true);
        playerFovCheck.setFont(modifierFont);
        playerFovCheck.setForeground(Color.WHITE);
        playerFovCheck.setBackground(menu.backgroundColor);
        playerFovCheck.setBounds(400, 490, 80, 75);
        playerFovCheck.setSelectedIcon(checkTrue);
        playerFovCheck.setDisabledIcon(checkFalse);
        playerFovCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = playerFovCheck.getModel().isSelected();
                if (selected) {
                    playerFovInput.setEnabled(true);
                    playerFovInput.setBackground(Color.WHITE);
                } else if (!selected) {
                    playerFovInput.setEnabled(false);
                    playerFovInput.setBackground(Color.GRAY);
                }
            }
        });

        playerFovInput = new JFormattedTextField();
        playerFovInput.setFont(modifierFont);
        playerFovInput.setBounds(500, 490, 250, 75);
        playerFovInput.setForeground(Color.BLACK);
        playerFovInput.setBackground(Color.WHITE);

        playerViewRangeLabel = new JLabel("Player View Range:");
        playerViewRangeLabel.setFont(modifierFont);
        playerViewRangeLabel.setBounds(50, 550, 500, 150);
        playerViewRangeLabel.setForeground(Color.WHITE);

        playerViewRangeInput = new JFormattedTextField();
        playerViewRangeInput.setFont(modifierFont);
        playerViewRangeInput.setBounds(500, 590, 250, 75);
        playerViewRangeInput.setForeground(Color.BLACK);
        playerViewRangeInput.setBackground(Color.WHITE);

        // Enemy modifiers
        enemyHealthLabel = new JLabel("Enemy Health:");
        enemyHealthLabel.setFont(modifierFont);
        enemyHealthLabel.setBounds(body.screenWidth / 2, 250, 500, 150);
        enemyHealthLabel.setForeground(Color.WHITE);

        enemyHealthInput = new JFormattedTextField();
        enemyHealthInput.setFont(modifierFont);
        enemyHealthInput.setBounds(500 + body.screenWidth / 2, 290, 250, 75);
        enemyHealthInput.setForeground(Color.BLACK);
        enemyHealthInput.setBackground(Color.WHITE);

        enemyDamageLabel = new JLabel("Enemy Damage:");
        enemyDamageLabel.setFont(modifierFont);
        enemyDamageLabel.setBounds(body.screenWidth / 2, 350, 500, 150);
        enemyDamageLabel.setForeground(Color.WHITE);

        enemyDamageInput = new JFormattedTextField();
        enemyDamageInput.setFont(modifierFont);
        enemyDamageInput.setBounds(500 + body.screenWidth / 2, 390, 250, 75);
        enemyDamageInput.setForeground(Color.BLACK);
        enemyDamageInput.setBackground(Color.WHITE);

        enemyFovLabel = new JLabel("Enemy FOV:");
        enemyFovLabel.setFont(modifierFont);
        enemyFovLabel.setBounds(body.screenWidth / 2, 450, 500, 150);
        enemyFovLabel.setForeground(Color.WHITE);

        enemyFovInput = new JFormattedTextField();
        enemyFovInput.setFont(modifierFont);
        enemyFovInput.setBounds(500 + body.screenWidth / 2, 490, 250, 75);
        enemyFovInput.setForeground(Color.BLACK);
        enemyFovInput.setBackground(Color.WHITE);
        // enemyFovInput.setValue();

        enemyViewRangeLabel = new JLabel("Enemy View Range:");
        enemyViewRangeLabel.setFont(modifierFont);
        enemyViewRangeLabel.setBounds(body.screenWidth / 2, 550, 500, 150);
        enemyViewRangeLabel.setForeground(Color.WHITE);

        enemyViewRangeInput = new JFormattedTextField();
        enemyViewRangeInput.setFont(modifierFont);
        enemyViewRangeInput.setBounds(500 + body.screenWidth / 2, 590, 250, 75);
        enemyViewRangeInput.setForeground(Color.BLACK);
        enemyViewRangeInput.setBackground(Color.WHITE);

        add(playerHealthLabel);
        add(playerDamageLabel);
        add(playerFovLabel);
        add(playerViewRangeLabel);
        add(enemyHealthLabel);
        add(enemyDamageLabel);
        add(enemyFovLabel);
        add(enemyViewRangeLabel);

        // ------------ Modifier boxes ------------ //
        add(playerHealthInput);
        add(playerDamageInput);
        add(playerFovCheck);
        add(playerFovInput);
        add(playerViewRangeInput);

        add(enemyHealthInput);
        add(enemyDamageInput);
        // add(enemyFovCheck);
        add(enemyFovInput);
        add(enemyViewRangeInput);

        setDefault();
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(menu.backgroundColor);
        g.fillRect(0, 0, body.screenWidth, body.screenHeight);   
        
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