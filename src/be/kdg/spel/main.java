package be.kdg.spel;

import be.kdg.spel.controller.Controller;
import be.kdg.spel.view.SpelUI;

/**
 * Created by Rune on 4/02/2015.
 */
public class main {
    public static void main(String[] args) {
        new Controller();
        //TODO: Tijdens het testen (spelen :) ) heb ik gemerkt dat het soms te vroeg game over geeft. bijvoorbeeld als je de hele tijd naar recht doet uiteindelijk geeft
        // game over ook al kan je nog wel bewegen (naar boven in die voorbeeld)
        //TODO: beste score, en highscores moeten nog gedaan worden
    }
}
