package be.kdg.spel.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alex Buze
 * @version 1.0 4/03/2015 11:29
 */
public class SplashScreenInfo extends JWindow {

    public SplashScreenInfo() {
        //labels
        JLabel lblSplashscreen = new JLabel(new ImageIcon(getClass().getResource("/be/kdg/spel/resources/splash.png")));
        JLabel lblInfo = new JLabel("Versie 2.0 \n© 2015 Rune Laenen & Alex Buze. All rights reserved.");


        //panel
        JPanel splash = new JPanel(new BorderLayout());
        splash.add(lblSplashscreen, BorderLayout.CENTER);
        splash.add(lblInfo, BorderLayout.SOUTH);
        super.add(splash);



        setSize(400, 400);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //Doe niets
        }
        this.dispose();
    }
}
