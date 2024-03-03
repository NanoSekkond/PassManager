import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPasswordPanel extends JDialog {

    private JTextField passwordField;

    public SetPasswordPanel(JFrame parent) {
        super(parent, "Set Password", true);
        Utils.setTaskbarIcon(null, this);
        setTitle("Change password");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        passwordField = new JTextField();

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                if (password.length() > 0) {
                    AppSettings.setPassword(passwordField.getText());
                    dispose();
                }
            }
        });

        JButton okButton = Utils.newButton("OK", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                if (password.length() > 0) {
                    AppSettings.setPassword(passwordField.getText());
                    dispose();
                }
            }
        }, null);

        JButton cancelButton = Utils.newButton("Cancel", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(okButton);
        panel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(parent);
        setLocation(this.getLocation().x + 50, this.getLocation().y + 50);
        setVisible(true);
    }
}