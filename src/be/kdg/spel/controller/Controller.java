package be.kdg.spel.controller;

import be.kdg.spel.model.*;
import be.kdg.spel.view.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class Controller {
    private Tegels tegels;
    private SpelUI spelUI;
    private Score scores;
    private InstellingenUI instellingenUI;

    public static int ZIJDEGROOTTE = 4;
    public static int BORDGROOTTE = ZIJDEGROOTTE*ZIJDEGROOTTE;

    public Controller(){
        this.tegels = new Tegels(this);
        spelUI = new SpelUI(tegels.getTegelArray(), this);
        scores = new Score();
    }

    public void addScore(int score){
        scores.addScore(score);
        spelUI.setScore(scores.getScore());
    }
    public int getScore() { return scores.getScore(); }

    public void keyLEFT(){
        tegels.left();
        spelUI.updateSpelUI(tegels.getTegelArray());
        if(tegels.isgewonnen()){
            spelUI.gewonnen();
        }
    }

    public void keyRIGHT(){
        tegels.right();
        spelUI.updateSpelUI(tegels.getTegelArray());
        if(tegels.isgewonnen()){
            spelUI.gewonnen();
        }
    }

    public void keyUP(){
        tegels.up();
        spelUI.updateSpelUI(tegels.getTegelArray());
        if(tegels.isgewonnen()){
            spelUI.gewonnen();
        }
    }

    public void keyDOWN(){
        tegels.down();
        spelUI.updateSpelUI(tegels.getTegelArray());
        if(tegels.isgewonnen()){
            spelUI.gewonnen();
        }
    }
    public void instellingen(){
        instellingenUI = new InstellingenUI(this);

    }


    public void opslaan(Color kleur) {

        JOptionPane.showConfirmDialog(null, "Je instellingen zijn opgeslagen!", "Opslaan gelukt!", JOptionPane.CLOSED_OPTION);
        spelUI.setAchtergrondsKleur(kleur);
        spelUI.refreshBackground();


    }
    public Color getAchtergrondsKleur(){
        return spelUI.getAchtergrondsKleur();
    }

}

