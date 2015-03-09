package be.kdg.spel.model;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.*;

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

            String inhoud = gebruikersnaam + ";" + getScore();
            bs.schrijf("highscores.txt", inhoud);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Er is een fout opgetreden bij het wegschrijven van uw highscores! Het spijt ons zeer !");
        }
    }
    public void addToHighscore(int scoreCheat){
        String gebruikersnaam = "Valsspeler";

        String inhoud = gebruikersnaam + ";" + scoreCheat;
        try {
            bs.schrijf("highscores.txt", inhoud);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Er is een fout opgetreden bij het wegschrijven van uw highscores! Het spijt ons zeer !");
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
                if(regel != ""){
                    String[] regelArray = regel.split("[;]");
                    if(Integer.parseInt(regelArray[1]) > best){
                        best = Integer.parseInt(regelArray[1]);
                    }
                }
            }
            return best;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Er is een fout opgetreden bij het inlezen van uw highscores! Gelieve het spel opnieuw op te starten!");
        }
        return 0;
    }

    public String[][] getHighscoresList(){
        List<String[]> uitbestand = new ArrayList<String[]>();
        try {
            String bestand = bs.lees("highscores.txt");

            StringTokenizer st = new StringTokenizer(bestand, "\n");

            while(st.hasMoreTokens()){

                String regel = st.nextToken();
                if (!regel.isEmpty()) {
                    uitbestand.add(regel.split("[;]"));
                }

            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Er is een fout opgetreden bij het inlezen van uw highscores! Gelieve het spel opnieuw op te starten!");

        }

        String[][] highscore = new String[uitbestand.size()][2];
        uitbestand.toArray(highscore);

        Arrays.sort(highscore, new Comparator<String[]>() {
            @Override
            public int compare(final String[] een, final String[] twee) {
                int score1 = Integer.parseInt(een[1]);
                int score2 = Integer.parseInt(twee[1]);
                return (score1 > score2 ? -1 : 1);
            }
        });



        return highscore;
    }

    public void resetHighscore() {
        try {
            bs.maakLeeg("highscores.txt");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Er is een fout opgetreden bij het resetten van uw highscore! Gelieve het spel opnieuw op te starten en nog eens te proberen!");

        }
    }
}
