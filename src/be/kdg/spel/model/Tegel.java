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


}
