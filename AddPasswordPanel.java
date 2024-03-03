import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddPasswordPanel extends JDialog {
    private PasswordStoragerPanel parent;
    private JTextField siteInput, userInput, passInput;

    public AddPasswordPanel(PasswordStoragerPanel parent) {
        super(parent, "AddPassword", true);
        Utils.setTaskbarIcon(null, this);
        this.parent = parent;
        setTitle("Add Password");
        setSize(450, 225);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        createUI();
    
        setVisible(true);
    }
    
    private void createUI() {

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Site name:"));
        siteInput = new JTextField();
        panel.add(siteInput);

        panel.add(new JLabel("Username:"));
        userInput = new JTextField();
        panel.add(userInput);

        panel.add(new JLabel("Password:"));
        passInput = new JTextField();
        panel.add(passInput);

        siteInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInput.requestFocus();
            }  
        });

        userInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passInput.requestFocus();
            }  
        });

        passInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSup();
                dispose();
            }  
        });

        JButton okButton = Utils.newButton("Add", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSup();
                dispose();
            }
        }, null);

        JButton cancelButton = Utils.newButton("Cancel", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveSup() {
        String siteString = siteInput.getText();
        String userString = userInput.getText();
        String passString = passInput.getText();
        if (siteString.isEmpty() || userString.isEmpty() || passString.isEmpty()) {
            return;
        }
        parent.addSup(new SiteUserPassword(siteString, userString, passString));
    }
}