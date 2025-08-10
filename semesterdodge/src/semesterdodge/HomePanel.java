package semesterdodge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class HomePanel extends JPanel {
    private Image background;

    public HomePanel(GameFrame frame) {
        try {
            background = ImageIO.read(getClass().getResource("/menu_page.jpg"));
        } catch (IOException e) {
            System.out.println("Could not load menu_page.jpg");
        }

        setLayout(null);

        JButton startBtn = createButton("Start Game");
        JButton aboutBtn = createButton("About");
        JButton exitBtn = createButton("Exit");

        startBtn.setBounds(300, 150, 200, 40);
        aboutBtn.setBounds(300, 210, 200, 40);
        exitBtn.setBounds(300, 270, 200, 40);

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

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    HomePanel.this, 
                    "🎓 Semester Dodge Game\n\nAvoid the obstacles and survive as long as possible!\nUse keyboard controls to play.\n\nCreated for fun and learning.",
                    "About the Game",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        add(startBtn);
        add(aboutBtn);
        add(exitBtn);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180)); 
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Semester Dodge", 250, 100);
    }
}
