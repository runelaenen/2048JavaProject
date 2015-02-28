package be.kdg.spel.controller;

import be.kdg.spel.model.*;
import be.kdg.spel.view.*;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by Rune on 4/02/2015.
 */
public class Controller {
    private Tegels tegels;
    private SpelUI spelUI;
    private Score scores;
    private InstellingenUI instellingenUI;
    private RanglijstUI ranglijstUI;

    private int gebruikteThema;

    public static int ZIJDEGROOTTE = 4;
    public static int BORDGROOTTE = ZIJDEGROOTTE*ZIJDEGROOTTE;

    public Controller() {
        this.tegels = new Tegels(this);
        spelUI = new SpelUI(this);
        scores = new Score(this);
        gebruikteThema = 1;

        spelUI.updateBest();
    }

    public Tegel[] getTegelArray() {
        return tegels.getTegelArray();
    }

    public void addScore(int score){
        scores.addScore(score);
        spelUI.setScore(scores.getScore());
    }

    public void setScore(int score){
        scores.setScore(score);
        spelUI.setScore(scores.getScore());
    }


    public int getScore() { return scores.getScore(); }

    public void keyLEFT(){
        tegels.left();
        spelUI.updateSpelUI(tegels.getTegelArray());
    }

    public void keyRIGHT(){
        tegels.right();
        spelUI.updateSpelUI(tegels.getTegelArray());

    }

    public void keyUP(){
        tegels.up();
        spelUI.updateSpelUI(tegels.getTegelArray());

    }

    public void keyDOWN(){
        tegels.down();
        spelUI.updateSpelUI(tegels.getTegelArray());

    }
    public void instellingen(){
        instellingenUI = new InstellingenUI(this);

    }

    public void gewonnen(){
        spelUI.gewonnen();
    }
    public void verloren(){
        spelUI.verloren();
    }

    public void opnieuw(){
        this.tegels = new Tegels(this);
        scores.addToHighscore();
        spelUI.updateBest();
        setScore(0);
        spelUI.updateSpelUI(tegels.getTegelArray());
    }

    public String gebruikersnaam(){
        return (String)JOptionPane.showInputDialog(
                spelUI,
                "Wat is jouw naam?",
                "Naam",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
    }

    public Font getFont(){
        return spelUI.getFont();
    }


    public void opslaan(Color kleur) {

        JOptionPane.showConfirmDialog(null, "Je instellingen zijn opgeslagen!", "Opslaan gelukt!", JOptionPane.CLOSED_OPTION);
        spelUI.setAchtergrondsKleur(kleur);
        spelUI.refreshBackground();


    }
    public void ranglijst(){
        new RanglijstUI(this);
    }
    public Color getAchtergrondsKleur(){
        return spelUI.getAchtergrondsKleur();
    }

    public int getBest() {
        return scores.getBest();
    }
    public String[][] getHighscoreList(){
        return scores.getHighscoresList();
    }

    public void resetHighscore() {
        scores.resetHighscore();
        spelUI.updateBest();
    }
}

