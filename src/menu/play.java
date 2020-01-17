package menu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import packy.*;
import packy.game.MODE;

@SuppressWarnings({"serial", "rawtypes"})
public class play extends JPanel implements MouseListener{
    JLabel titleLabel, enemiesLabel, mapLabel, modifiersLabel, gamemodeLabel, backLabel, startLabel, timeLabel;
    
    Image headerImage = Toolkit.getDefaultToolkit().getImage("images/header_play.png");
    static Font optionFont = new Font("Segoe UI", Font.PLAIN, 60);

    JSpinner enemiesInput, timeInput; // TODO: adjust max size of spinner (what is our max number?)
    JComboBox<String> mapSelector;
    JComboBox<game.MODE> gamemodeSelector;
    static File mapFolder = new File("maps");
    static String[] mapFilepaths = mapFolder.list();
    
    // Player-selected settings
    int enemiesCount;
    public static String mapName = "maps/" + mapFilepaths[0];
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
        enemiesInput = new JSpinner(new SpinnerNumberModel(15, 1, packy.enemy.MAX, 1));
        enemiesInput.setFont(optionFont);
        enemiesInput.setBounds(300, 280, 250, 100);
        enemiesInput.getEditor().getComponent(0).setForeground(Color.BLACK);
        enemiesInput.getEditor().getComponent(0).setBackground(Color.WHITE);

        mapLabel = new JLabel("Map:");
        mapLabel.setFont(optionFont);
        mapLabel.setBounds(50, 400, 250, 150);
        mapLabel.setForeground(Color.WHITE);

        mapSelector = new JComboBox<String>(mapFilepaths);
        mapSelector.setFont(optionFont);
        mapSelector.setBounds(200, 425, 600, 100);
        mapSelector.setForeground(Color.BLACK);
        mapSelector.setBackground(Color.WHITE);

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

        gamemodeSelector = new JComboBox<game.MODE>(game.MODE.values());
        gamemodeSelector.setFont(optionFont);
        gamemodeSelector.setBounds(400, 570, 500, 100);
        gamemodeSelector.setForeground(Color.BLACK);
        gamemodeSelector.setBackground(Color.WHITE);
        
        timeLabel = new JLabel("Time Limit:");
    	timeLabel.setFont(optionFont);
    	timeLabel.setBounds(50, 700, 500, 150);
    	timeLabel.setForeground(Color.WHITE);

    	timeInput = new JSpinner(new SpinnerNumberModel(60, 10, 3600, 10));
    	timeInput.setFont(optionFont);
    	timeInput.setBounds(400, 725, 300, 100);
        timeInput.getEditor().getComponent(0).setForeground(Color.BLACK);
        timeInput.getEditor().getComponent(0).setBackground(Color.WHITE);
        
        gamemodeSelector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox comboBox = (JComboBox) event.getSource();
                
                if(comboBox.getSelectedItem().toString().equals("DM")){
                	
                	timeLabel.setVisible(true);
                    timeInput.setVisible(true);
                }
                else {
                	
                	timeLabel.setVisible(false);
                    timeInput.setVisible(false);
                }
            }
        });

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
                	
                	game.killLimit = Integer.parseInt(enemiesInput.getValue().toString());
                	game.timeLimit = Integer.parseInt(timeInput.getValue().toString());
                	game.mode = (MODE) gamemodeSelector.getSelectedItem();
                	packy.body.startTime = (int) System.currentTimeMillis();
                    enemiesCount = Integer.parseInt(enemiesInput.getValue().toString());
                    packy.enemy.MAX = enemiesCount;
                    mapName = "maps\\" + (String) mapSelector.getSelectedItem();
                    packy.game.file = new File(mapName);
                    packy.body.panel = new body();
                    menu.switchStatePanel(body.playPanel, body.panel);
                    
                    packy.player.FOVENABLED = modifiers.playerFovEnabled;
                    packy.player.VIEWRANGE = modifiers.playerViewRange;
                    packy.player.health = modifiers.playerHealth;
                    packy.playerProjectile.DAMAGE = modifiers.playerDamage;
                    packy.player.FOV = modifiers.playerFovRange;
                    
                    packy.enemy.VIEWRANGE = modifiers.enemyViewRange;
                    packy.enemy.HEALTH = modifiers.enemyHealth;
                    packy.enemyProjectile.DAMAGE = modifiers.enemyDamage;
                    packy.enemy.FOV = modifiers.enemyFovRange;
                } catch (Exception e2) {
                    // TODO: return error if something bad
                }
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
        add(gamemodeSelector);
        add(backLabel);
        add(startLabel);
        add(timeLabel);
        add(timeInput);
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