package be.kdg.spel.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alex Buze
 * @version 1.0 4/03/2015 11:29
 */
public class SplashScreen extends JWindow {
    public SplashScreen(){
        JLabel lblSplachscreen = new JLabel(new ImageIcon(SplashScreen.class.getResource("/be/kdg/spel/resources/splash.png")));
        JLabel lblCredits = new JLabel("@2015 Rune Laenen & Alex Buze. All rights reserved.");
        JPanel splash = new JPanel();
        splash.add(lblSplachscreen, BorderLayout.NORTH);
        splash.add(lblCredits,BorderLayout.SOUTH);
        super.add(splash);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        pack();
        setVisible(true);
        try {
            Thread.sleep(3000); // 3 sec pauzeren
        } catch (InterruptedException e) {
            // doe niets
        }
        this.dispose();
    }
}
