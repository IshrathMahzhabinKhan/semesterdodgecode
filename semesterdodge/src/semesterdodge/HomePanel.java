package semesterdodge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePanel extends JPanel {
    public HomePanel(GameFrame frame) {
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        JButton startBtn = new JButton("Start Game");
        JButton exitBtn = new JButton("Exit");

        startBtn.setBounds(300, 150, 200, 40);
        exitBtn.setBounds(300, 210, 200, 40);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.startGame();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(startBtn);
        add(exitBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Semester Dodger", 270, 100);
    }
}
