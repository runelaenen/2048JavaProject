package be.kdg.spel.model;

import java.awt.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class Tegel {
    private int waarde;

    public Tegel() {
        this(0);
    }

    public Tegel(int num) {
        waarde = num;
    }

    public int getWaarde() {
        return waarde;
    }

    public void setWaarde(int waarde) {
        this.waarde = waarde;
    }

    public boolean isLeeg() {
        return waarde == 0;
    }

    public Color tekstKleur() {
        if(waarde < 16) {
            return new Color(0x776e65);
        } else {
            return new Color(0xf9f6f2);
        }
    }

    public Color Achtergrondkleur() {
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
}
