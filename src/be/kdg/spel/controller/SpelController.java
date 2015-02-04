package be.kdg.spel.controller;

import be.kdg.spel.model.*;
import be.kdg.spel.view.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class SpelController {
    public static final int BORDGROOTTE = 4;

    public static void main(String[] args) {
        new SpelUI(new Tegel[BORDGROOTTE]);

    }
}

