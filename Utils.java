import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Utils {
    public static JButton newButton(String text, ActionListener actionListener, Dimension absoluteSize) {
        JButton res = new JButton(text);
        res.setFocusPainted(false);
        res.addActionListener(actionListener);
        if (absoluteSize != null) {
            res.setPreferredSize(absoluteSize);
            res.setMinimumSize(absoluteSize);
            res.setMaximumSize(absoluteSize);
        }
        return res;
    }

    public static JPanel blank() {
        return new JPanel();
    }

    public static void resize(Component comp, int x, int y) {
        Dimension absoluteSize = new Dimension(x, y);
        comp.setPreferredSize(absoluteSize);
        comp.setMinimumSize(absoluteSize);
        comp.setMaximumSize(absoluteSize);
    }

    public static void setTaskbarIcon(JFrame frame1, JDialog frame2) {
        URL iconURL = Utils.class.getResource("Icon.png");
        ImageIcon icon = new ImageIcon(iconURL);

        if (frame1 == null) {
            frame2.setIconImage(icon.getImage());
        }
        else if (frame2 == null) {
            frame1.setIconImage(icon.getImage());
        }
    }
}
