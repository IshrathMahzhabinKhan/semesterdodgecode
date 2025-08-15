package semesterdodge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Image background;
    Timer timer;
    Player player;

    ArrayList<Obstacle> obstacles;
    Random random;
    int obstacleSpawnCounter = 0;

    int score = 0;
    boolean gameOver = false;

    public GamePanel(GameFrame frame) {
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);

        SwingUtilities.invokeLater(() -> requestFocusInWindow());

        background = AssetLoader.load("res/background.png");
        player = new Player(); 

        obstacles = new ArrayList<>();
        random = new Random();


        JRadioButton exitButton = new JRadioButton("Exit Game");
        exitButton.setBounds(650, 20, 120, 30);
        exitButton.setOpaque(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));
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

   
        if (player != null) {
            player.draw(g);
        }

 
        for (Obstacle obs : obstacles) {
            obs.draw(g);
        }

   
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);

    
        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 150)); 
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String gameOverText = "GAME OVER";
            int textWidth = g.getFontMetrics().stringWidth(gameOverText);
            g.drawString(gameOverText, (getWidth() - textWidth) / 2, getHeight() / 2 - 50);

            g.setFont(new Font("Arial", Font.PLAIN, 30));
            String scoreText = "Score: " + score;
            int scoreWidth = g.getFontMetrics().stringWidth(scoreText);
            g.drawString(scoreText, (getWidth() - scoreWidth) / 2, getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            player.update();

            obstacleSpawnCounter++;
            if (obstacleSpawnCounter > 100) { 
                boolean flying = random.nextBoolean();
                obstacles.add(new Obstacle(getWidth(), flying));
                obstacleSpawnCounter = 0;
            }

      
            Iterator<Obstacle> iter = obstacles.iterator();
            while (iter.hasNext()) {
                Obstacle obs = iter.next();
                obs.update();

      
                if (!obs.scored && obs.x + obs.width < player.x) {
                    score++;
                    obs.scored = true;
                }

        
                if (obs.x + obs.width < 0) {
                    iter.remove();
                }
            }

  
            for (Obstacle obs : obstacles) {
                if (player.getBounds().intersects(obs.getBounds())) {
                    gameOver = true;
                    timer.stop();
                    break;
                }
            }
        }

        repaint();
    }

    private void restartGame() {
        player = new Player();
        obstacles.clear();
        score = 0;
        gameOver = false;
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.jump();
        } else if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
