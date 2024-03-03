import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class PasswordStoragerPanel extends JFrame {
    private JScrollPane mainPanel;
    private ArrayList<SiteUserPassword> supList = new ArrayList<SiteUserPassword>();

    public PasswordStoragerPanel() {
        Utils.setTaskbarIcon(this, null);
        setLayout(new BorderLayout());
        setTitle("Password Storage");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        loadArray();

        createUI();
    
        setVisible(true);
        setResizable(false);
    }

    public void addSup(SiteUserPassword sup) {
        supList.add(sup);
        loadGrid("");
    }

    public void removeSup(SiteUserPassword sup) {
        supList.remove(sup);
        loadGrid("");
    }

    private void loadArray() {
        try {
            supList = Storage.getData();
        } catch (Exception e) {

        }
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            Storage.saveData(supList);
        } catch (Exception e) {
            System.out.println("Hwllo");
        }
    }

    private void createUI() {
        JTextField searchBar = new JTextField();
        JButton changePass = Utils.newButton("Change main password", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetPasswordPanel(PasswordStoragerPanel.this);
            }
        }, null);
        JButton addButton = Utils.newButton("+", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPasswordPanel(PasswordStoragerPanel.this);
            }
        }, null);
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGrid(searchBar.getText());
                searchBar.setText("");
            }
        });
        Utils.resize(searchBar, 200, 30);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.add(addButton, BorderLayout.EAST);
        searchPanel.add(changePass, BorderLayout.WEST);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        add(searchPanel, BorderLayout.NORTH);

        loadGrid("");
    }

    private void loadGrid(String filter) {
        if (mainPanel != null) {
            getContentPane().remove(mainPanel);
        }
        
        JPanel gridPanel = new JPanel(new GridLayout(0, 4, 10, 10));

        int count = 0;

        for (SiteUserPassword sup : checkMatch(filter)) {
            gridPanel.add(new SiteUserPasswordPanel(sup, this));
            count++;
        }

        int missing = 13 - count;

        while (missing > 0) {
            gridPanel.add(Utils.blank());
            missing--;
        }

        mainPanel = new JScrollPane(gridPanel);
        mainPanel.getVerticalScrollBar().setUnitIncrement(12);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private ArrayList<SiteUserPassword> checkMatch(String filter) {
        ArrayList<SiteUserPassword> res = new ArrayList<>(supList);
        res.removeIf(new Predicate<SiteUserPassword>() {
            @Override
            public boolean test(SiteUserPassword t) {
                Scanner sc = new Scanner(t.getSite());
                String match = sc.findInLine("(?i)" + filter);
                sc.close();
                return match == null;
            }
        });
        return res;
    }
}
