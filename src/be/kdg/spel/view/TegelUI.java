package be.kdg.spel.view;

import javax.swing.*;
import be.kdg.spel.model.Tegel;

import java.awt.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class TegelUI extends JLabel {

    public TegelUI(Tegel tegel){
        if(tegel.getWaarde()!=0) {
            super.setText(String.valueOf(tegel.getWaarde()));
        } else {
            super.setText("");
        }

        switch (tegel.getWaarde()) {
            case 0: super.setBackground(new Color(0xccc0b3)); break;
            case 2: super.setBackground(new Color(0xeee4da)); break;
            case 4: super.setBackground(new Color(0xede0c8)); break;
            case 8:    super.setBackground(new Color(0xf2b179)); break;
            case 16:   super.setBackground(new Color(0xf59563)); break;
            case 32:   super.setBackground(new Color(0xf67c5f)); break;
            case 64:   super.setBackground(new Color(0xf65e3b)); break;
            case 128:  super.setBackground(new Color(0xedcf72)); break;
            case 256:  super.setBackground(new Color(0xedcc61)); break;
            case 512:  super.setBackground(new Color(0xedc850)); break;
            case 1024: super.setBackground(new Color(0xedc53f)); break;
            case 2048: super.setBackground(new Color(0xedc22e)); break;
        }


        super.setOpaque(true);
        super.setHorizontalAlignment(JLabel.CENTER);
        super.setVerticalAlignment(JLabel.CENTER);

        Font fLabel = new Font(Font.MONOSPACED, Font.PLAIN, 25);
        super.setFont(fLabel);
    }
}
