package be.kdg.spel.model;

import be.kdg.spel.controller.Controller;

import java.io.FileNotFoundException;
import java.util.StringTokenizer;

/**
 * Created by Rune on 4/02/2015.
 */
public class Score {


    private int score;
    private int best;
    private BestandsSysteem bs;
    private Controller controller;

    public Score(Controller controller) {
        this.bs = new BestandsSysteem();
        this.controller = controller;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void addToHighscore() {
        try {
            String gebruikersnaam = controller.gebruikersnaam();

            //String bestand = bs.lees("highscores");
            String inhoud = gebruikersnaam + ";" + getScore();
            bs.schrijf("highscores.txt", inhoud);

        } catch (FileNotFoundException e) {
            //TODO: add file exception error shizzle
        }
    }

    public void setBest(int best) {
        this.best = best;
    }

    public int getBest() {
        try {
            int best = 0;

            String bestand = bs.lees("highscores.txt");
            StringTokenizer st = new StringTokenizer(bestand, "\n");
            while(st.hasMoreTokens()){
                String regel = st.nextToken();
                if(!regel.isEmpty()){
                    String[] regelArray = regel.split("[;]");
                    if(Integer.parseInt(regelArray[1]) > best){
                        best = Integer.parseInt(regelArray[1]);
                    }
                }
            }
            return best;
        } catch (FileNotFoundException e) {
            //TODO: add file exception error shizzle
        }
        return 0;
    }
}
