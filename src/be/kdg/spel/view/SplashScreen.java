package be.kdg.spel.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alex Buze
 * @version 1.0 4/03/2015 11:29
 */
public class SplashScreen extends JWindow {
    public SplashScreen() {

        //progressbar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMaximum(0);
        progressBar.setMaximum(100);
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.blue);

        //labels
        JLabel lblSplachscreen = new JLabel(new ImageIcon(getClass().getResource("/be/kdg/spel/resources/splash.png")));
        JLabel lblCredits = new JLabel("© 2015 Rune Laenen & Alex Buze. All rights reserved.");
        //panel
        JPanel splash = new JPanel(new BorderLayout());
        splash.add(progressBar, BorderLayout.NORTH);
        splash.add(lblSplachscreen, BorderLayout.CENTER);
        splash.add(lblCredits, BorderLayout.SOUTH);
        super.add(splash);

        setSize(400, 400);
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);

        for (int i = 1; i < 5; i++) {
            progressBar.setValue(25 * i);
            try {
                Thread.sleep(750);
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
