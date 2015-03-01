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
    private GeluidUI geluidUI;
    private SpelUI spelUI;
    private Score scores;
    private Instellingen instellingen;

    public static int ZIJDEGROOTTE = 4;
    public static int BORDGROOTTE = ZIJDEGROOTTE*ZIJDEGROOTTE;

    public static void main(String[] args) {
        new Controller();
    }

    public Controller() {
        this.tegels = new Tegels(this);
        geluidUI = new GeluidUI(this);
        spelUI = new SpelUI(this);
        scores = new Score(this);
        instellingen = new Instellingen(this);

        //geluid().playMusic();
        tegels.loadGameState(); // laad bestaande tegels en scores

        spelUI.updateBest();
    }

    public Tegel[] getTegelArray() {
        return tegels.getTegelArray();
    }

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

    public void gewonnen(){
        spelUI.gewonnen();
    }
    public void verloren(){
        spelUI.verloren();
    }
    public void opnieuw(){
        resetGameState();
        this.tegels = new Tegels(this);

        spelUI.updateBest();
        spelUI.updateSpelUI(tegels.getTegelArray());
    }
    public void addScore(int score){
        scores.addScore(score);
        spelUI.setScore(scores.getScore());
        spelUI.updateBest();
    }
    public void setScore(int score){
        scores.setScore(score);
        spelUI.setScore(scores.getScore());
        spelUI.updateBest();
    }
    public int getScore() { return scores.getScore(); }
    public void addToHighscore(){
        scores.addToHighscore();
    }
    public void ranglijst(){
        new RanglijstUI(this);
    }
    public void resetHighscore() {
        scores.resetHighscore();
        spelUI.updateBest();
    }
    public String[][] getHighscoreList(){
        return scores.getHighscoresList();
    }
    public int getBest() {
        return scores.getBest();
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


    public void instellingenOpslaan(Color kleur, boolean geluid, boolean muziek) {
        instellingen.instellingenOpslaan(kleur, geluid, muziek);
        instellingen.leesEnActiveerInstellingen();
    }
    public void instellingenDefault() {
        instellingen.setDefault();
        instellingen.leesEnActiveerInstellingen();
    }
    public void instellingen(){
        new InstellingenUI(this);
    }

    public GeluidUI geluid(){
        return geluidUI;
    }

    public Color getAchtergrondsKleur(){
        return spelUI.getAchtergrondsKleur();
    }
    public void setAchtergrondsKleur(Color kleur){
        spelUI.setAchtergrondsKleur(kleur);
    }
    public Font getFont(){
        return spelUI.getFont();
    }

    public void newGameState() {
        tegels.newGameState();
    }
    public void loadGameState() {
        tegels.loadGameState();
    }
    public void saveGameState() {
        tegels.saveGameState();
    }
    public void resetGameState(){
        setScore(0);
        newGameState();
        setScore(0);
        saveGameState();
    }
}

