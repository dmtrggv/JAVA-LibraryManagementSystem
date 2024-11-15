package kravax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSignin extends Panels implements ActionListener {

    private static boolean isLoggedIn = false;

    JButton buttLogin = new JButton("Влез");
    JButton buttClose = new JButton("Спри програмата");
    JTextField txUsername = new JTextField();
    JPasswordField txPassword = new JPasswordField();
    JLabel creator = new JLabel("Created by Dimitar Gogov, 2101261032");
    JLabel copyright = new JLabel("THE BEST PROGRAM EVER v1.0.0 © Copyright 2024");

    public PanelSignin() {

        // Create frame
        setPanelExist(true);
        JFrame frame = new JFrame("Влез в профил");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 350, 400);
        frame.setResizable(false);

        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);

        // Username
        JLabel usernameLabel = new JLabel("Име:");
        usernameLabel.setBounds(20, 40, 100, 30);
        txUsername.setBounds(20, 65, 300, 35);
        txUsername.setFont(new Font("Sans Serif", 0, 15));
        txUsername.setFocusable(true);

        // Password
        JLabel passwordLabel = new JLabel("Парола:");
        passwordLabel.setBounds(20, 100, 100, 30);
        txPassword.setBounds(20, 125, 300, 35);
        txPassword.setFont(new Font("Sans Serif", 0, 15));

        // Login button
        buttLogin.setBounds(17, 210, 300, 40);  // x, y, width, height
        buttLogin.setFocusable(false);
        buttLogin.addActionListener(this);
        buttLogin.setFocusable(true);

        // Close button
        buttClose.setBounds(17, 260, 300, 40);  // x, y, width, height
        buttClose.setFocusable(false);
        buttClose.addActionListener(this);
        buttClose.setFocusable(true);

        // Creator
        creator.setFont(new Font("Sans Serif", Font.BOLD, 12));
        creator.setForeground(Color.gray);

        // Copyright
        copyright.setFont(new Font("Sans Serif", Font.BOLD, 12));
        copyright.setForeground(Color.gray);

        // Set panel
        panel.add(usernameLabel);
        panel.add(txUsername);
        panel.add(passwordLabel);
        panel.add(txPassword);
        panel.add(buttLogin);
        panel.add(buttClose);
        panel.add(creator);
        panel.add(copyright);
        frame.add(panel);
        frame.setVisible(true);
        updateCreatorPosition(panel);
    }

    private void updateCreatorPosition(JPanel panel) {

        int pW = panel.getWidth();
        int pH = panel.getHeight();
        int cW = creator.getPreferredSize().width;
        int cpW = copyright.getPreferredSize().width;

        // Change creator position
        creator.setBounds(((pW - cW) / 2) - 5, pH - 40, cW * 2, 15);
        copyright.setBounds(((pW - cpW) / 2) - 5, pH - 25, cpW * 2, 15);

        // Refresh
        panel.revalidate();
        panel.repaint();
    }

    //region Login status

    public static void setLoginStatus(boolean status) {
        isLoggedIn = status;
    }

    public static boolean getLoginStatus() {
        return isLoggedIn;
    }

    //endregion

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttLogin) {

            if (txUsername.getText().equals("admin") && new String(txPassword.getPassword()).equals("pass")) {

                // Login is successful
                setLoginStatus(true);
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(buttLogin);
                topFrame.dispose();
                new PanelMain();

            } else {

                // Not valid login
                JOptionPane.showMessageDialog(
                        null,
                        "Греяни данни, пробвай с:\n" +
                                "име: admin\n" +
                                "парола: pass"
                );

            }

        } else if (e.getSource() == buttClose) {

            // Close program
            System.exit(0);

        }
    }
}
