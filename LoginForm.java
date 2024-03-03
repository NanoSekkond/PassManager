import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginForm extends JFrame {
    private JPasswordField passwordField;
    
    public LoginForm() {
        Utils.setTaskbarIcon(this, null);
        setTitle("Password Manager Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        createUI();
    
        setVisible(true);
        setResizable(false);
        checkPassword();
    }
    
    private void createUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Enter Master Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
    
        passwordField = new JPasswordField();
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });

        JButton loginButton = Utils.newButton("Login", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        }, null);
    
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        inputPanel.add(passwordField);
        inputPanel.add(loginButton);
    
        panel.add(inputPanel, BorderLayout.CENTER);
        add(panel);
    }

    private void Login() {
        String pass = AppSettings.getPassword();
        char[] passwordChars = passwordField.getPassword();
        String password = "";
        try {
            password = Storage.calculateSHA256(String.valueOf(passwordChars));
        } catch (Exception e) {
            dispose();
        }
        if (Storage.checkFile()) {
            if (password.equals(pass)) {
                new PasswordStoragerPanel();
                dispose();
            }
            else {
                new WrongPasswordPanel(this);
            }
        }
        else {
            new NoUSBPanel(this);
        }
    }

    private void checkPassword() {
        if (AppSettings.isPasswordSet()) {
            return;
        }
        else {
            new SetPasswordPanel(this);
        }
    }
}