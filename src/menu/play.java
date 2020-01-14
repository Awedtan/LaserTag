package menu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import packy.*;

@SuppressWarnings("serial")
public class play extends JPanel implements MouseListener{
    JLabel titleLabel, enemiesLabel, mapLabel, modifiersLabel, gamemodeLabel, backLabel, startLabel;
    
    Image headerImage = Toolkit.getDefaultToolkit().getImage("images/header_play.png");
    static Font optionFont = new Font("Segoe UI", Font.PLAIN, 60);

    JSpinner enemiesInput; // TODO: adjust max size of spinner (what is our max number?)
    JComboBox<String> mapSelector;
    File mapFolder = new File("maps");
    String[] mapFilepaths = mapFolder.list();
    
    // Player-selected settings
    int enemiesCount;
    String mapName;
    game.MODE gamemode; // TODO: use these values in the actual game

    public play() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
        setFocusable(true);
        setLayout(null);

        enemiesLabel = new JLabel("Enemies:");
        enemiesLabel.setFont(optionFont);
        enemiesLabel.setBounds(50, 250, 250, 150);
        enemiesLabel.setForeground(Color.WHITE);

        // TODO: figure out how to change colors for this (right now it's mostly the default look and feel)
        enemiesInput = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));
        enemiesInput.setFont(optionFont);
        enemiesInput.setBounds(300, 300, 250, 70);
        enemiesInput.getEditor().getComponent(0).setForeground(Color.WHITE);
        enemiesInput.getEditor().getComponent(0).setBackground(menu.backgroundColor);

        mapLabel = new JLabel("Map:");
        mapLabel.setFont(optionFont);
        mapLabel.setBounds(50, 400, 250, 150);
        mapLabel.setForeground(Color.WHITE);

        mapSelector = new JComboBox<String>(mapFilepaths);
        mapSelector.setFont(optionFont);
        mapSelector.setBounds(250, 425, 600, 100);
        mapSelector.setForeground(Color.WHITE);
        mapSelector.setBackground(menu.backgroundColor);

        modifiersLabel = new JLabel("Change Modifiers", SwingConstants.CENTER);
        modifiersLabel.setFont(optionFont);
        modifiersLabel.setBounds(375, menu.screenHeight - 185, 500, 120);
        modifiersLabel.setForeground(Color.WHITE);
        modifiersLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(body.playPanel, body.modifiersPanel);
            }
        });
        modifiersLabel.setBorder(menu.defaultBorder);

        gamemodeLabel = new JLabel("Gamemode: ");
        gamemodeLabel.setFont(optionFont);
        gamemodeLabel.setBounds(50, 550, 500, 120);
        gamemodeLabel.setForeground(Color.WHITE);

        backLabel = new JLabel("< Back", SwingConstants.CENTER);
        backLabel.setFont(optionFont);
        backLabel.setBounds(50, menu.screenHeight - 185, 300, 120);
        backLabel.setForeground(Color.WHITE);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(body.playPanel, body.menuPanel);
            }
        });
        backLabel.setBorder(menu.defaultBorder);

        startLabel = new JLabel("Start", SwingConstants.CENTER);
        startLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        startLabel.setBounds(menu.screenWidth - 300, menu.screenHeight - 200, 250, 150);
        startLabel.setForeground(Color.WHITE);
        startLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: run the game using the settings here and on the modifers page
                try {
                    enemiesCount = Integer.parseInt(enemiesInput.getValue().toString());
                } catch (Exception e2) {
                    //TODO: return error if something poopy
                }
                mapName = "maps/" + (String) mapSelector.getSelectedItem();
                // body.main(new String[0]);
                menu.switchStatePanel(body.playPanel, body.panel);
            }
        });
        startLabel.setBorder(menu.defaultBorder);

        // add(titleLabel);
        add(enemiesLabel);
        add(enemiesInput);
        add(mapLabel);
        add(mapSelector);
        add(modifiersLabel);
        add(gamemodeLabel);
        add(backLabel);
        add(startLabel);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(menu.backgroundColor);
        g.fillRect(0, 0, menu.screenWidth, menu.screenHeight);   
        
        g.drawImage(headerImage, 50, 50, 500, 173, this);
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