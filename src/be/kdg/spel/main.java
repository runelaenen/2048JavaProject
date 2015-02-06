package be.kdg.spel;

import be.kdg.spel.controller.Controller;
import be.kdg.spel.view.SpelUI;

/**
 * Created by Rune on 4/02/2015.
 */
public class main {
    public static void main(String[] args) {
        new Controller();
    }

    //problemen opgemerkt
    // 1) als je focus op een knop zet door er op te klikken kan je niet meer met de pijltjes het spel spelen
    // 2) soms als alle vakjes naar een kant moeten bv allemaal naar boven blijft er zoms een leeg vakje er tussen wat niet de bedoeling is
    // 3) het spel lijkt precies nooit te eindigen( bord geraakt precies niet vol)

}
