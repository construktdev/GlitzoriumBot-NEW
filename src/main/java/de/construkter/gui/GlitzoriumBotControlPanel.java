package de.construkter.gui;

import de.construkter.gui.cp.CP;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.OutputStream;


public class GlitzoriumBotControlPanel extends JFrame {
    private JLabel l_AdminLogin = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private JButton b_submit = new JButton();
    private JLabel lUsername = new JLabel();
    private JLabel lPasswort = new JLabel();

    public GlitzoriumBotControlPanel() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 281;
        int frameHeight = 300;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Glitzorium-Bot Konsole");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        l_AdminLogin.setBounds(43, 8, 190, 52);
        l_AdminLogin.setText("Admin Login");
        l_AdminLogin.setFont(new Font("Dialog", Font.BOLD, 26));
        cp.add(l_AdminLogin);
        jTextField1.setBounds(14, 95, 238, 36);
        cp.add(jTextField1);
        jTextField2.setBounds(14, 159, 238, 36);
        cp.add(jTextField2);
        b_submit.setBounds(13, 206, 243, 41);
        b_submit.setText("Login");
        b_submit.setMargin(new Insets(2, 2, 2, 2));
        b_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                b_submit_ActionPerformed(evt);
            }
        });
        cp.add(b_submit);
        lUsername.setBounds(14, 70, 110, 20);
        lUsername.setText("Username");
        cp.add(lUsername);
        lPasswort.setBounds(14, 134, 110, 20);
        lPasswort.setText("Passwort");
        cp.add(lPasswort);

        setVisible(true);
    }

    public static void main(String[] args) {
        new GlitzoriumBotControlPanel();
    }

    public void b_submit_ActionPerformed(ActionEvent evt) {
        String username = jTextField1.getText();
        String password = jTextField2.getText();
        if (!username.equals("admin")) {
            lUsername.setText("Falscher Benutzername!");
        } else {
            if (!password.equals("glitzorium")) {
                lPasswort.setText("Falsches Passwort");
            } else {
                CP.open();
                dispose();
            }
        }
    }
}
