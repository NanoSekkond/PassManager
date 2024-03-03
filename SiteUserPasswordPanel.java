import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SiteUserPasswordPanel extends JPanel {
    private boolean passwordHidden = true;
    private final String defaultPassword = "••••••••";
    private PasswordStoragerPanel parent;
    private SiteUserPassword sup;

    SiteUserPasswordPanel(SiteUserPassword sup, PasswordStoragerPanel parent) {
        this.sup = sup;
        this.parent = parent;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(0, 145));

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(sup.getSite());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.BLACK);
        add(titlePanel, BorderLayout.NORTH);

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel userLabel = new JLabel("Username: " + defaultPassword);
        userLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        JLabel passLabel = new JLabel("Password: " + defaultPassword);
        labelsPanel.add(userLabel, BorderLayout.NORTH);
        labelsPanel.add(passLabel, BorderLayout.SOUTH);
        labelsPanel.setBackground(Color.LIGHT_GRAY);
        add(labelsPanel, BorderLayout.WEST);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton button1 = Utils.newButton("Show", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordHidden) {
                    userLabel.setText("Username: " + sup.getUser());
                    passLabel.setText("Password: " + sup.getPassword());
                }
                else {
                    userLabel.setText("Username: " + defaultPassword);
                    passLabel.setText("Password: " + defaultPassword);
                }
                passwordHidden = !passwordHidden;
            }
            
        }, null);
        JButton button2 = Utils.newButton("Delete", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteConfirmation(SiteUserPasswordPanel.this);
            }
        }, null);
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.setBackground(Color.LIGHT_GRAY);
        add(buttonsPanel, BorderLayout.EAST);
        setBackground(Color.LIGHT_GRAY);
    }

    public void removeItself() {
        parent.removeSup(sup);
    }
}
