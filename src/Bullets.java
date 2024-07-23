import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Bullets {
    public ImageIcon imageIcon = new ImageIcon("res/images/bullet.png");
    public int x, y, SIZE = 30;

    public boolean isHit = false;

    public Bullets(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(() -> {
            while (y > -SIZE) {
                this.y -= moveSpeed;
                try {
                    sleep(10);
                }
                catch (Exception ignored) {
                }
            }
        }).start();
    }

    public int moveSpeed = 6;

    public Rectangle getBullet() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.drawImage(imageIcon.getImage(), x, y, SIZE, SIZE, null);
    }
}
