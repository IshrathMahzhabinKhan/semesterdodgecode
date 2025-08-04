package semesterdodge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    Image background;

    public GamePanel(GameFrame frame) {
        setLayout(null);

        background = AssetLoader.load("res/background.png");

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
    }
}
