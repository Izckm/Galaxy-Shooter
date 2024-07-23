import javax.swing.*;
import java.awt.*;

public class Enemy {
    private ImageIcon imageIcon = new ImageIcon("res/images/idk2.png");
    private ImageIcon imageIcon2 = new ImageIcon("res/images/idk3.png");
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x,y;

    public int moveSpeed = 4;
    public static final int SIZE = 120;
    public static final int SIZE2 = 60;
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.drawImage(imageIcon.getImage(),x,y,SIZE,SIZE,null);
    }
    public void paint2(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.drawImage(imageIcon2.getImage(),x,y,SIZE2,SIZE2,null);
    }
    public Rectangle getBody() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
    public Rectangle getBody2() {
        return new Rectangle(x, y, SIZE2, SIZE2);
    }
    }
