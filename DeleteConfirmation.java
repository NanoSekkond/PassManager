import javax.swing.*;
import java.awt.*;

public class DeleteConfirmation extends JDialog {

    private SiteUserPasswordPanel parent;

    public DeleteConfirmation(SiteUserPasswordPanel sup) {
        Utils.setTaskbarIcon(null, this);
        parent = sup;
        setTitle("Delete Confirmation");
        setSize(400, 135);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }

    private void createUI() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel messageLabel = new JLabel("Are you sure you want to delete this password?");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JButton okButton = Utils.newButton("Yes", e -> {
            parent.removeItself();
            dispose();
        }, null);

        JButton cancelButton = Utils.newButton("No", e -> dispose(), null);

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
