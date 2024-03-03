import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoUSBPanel extends JDialog {
    public NoUSBPanel(JFrame parent) {
        super(parent, "No USB Found", true);
        Utils.setTaskbarIcon(null, this);
        setTitle("No USB Found");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
    
        setLocation(this.getLocation().x + 50, this.getLocation().y + 50);

        createUI();
    
        setVisible(true);
    }
    
    private void createUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JLabel messageLabel = new JLabel("<html><center>Insert USB and try again.<br>The file name should be \"data.csv\".</center></html>");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton closeButton = Utils.newButton("Close", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, null);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        add(panel);
    }
}

