import javax.swing.*;
import java.awt.*;

public class Character {
    public ImageIcon imageIcon = new ImageIcon("res/images/idk.png");
    private int x,y;
    public  int moveSpeed = 2;
    public static boolean isAlive = true;
    public static int SIZE = 60;


    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) { this.x = x;}

    public void setY(int y) { this.y = y;}

    public void moveRight() {if (this.x - moveSpeed < Window.WIDTH - 100) this.x += moveSpeed;}

    public void moveLeft() {
        if (this.x + moveSpeed > 50) this.x -= moveSpeed;
    }

    public void moveUp() {
        if (this.y + moveSpeed > 140) this.y -= moveSpeed;
    }

    public void moveDown() {if (this.y - moveSpeed < Window.HEIGHT - 140) this.y += moveSpeed;
    }
    public Rectangle getBody(){
        return new Rectangle(x,y,SIZE,SIZE);
    }
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.drawImage(imageIcon.getImage(), x, y, SIZE, SIZE, null);
    }
}

