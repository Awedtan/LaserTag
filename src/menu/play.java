package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class play extends JPanel implements KeyListener, MouseListener{
    JLabel titleLabel, enemiesLabel, mapLabel, modifiersLabel, backLabel, startLabel;

    Image headerImage = Toolkit.getDefaultToolkit().getImage("images/header_play.png");

    JSpinner enemiesInput; // TODO: adjust max size of spinner (what is our max number?)
    
    static int enemiesCount;
    static String mapName;
    static Font optionFont = new Font("Segoe UI", Font.PLAIN, 60);

    public play() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
        setLayout(null);
        
        titleLabel = new JLabel("Play");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 100));
        titleLabel.setBounds(50, 50, 250, 150);
        titleLabel.setForeground(Color.WHITE);

        enemiesLabel = new JLabel("Enemies:");
        enemiesLabel.setFont(optionFont);
        enemiesLabel.setBounds(50, 250, 250, 150);
        enemiesLabel.setForeground(Color.WHITE);

        // TODO: figure out how to change colors for this (right now it's mostly the default look and feel)
        enemiesInput = new JSpinner(new SpinnerNumberModel(0, 0, 15, 1));
        enemiesInput.setFont(optionFont);
        enemiesInput.setBounds(300, 300, 250, 70);
        enemiesInput.setBorder(menu.defaultBorder);

        mapLabel = new JLabel("Map:");
        mapLabel.setFont(optionFont);
        mapLabel.setBounds(50, 400, 250, 150);
        mapLabel.setForeground(Color.WHITE);

        modifiersLabel = new JLabel("Change Modifiers", SwingConstants.CENTER);
        modifiersLabel.setFont(optionFont);
        modifiersLabel.setBounds(50, 600, 500, 120);
        modifiersLabel.setForeground(Color.WHITE);
        modifiersLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: implement modifiers menu
                menu.switchStatePanel(menu.playPanel, menu.modifiersPanel);
            }
        });
        modifiersLabel.setBorder(menu.defaultBorder);

        backLabel = new JLabel("< Back", SwingConstants.CENTER);
        backLabel.setFont(optionFont);
        backLabel.setBounds(50, menu.screenHeight - 185, 300, 120);
        backLabel.setForeground(Color.WHITE);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(menu.playPanel, menu.menuPanel);
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
                // body.main(new String[0]);
            }
        });
        startLabel.setBorder(menu.defaultBorder);

        // add(titleLabel);
        add(enemiesLabel);
        add(enemiesInput);
        add(mapLabel);
        add(modifiersLabel);
        add(backLabel);
        add(startLabel);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(menu.backgroundColor);
        g.fillRect(0, 0, menu.screenWidth, menu.screenHeight);   
        
        g.drawImage(headerImage, 50, 50, 490, 150, this);
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