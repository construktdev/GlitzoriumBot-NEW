package de.construkter.gui.cp;

import de.construkter.Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 18.07.2024
 * @author
 */

public class CP extends JFrame {
    // Anfang Attribute
    private JButton bStopBot = new JButton();
    private JButton bRestartBot = new JButton();
    private JButton bCloseCPthis = new JButton();
    private JButton bRestartCPthis = new JButton();
    private JButton bSendStatusEmbed = new JButton();
    private JLabel lUtils = new JLabel();
    private JLabel lBotActions = new JLabel();
    // Ende Attribute

    public CP() {
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 593;
        int frameHeight = 444;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("CP");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        bStopBot.setBounds(5, 358, 131, 41);
        bStopBot.setText("Stop Bot");
        bStopBot.setMargin(new Insets(2, 2, 2, 2));
        bStopBot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bStopBot_ActionPerformed(evt);
            }
        });
        cp.add(bStopBot);
        bRestartBot.setBounds(149, 358, 131, 41);
        bRestartBot.setText("Restart Bot");
        bRestartBot.setMargin(new Insets(2, 2, 2, 2));
        bRestartBot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bRestartBot_ActionPerformed(evt);
            }
        });
        cp.add(bRestartBot);
        bCloseCPthis.setBounds(293, 358, 131, 41);
        bCloseCPthis.setText("Close CP (this)");
        bCloseCPthis.setMargin(new Insets(2, 2, 2, 2));
        bCloseCPthis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bCloseCPthis_ActionPerformed(evt);
            }
        });
        cp.add(bCloseCPthis);
        bRestartCPthis.setBounds(440, 358, 131, 41);
        bRestartCPthis.setText("Restart CP (this)");
        bRestartCPthis.setMargin(new Insets(2, 2, 2, 2));
        bRestartCPthis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bRestartCPthis_ActionPerformed(evt);
            }
        });
        cp.add(bRestartCPthis);
        bSendStatusEmbed.setBounds(5, 288, 131, 41);
        bSendStatusEmbed.setText("Send Status Embed");
        bSendStatusEmbed.setMargin(new Insets(2, 2, 2, 2));
        bSendStatusEmbed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bSendStatusEmbed_ActionPerformed(evt);
            }
        });
        cp.add(bSendStatusEmbed);
        lUtils.setBounds(7, 332, 110, 20);
        lUtils.setText("Utils");
        cp.add(lUtils);
        lBotActions.setBounds(7, 260, 110, 20);
        lBotActions.setText("Bot Actions");
        cp.add(lBotActions);
        // Ende Komponenten

        setVisible(true);
    } // end of public CP

    // Anfang Methoden

    public static void open() {
        new CP();
    } // end of main

    public void bStopBot_ActionPerformed(ActionEvent evt) {
        System.exit(130);
    } // end of bStopBot_ActionPerformed

    public void bRestartBot_ActionPerformed(ActionEvent evt) {
        String[] args = {""};
        Main.main(args);
    } // end of bRestartBot_ActionPerformed

    public void bCloseCPthis_ActionPerformed(ActionEvent evt) {
       dispose();

    } // end of bCloseCPthis_ActionPerformed

    public void bRestartCPthis_ActionPerformed(ActionEvent evt) {
        dispose();
        open();
    } // end of bRestartCPthis_ActionPerformed

    public void bSendStatusEmbed_ActionPerformed(ActionEvent evt) {


    } // end of bSendStatusEmbed_ActionPerformed

    // Ende Methoden
} // end of class CP
