import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private JLabel label;
    public Menu (String text,int x, int y, int width, int height) {
        label = new JLabel(text);
        this.setBounds(x, y, width, height);
        this.add(label);
        label.setFont(new Font("idk",Font.BOLD,60));
    }
    public void setText(String newText){
        this.label.setText(newText);
    }
}