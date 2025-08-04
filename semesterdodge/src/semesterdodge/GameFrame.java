package semesterdodge;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Semester Dodge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        showHomePage();

        setVisible(true);
    }

    public void showHomePage() {
        setContentPane(new HomePanel(this));
        revalidate();
    }

    public void startGame() {
        setContentPane(new GamePanel(this));
        revalidate();
        repaint();
    }
}
