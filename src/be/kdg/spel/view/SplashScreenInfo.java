package be.kdg.spel.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Alex Buze
 * @version 1.0 4/03/2015 11:29
 */
public class SplashScreenInfo extends JWindow {

    public SplashScreenInfo() {
        //labels
        JLabel lblSplashscreen = new JLabel(new ImageIcon(getClass().getResource("/be/kdg/spel/resources/splash_info.png")));
        JLabel lblInfo = new JLabel("© 2015 Rune Laenen & Alex Buze. All rights reserved.");


        //panel
        JPanel splash = new JPanel(new BorderLayout());
        splash.add(lblSplashscreen, BorderLayout.CENTER);
        splash.add(lblInfo, BorderLayout.SOUTH);
        super.add(splash);



        setSize(400, 400);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        /*
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //Doe niets
        }
        this.dispose();
        */

        JWindow dit = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dit.dispose();
            }
        });
    }


}
