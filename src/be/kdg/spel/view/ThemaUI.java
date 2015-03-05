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
            case 2: return new Color(0xeee4da);
            case 4: return new Color(0xede0c8);
            case 8: return new Color(0xf2b179);
            case 16: return new Color(0xf59563);
            case 32: return new Color(0xf67c5f);
            case 64: return new Color(0xf65e3b);
            case 128: return new Color(0xedcf72);
            case 256: return new Color(0xedcc61);
            case 512: return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }

    public ThemaUI(String naam,Color achtergrondKleur, Color tekstKleur, Color knopKleur) {
        this.naam= naam;
        this.achtergrondKleur = achtergrondKleur;
        this.tekstKleur = tekstKleur;
        this.knopKleur = knopKleur;

    }

    public Color getAchtergrondKleur() {
        return achtergrondKleur;

    }

    public Color getKnopKleur() {
        return knopKleur;
    }

    public Color getTekstKleur() {
        return tekstKleur;

    }

    public String getNaam() {
        return naam;
    }

}