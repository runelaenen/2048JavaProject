package be.kdg.spel.view;

import javax.swing.*;

import be.kdg.spel.controller.Controller;
import be.kdg.spel.model.Tegel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Rune on 4/02/2015.
 */
public class TegelUI extends JLabel {

    public TegelUI(Tegel tegel, Controller controller){
        if(!tegel.isLeeg()) {
            super.setText(String.valueOf(tegel.getWaarde()));
        } else {
            super.setText("");
        }

        super.setBackground(ThemaUI.Achtergrondkleur(tegel.getWaarde()));
        super.setForeground(tegel.tekstKleur());

        super.setOpaque(true);
        super.setHorizontalAlignment(JLabel.CENTER);
        super.setVerticalAlignment(JLabel.CENTER);


        super.setFont(controller.getFont().deriveFont(32f));
    }
}
