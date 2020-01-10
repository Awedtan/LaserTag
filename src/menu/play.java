package menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class play extends JPanel implements KeyListener, MouseListener{
    JLabel titleLabel, enemiesLabel, mapLabel, modifiersLabel, startLabel;

    Border defaultBorder = BorderFactory.createLineBorder(Color.WHITE, 5);
    
    static int numberEnemies;
    static String mapName;
    static Font optionFont = new Font("Segoe UI", Font.PLAIN, 60);

    public play() {
        setPreferredSize(new Dimension(menu.screenWidth, menu.screenHeight));
        setLayout(null);
        
        titleLabel = new JLabel("Play");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 100));
        titleLabel.setBounds(50, 50, 250, 150);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.switchStatePanel(menu.menuPanel, menu.playPanel, menu.STATE.MAIN);
            }
        });

        enemiesLabel = new JLabel("Enemies:");
        enemiesLabel.setFont(optionFont);
        enemiesLabel.setBounds(50, 300, 250, 150);
        enemiesLabel.setForeground(Color.WHITE);
        // TODO: add input field for number of enemes (Daniel says unbounded, Daniel is wrong)

        mapLabel = new JLabel("Map:");
        mapLabel.setFont(optionFont);
        mapLabel.setBounds(50, 400, 250, 150);
        mapLabel.setForeground(Color.WHITE);

        modifiersLabel = new JLabel("Modifiers", SwingConstants.CENTER);
        modifiersLabel.setFont(optionFont);
        modifiersLabel.setBounds(50, menu.screenHeight - 185, 300, 120);
        modifiersLabel.setForeground(Color.WHITE);
        modifiersLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: implement modifiers menu
            }
        });
        modifiersLabel.setBorder(defaultBorder);

        startLabel = new JLabel("Start", SwingConstants.CENTER);
        startLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        startLabel.setBounds(menu.screenWidth - 300, menu.screenHeight - 200, 250, 150);
        startLabel.setForeground(Color.WHITE);
        startLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: run the game using the settings here and on the modifers page
            }
        });
        startLabel.setBorder(defaultBorder);

        add(titleLabel);
        add(enemiesLabel);
        add(mapLabel);
        add(modifiersLabel);
        add(startLabel);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(20, 20, 20));
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