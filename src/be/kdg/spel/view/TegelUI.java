package be.kdg.spel.view;

import javax.swing.*;
import be.kdg.spel.model.Tegel;

import java.awt.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class TegelUI extends JLabel {

    public TegelUI(Tegel tegel){
        if(!tegel.isLeeg()) {
            super.setText(String.valueOf(tegel.getWaarde()));
        } else {
            super.setText("");
        }

        super.setBackground(tegel.Achtergrondkleur());
        super.setForeground(tegel.tekstKleur());

        super.setOpaque(true);
        super.setHorizontalAlignment(JLabel.CENTER);
        super.setVerticalAlignment(JLabel.CENTER);

        Font fLabel = new Font(Font.SANS_SERIF, Font.PLAIN, 32);
        super.setFont(fLabel);
    }
}
