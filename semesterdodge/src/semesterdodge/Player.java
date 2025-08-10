package semesterdodge;

import java.awt.*;

public class Player {
    int x = 100;
    int y;
    int width = 50, height = 50;
    int velocityY = 0;
    boolean jumping = false;

    final int ground = 240;
    final int gravity = 1;

    Image[] runFrames;
    Image jumpImg;
    int frameCount = 0;

    public Player() {
        y = ground - height;  

        runFrames = new Image[] {
            AssetLoader.load("res/player/run1.png"),
            AssetLoader.load("res/player/run2.png"),
            AssetLoader.load("res/player/run3.png")
        };
        jumpImg = AssetLoader.load("res/player/jump.png");
    }

    public void jump() {
        if (!jumping) {
            velocityY = -15;
            jumping = true;
        }
    }

    public void update() {
        y += velocityY;
        velocityY += gravity;

        if (y + height >= ground) {
            y = ground - height; 
            jumping = false;
            velocityY = 0;
        }
    }

    public void draw(Graphics g) {
        Image img = jumping ? jumpImg : runFrames[(frameCount++ / 10) % runFrames.length];
        g.drawImage(img, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
