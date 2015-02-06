package be.kdg.spel.controller;

import be.kdg.spel.model.*;
import be.kdg.spel.view.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by Rune on 4/02/2015.
 */
public class Controller {
    private Tegels tegels;
    private SpelUI spelUI;

    public static int ZIJDEGROOTTE = 4;
    public static int BORDGROOTTE = ZIJDEGROOTTE*ZIJDEGROOTTE;

    public Controller(){
        this.tegels = new Tegels(this);
        spelUI = new SpelUI(tegels.getTegelArray(), this);
    }

    public void addScore(int score){
        spelUI.addScore(score);
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



}

