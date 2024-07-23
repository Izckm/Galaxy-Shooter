import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Scene extends JPanel {
    public ImageIcon imageIcon = new ImageIcon("res/images/background.png");
    boolean gameOver = false;
    public ImageIcon imageIcon2 = new ImageIcon("res/images/Expolsion.gif");
    private Character character;

    private int cooldown = 25, gameScore = 0;
    private static final int ENEMIES_COUNT = 7, ENEMIES2_COUNT = 8, KILLS_FOR_WIN = 5;
    private int space, space2;
    private ArrayList<Enemy> enemies, enemies2;
    private ArrayList<Bullets> bullets;

    public Scene(int x, int y, int width, int height) {
        setVisible(true);
        this.character = new Character(700, 700);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new Controller(this.character, this));

        new Thread(() -> {
            while (!Window.started) {
                try {
                    sleep(100);
                } catch (Exception e) {
                }
            }
            this.setBounds(x, y, width, height);
            this.enemies = new ArrayList<>();
            this.enemies2 = new ArrayList<>();
            this.bullets = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < ENEMIES_COUNT; i++) {
                Enemy enemy = new Enemy(100 + space, random.nextInt(-800, -Enemy.SIZE));
                space += 200;
                enemies.add(enemy);
            }
            for (int i = 0; i < ENEMIES2_COUNT; i++) {
                Enemy enemy2 = new Enemy(40 + space2, random.nextInt(-800, -Enemy.SIZE2));
                space2 += 200;
                enemies2.add(enemy2);
            }
            mainGameLoop();
            try {
                sleep(100);
            } catch (Exception e) {
            }
            createEnemies();

        }).start();

    }

    public void createEnemies() {
        new Thread(() -> {
            while (Character.isAlive) {
                if (enemies.size() < ENEMIES_COUNT) {
                    Random random = new Random();
                    Enemy enemy = new Enemy(random.nextInt(30, 1430), -Enemy.SIZE);
                    enemies.add(enemy);
                }
                if (enemies2.size() < ENEMIES2_COUNT) {
                    Random random = new Random();
                    Enemy enemy = new Enemy(random.nextInt(50, 1300), -Enemy.SIZE2);
                    enemies2.add(enemy);
                }
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(imageIcon.getImage(), 0, 0, Window.WIDTH, Window.HEIGHT, null);
        this.character.paint(g);

        for (int i = 0; enemies!= null && i < enemies.size() ; i++) {
            enemies.get(i).paint(g);
        }
        for (int i = 0; enemies2!= null && i < enemies2.size() ; i++) {
            enemies2.get(i).paint2(g);
        }
        for (Bullets bullet : this.bullets) {
            bullet.paint(g);
        }
    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {

                if(!character.isAlive) gameOver = true;
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    enemy.y += enemy.moveSpeed;
                    if (collision(character.getBody(), enemy.getBody()) && Character.isAlive) {
                        Character.isAlive = false;
                        character.moveSpeed = 0;
                        Character.SIZE = 200;
                        character.imageIcon = imageIcon2;
                    }
                    if (enemy.y > Window.HEIGHT) {
                        this.enemies.remove(enemy);
                        i--;
                    }
                }
                for (int i = 0; i < enemies2.size(); i++) {
                    Enemy enemy = enemies2.get(i);
                    enemy.y += enemy.moveSpeed;
                    if (collision(character.getBody(), enemy.getBody2()) && character.isAlive == true) {
                        character.isAlive = false;
                        character.moveSpeed = 0;
                        character.SIZE = 200;
                        character.imageIcon = imageIcon2;
                    }
                    if (enemy.y > Window.HEIGHT) {
                        this.enemies2.remove(enemy);
                        i--;
                    }
                }


                for (int i = 0; i < enemies2.size(); i++) {
                    Enemy enemy = enemies2.get(i);
                    for (int j = 0; j < bullets.size(); j++) {
                        Bullets bullet = bullets.get(j);
                        if (collision(bullet.getBullet(), enemy.getBody2())) {
                            this.enemies2.remove(enemy);
                            this.bullets.remove(bullet);
                            i--;
                            j--;
                        }
                    }
                }
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    for (int j = 0; j < bullets.size(); j++) {
                        Bullets bullet = bullets.get(j);
                        if (collision(bullet.getBullet(), enemy.getBody())) {
                            this.bullets.remove(bullet);
                            i--;
                            j--;
                        }
                    }
                }
                try {
                    sleep(16);
                } catch (Exception e) {
                }
                repaint();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                this.cooldown--;
                this.gameScore++;
                if (Controller.isSpacePressed) this.firedBullet();
                if (Controller.isLeftPressed) this.character.moveLeft();
                if (Controller.isUpPressed) this.character.moveUp();
                if (Controller.isDownPressed) this.character.moveDown();
                if (Controller.isRightPressed) this.character.moveRight();
                try {
                    sleep(8);
                } catch (Exception e) {
                }
            }
        }).start();
        new Thread(() -> {
            try {
                sleep(400);
            } catch (Exception e) {
            }
            Menu label2 = new Menu("a", 300, 600, 1000, 100);
            this.add(label2);
            label2.setBackground(Color.YELLOW);
            while (!gameOver) {

                try {
                    sleep(100);
                } catch (Exception e) {
                }
                label2.setText("Score: " + gameScore);

            }
        }).start();
    }

    public boolean collision(Rectangle x1, Rectangle x2) {
        return x1.intersects(x2);
    }

    public void firedBullet() {
        if (this.cooldown < 0 && character.isAlive) {
            Bullets bullet = new Bullets(character.getX() + 15, character.getY() - 20);
            bullets.add(bullet);
            this.cooldown = 100;
        }

    }
}