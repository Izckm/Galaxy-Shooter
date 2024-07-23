import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.System.exit;

public class Controller implements KeyListener {
    private Character character;
    private Scene scene;
    public static boolean isLeftPressed,isRightPressed,isDownPressed,isUpPressed,isSpacePressed;
    public Controller(Character character, Scene scene) {
        this.character = character;
        this.scene = scene;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            isLeftPressed = true;

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            isRightPressed = true;

        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            isUpPressed = true;

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            isDownPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacePressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) exit(0);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            isDownPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            isUpPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            isRightPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            isLeftPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Window.started = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacePressed = false;
    }
    }
}