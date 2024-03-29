package be.kdg.spel.view;

import javax.swing.*;
import java.awt.*;

public class ThemaUI {
    private String naam;
    private Color achtergrondKleur;
    private Color tekstKleur;
    private Color knopKleur;


    public static Color Achtergrondkleur(int waarde) {
        switch (waarde) {
            case 0:
                return new Color(0xcdc1b4);
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
            case 4096:
                return new Color(0x000000);
            default:
                return new Color(0xCF6406);

        }
    }
}







