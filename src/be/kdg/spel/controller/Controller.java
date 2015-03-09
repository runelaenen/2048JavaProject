package be.kdg.spel.controller;

import be.kdg.spel.model.*;
import be.kdg.spel.view.*;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.awt.*;


/**
 * Created by Rune on 4/02/2015.
 */
public class Controller {
    private Tegels tegels;
    private GeluidUI geluidUI;
    private SpelUI spelUI;
    private Score scores;
    private Instellingen instellingen;
    private Font fntLettertype;

    public static int ZIJDEGROOTTE = 4;
    public static int BORDGROOTTE = ZIJDEGROOTTE * ZIJDEGROOTTE;

    public static void main(String[] args) {
        new Controller();
    }

    public Controller() {
        loadFont();
        scores = new Score(this);
        this.tegels = new Tegels(this);
        spelUI = new SpelUI(this);
        geluidUI = new GeluidUI(this);


        instellingen = new Instellingen(this);

        //geluid().playMusic();
        tegels.loadGameState(); // laad bestaande tegels en scores
        spelUI.updateBest();
    }

    private void loadFont() {
        // font initialiseren
        this.fntLettertype = new Font(Font.SANS_SERIF, Font.PLAIN, 52);
        try {
            InputStream is = SpelUI.class.getResourceAsStream("/be/kdg/spel/resources/Ubuntu-R.ttf");
            this.fntLettertype = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fout bij het laden van het Ubuntu font\n Het programma is automatisch overschakeld naar het standaart font!");
        }
    }

    public Tegel[] getTegelArray() {
        return tegels.getTegelArray();
    }

    public void keyLEFT() {
        tegels.left();
        spelUI.updateSpelUI(tegels.getTegelArray());
    }

    public void keyRIGHT() {
        tegels.right();
        spelUI.updateSpelUI(tegels.getTegelArray());

    }

    public void keyUP() {
        tegels.up();
        spelUI.updateSpelUI(tegels.getTegelArray());

    }

    public void keyDOWN() {
        tegels.down();
        spelUI.updateSpelUI(tegels.getTegelArray());

    }

    public void gewonnen() {
        spelUI.gewonnen();
    }

    public void verloren() {
        spelUI.verloren();
    }

    public void opnieuw() {
        resetGameState();
        this.tegels = new Tegels(this);
        spelUI.updateBest();
        spelUI.updateSpelUI(tegels.getTegelArray());
    }

    public void addScore(int score) {
        scores.addScore(score);
        spelUI.setScore(scores.getScore());
        spelUI.updateBest();
    }

    public void setScore(int score) {
        scores.setScore(score);
        if(spelUI!=null) spelUI.setScore(scores.getScore());
        //spelUI.updateBest();
    }

    public int getScore() {
        return scores.getScore();
    }

    public void addToHighscore() {
        scores.addToHighscore();
    }

    public void addToHighscore(int scoreCheat) {
        scores.addToHighscore(scoreCheat);
    }

    public void ranglijst() {
        new RanglijstUI(this);
    }

    public void resetHighscore() {
        scores.resetHighscore();
        spelUI.updateBest();
    }

    public String[][] getHighscoreList() {
        return scores.getHighscoresList();
    }

    public int getBest() {
        return scores.getBest();
    }


    public String gebruikersnaam() {
        boolean vragen = true;
        String antwoord = "";
        while (vragen) {
            antwoord = (String) JOptionPane.showInputDialog(
                    spelUI,
                    "Wat is jouw naam?",
                    "Naam",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
            if (antwoord != null) {
                if (!antwoord.isEmpty()) {
                    vragen = false;
                }
            }
        }
        return antwoord;
    }


    public void instellingenOpslaan(Color kleur, boolean geluid, boolean muziek) {
        instellingen.instellingenOpslaan(kleur, geluid, muziek);
        instellingen.leesEnActiveerInstellingen();
    }

    public void instellingenDefault() {
        instellingen.setDefault();
        instellingen.leesEnActiveerInstellingen();
    }

    public void instellingen() {
        new InstellingenUI(this);
    }

    public GeluidUI geluid() {
        return geluidUI;
    }

    public Color getAchtergrondsKleur() {
        return spelUI.getAchtergrondsKleur();
    }

    public void setAchtergrondsKleur(Color kleur) {
        spelUI.setAchtergrondsKleur(kleur);
    }

    public Font getFont() {
        return fntLettertype;
    }

    public void newGameState() {
        tegels.newGameState();
    }

    public void saveGameState() {
        tegels.saveGameState();
    }

    public void resetGameState() {
        setScore(0);
        newGameState();
        setScore(0);
        saveGameState();
    }

    public void setTegelWaardeCheat() {
        tegels.setTegelWaardeCheat();
    }
}

