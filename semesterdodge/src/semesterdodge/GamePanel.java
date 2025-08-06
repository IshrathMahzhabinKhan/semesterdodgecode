package semesterdodge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Image background;
    Timer timer;
    Player player;

    public GamePanel(GameFrame frame) {
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        background = AssetLoader.load("res/background.png");

        player = new Player(); 

        JRadioButton exitButton = new JRadioButton("Exit Game");
        exitButton.setBounds(650, 20, 120, 30);
        exitButton.setOpaque(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);

        timer = new Timer(20, this); 
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Draw player
        if (player != null) {
            player.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update(); 
        repaint();       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.jump(); 
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
