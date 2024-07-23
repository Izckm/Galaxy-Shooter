import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    public static final int WIDTH = 1550;
    public static final int HEIGHT = 840;
    public static boolean started = false;
    public ImageIcon imageIcon = new ImageIcon("res/images/mainMenu.png");

    public Window() {
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        Scene scene = new Scene(0, 0, WIDTH, HEIGHT);
        this.add(scene);
        this.setVisible(true);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/Faith.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }


    }
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.drawImage(imageIcon.getImage(),0, 0, Window.WIDTH, Window.HEIGHT,null);
        Menu label = new Menu("Press Enter to Start", 350, 600, 850, 100);
        Menu label2 = new Menu(" Press Esc to Exit  ", 450, 100, 650, 80);
        this.add(label);
        this.add(label2);
        label2.setBackground(Color.RED);
        label.setBackground(Color.BLUE);
        this.setVisible(true);
    }
}