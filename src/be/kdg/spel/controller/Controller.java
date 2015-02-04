package be.kdg.spel.controller;

import be.kdg.spel.model.*;
import be.kdg.spel.view.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by Rune on 4/02/2015.
 */
public class Controller {
    public static int ZIJDEGROOTTE = 4;
    public static int BORDGROOTTE = ZIJDEGROOTTE*ZIJDEGROOTTE;

    public static void main(String[] args) {
        Tegel[] tegels = new Tegel[BORDGROOTTE];
        Random random = new Random();

        int eersteGetal, tweedeGetal;
        eersteGetal = random.nextInt(BORDGROOTTE);
        tweedeGetal = random.nextInt(BORDGROOTTE);
        for(int i = 0; i<BORDGROOTTE; i++){
            tegels[i] = new Tegel();

            if(i==eersteGetal || i==tweedeGetal){
                tegels[i].setWaarde(2);
            }
        }

        new SpelUI(tegels);
    }


}

