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

        startBtn.setBounds(300, 150, 200, 50);
        aboutBtn.setBounds(300, 210, 200, 50);
        exitBtn.setBounds(300, 270, 200, 50);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.startGame();
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

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(startBtn);
        add(aboutBtn);
        add(exitBtn);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(45, 45, 45)); // Dark grey
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(45, 45, 45));
            }
        });

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2d = (Graphics2D) g;
        String title = "Semester Dodge";


        Font titleFont = new Font("Impact", Font.BOLD, 70);
        g2d.setFont(titleFont);

        int textWidth = g2d.getFontMetrics().stringWidth(title);
        int x = (getWidth() - textWidth) / 2;
        int y = 100;

 
        for (int i = 6; i > 0; i--) {
            g2d.setColor(new Color(255, 50, 50, 40)); 
            g2d.drawString(title, x - i, y - i);
            g2d.drawString(title, x + i, y - i);
            g2d.drawString(title, x - i, y + i);
            g2d.drawString(title, x + i, y + i);
        }

     
        GradientPaint gp = new GradientPaint(x, y - 50, new Color(255, 70, 70),
                                             x, y + 20, new Color(180, 0, 0));
        g2d.setPaint(gp);
        g2d.drawString(title, x, y);
    }
}
