package semesterdodge;

import java.awt.*;

public class Obstacle {
    int x, y;
    int width = 40, height = 40;
    int speed = 5;
    boolean flying;
    boolean scored = false; 

    final int ground = 240;
    Image image;

    public Obstacle(int x, boolean flying) {
        this.x = x;
        this.flying = flying;

        // Load image
        image = AssetLoader.load("res/obstacles/obj2.jpg");
        if (image == null) {
            System.err.println("⚠️ Failed to load obstacle image.");
        }

        if (flying) {
            this.y = ground - height - 60; 
        } else {
            this.y = ground - height; 
        }
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        if (image != null && g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g.create();
            Shape circle = new java.awt.geom.Ellipse2D.Float(x, y, width, height);
            g2d.setClip(circle);
            g2d.drawImage(image, x, y, width, height, null);
            g2d.setClip(null);
            g2d.setColor(Color.YELLOW);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(circle);
            g2d.dispose();
        } else {
            g.setColor(flying ? Color.RED : Color.BLACK);
            g.fillOval(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
