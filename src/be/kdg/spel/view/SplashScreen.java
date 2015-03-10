package be.kdg.spel.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Alex Buze
 * @version 1.0 4/03/2015 11:29
 */
public class SplashScreen extends JWindow {
    public SplashScreen() {

        //progressbar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.BLACK);
        progressBar.setStringPainted(true);
        progressBar.setString("© 2015 Rune Laenen & Alex Buze. All rights reserved.");

        //labels
        JLabel lblSplashscreen = new JLabel(new ImageIcon(getClass().getResource("/be/kdg/spel/resources/splash.png")));

        //panel
        JPanel splash = new JPanel(new BorderLayout());
        splash.add(lblSplashscreen, BorderLayout.CENTER);
        splash.add(progressBar, BorderLayout.SOUTH);
        super.add(splash);

        setSize(400, 400);
        pack();
        setLocationRelativeTo(null);
        //setAlwaysOnTop(true);
        setVisible(true);

        for (int i = 1; i < 21; i++) {
            progressBar.setValue(5 * i);
            try {
                Thread.sleep((int) ((Math.random() * 101) + 50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {

        }
        this.dispose();
    }
}
