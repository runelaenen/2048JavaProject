package be.kdg.spel.model;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Rune on 5/02/2015.
 */
public class Tegels {
    private Tegel[] tegels;

    private Random random = new Random();
    private Controller controller;
    private boolean gewonnen;
    private boolean speeltVerder;
    private boolean checkVerloren;

    public Tegels(Controller controller) {
        this.controller = controller;
        this.tegels = new Tegel[controller.BORDGROOTTE];
        this.checkVerloren = false;

        newGameState();
        loadGameState();
    }
    public Tegel[] getTegelArray() {
        return tegels;
    }
    public void voegTegelToe() {
        List<Tegel> list = legePlaatsen();
        if (!list.isEmpty()) {
            int index = random.nextInt(list.size());
            Tegel legeTegel = list.get(index);
            legeTegel.setWaarde(2);
        }
    }

    public void left() {
        beweegLijn();
        saveGameState();
    }
    public void right() {
        this.roteerBord(2);
        beweegLijn();
        this.roteerBord(2);
        saveGameState();
    }
    public void up() {
        this.roteerBord(3);
        beweegLijn();
        this.roteerBord();
        saveGameState();
    }
    public void down() {
        this.roteerBord();
        beweegLijn();
        this.roteerBord(3);
        saveGameState();
    }

    public void newGameState(){
        for (int i = 0; i < controller.BORDGROOTTE; i++) {
            tegels[i] = new Tegel();
        }

        voegTegelToe();
        voegTegelToe();
    }
    public void saveGameState(){
        try {
            FileOutputStream fileStream = new FileOutputStream("./files/gamestate.txt");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(getTegelWaardeArray());
            objectStream.writeObject(new Integer(controller.getScore()));

            objectStream.close();
            fileStream.close();

        } catch(Exception ex){
            System.out.println("GameState save error");
            ex.printStackTrace();
        }

    }
    public void loadGameState(){
        try {
            FileInputStream fileStream = new FileInputStream("./files/gamestate.txt");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            // create tegelarray
            int[] tegelWaardes = (int[])objectStream.readObject();

            for (int i = 0; i < controller.BORDGROOTTE; i++) {
                tegels[i] = new Tegel(tegelWaardes[i]);
            }
            controller.setScore((int)objectStream.readObject());
        } catch(Exception ex){

        }
    }

    public void beweegLijn() {
        boolean tegelToevoegen = false;
        for (int i = 0; i < controller.ZIJDEGROOTTE; i++) {
            Tegel[] gewoneLijn = lijn(i);
            Tegel[] verwerkt = verwerkLijn(gewoneLijn);

            for (int j = i * controller.ZIJDEGROOTTE, k = 0; j < (i * controller.ZIJDEGROOTTE) + controller.ZIJDEGROOTTE; j++, k++) {
                tegels[j] = verwerkt[k];
            }


            if (!tegelToevoegen && !Arrays.equals(gewoneLijn, verwerkt)) {
                tegelToevoegen = true;
            }
        }

        if(checkVerloren == false){
            if(checkVerloren()){
                controller.verloren();
            }

        }

        if (tegelToevoegen) {
            voegTegelToe();
        }
    }
    private List<Tegel> legePlaatsen() {
        List<Tegel> legetegels = new ArrayList<Tegel>(16);
        for (Tegel t : tegels) {
            if (t.isLeeg()) {
                legetegels.add(t);
            }
        }
        return legetegels;
    }
    private boolean checkVerloren(){
        checkVerloren = true;

        Tegel[] kopie = new Tegel[controller.BORDGROOTTE];
        for(int i=0; i<tegels.length; i++){
            kopie[i] = new Tegel(tegels[i].getWaarde());
        }

        left();
        right();
        up();
        down();

        boolean isVeranderd = false;
        for(int i=0; i<tegels.length; i++){
            if(kopie[i].getWaarde() != tegels[i].getWaarde()){
                isVeranderd = true;
            }
        }

        for(int i=0; i<tegels.length; i++){
            tegels[i] = new Tegel(kopie[i].getWaarde());
        }

        checkVerloren = false;


        return !isVeranderd;

    }
    public Tegel[] lijn(int rij) {
        Tegel[] lijn = new Tegel[controller.ZIJDEGROOTTE];
        for (int i = 0; i < controller.ZIJDEGROOTTE; i++) {
            lijn[i] = tegels[rij * controller.ZIJDEGROOTTE + i];
        }
        return lijn;
    }
    public List<Tegel> schuifLijn(Tegel[] lijn) {
        List<Tegel> nieuweLijn = new ArrayList<Tegel>();

        for (int i = 0; i < controller.ZIJDEGROOTTE; i++) {
            if (!lijn[i].isLeeg()) {
                nieuweLijn.add(lijn[i]);
            }
        }

        boolean kleinerDanVier = true;
        while (kleinerDanVier) {
            if (nieuweLijn.size() < 4) {
                nieuweLijn.add(new Tegel(0));
            } else {
                kleinerDanVier = false;
            }
        }
        return nieuweLijn;
    }
    public Tegel[] verwerkLijn(Tegel[] lijn) {
        List<Tegel> nieuweLijn = new ArrayList<Tegel>();
        nieuweLijn = schuifLijn(lijn);

        //return nieuweLijn.toArray(new Tegel[4]);

        Tegel[] mergeLijn = new Tegel[controller.ZIJDEGROOTTE];
        for (int i = 0; i < controller.ZIJDEGROOTTE; i++) {

            if (
                    i < controller.ZIJDEGROOTTE - 1 // als i +1 nog in de array past
                            && !nieuweLijn.get(i).isLeeg() // en als het vakje i in de nieuwe lijn niet leeg is (0)
                    ) {
                if (nieuweLijn.get(i).getWaarde() == nieuweLijn.get(i + 1).getWaarde()) {
                    nieuweLijn.get(i + 1).setWaarde(0);
                    nieuweLijn.get(i).setWaarde(nieuweLijn.get(i).getWaarde() * 2);
                    
                    if(!checkVerloren) {
                        controller.addScore(nieuweLijn.get(i).getWaarde() * 2);

                        if (nieuweLijn.get(i).getWaarde() == 2048 && gewonnen == false) {
                            gewonnen = true;
                            controller.gewonnen();
                        }
                    }
                }

            }
            mergeLijn[i] = nieuweLijn.get(i);
        }

        return schuifLijn(mergeLijn).toArray(new Tegel[4]);

    }
    public void roteerBord(int rotaties) {
        for (int i = 0; i < rotaties; i++) {
            this.roteerBord();
        }
    }
    public void roteerBord() {
        Tegel[][] tegels2D = new Tegel[controller.ZIJDEGROOTTE][controller.ZIJDEGROOTTE];

        for (int rij = 0; rij < controller.ZIJDEGROOTTE; rij++) {
            for (int kolom = 0; kolom < controller.ZIJDEGROOTTE; kolom++) {
                tegels2D[rij][kolom] = tegels[(rij * 4) + kolom];
            }
        }

        Tegel[][] grTegels = new Tegel[controller.ZIJDEGROOTTE][controller.ZIJDEGROOTTE];

        for (int i = 0; i < controller.ZIJDEGROOTTE; ++i) {
            for (int j = 0; j < controller.ZIJDEGROOTTE; ++j) {
                grTegels[i][j] = tegels2D[controller.ZIJDEGROOTTE - j - 1][i];
            }
        }


        // terug in tegels zetten
        for (int rij = 0; rij < controller.ZIJDEGROOTTE; rij++) {
            for (int kolom = 0; kolom < controller.ZIJDEGROOTTE; kolom++) {
                tegels[(rij * 4) + kolom] = grTegels[rij][kolom];
            }
        }
    }

    public Object getTegelWaardeArray() {
        int[] tegelWaardes = new int[controller.BORDGROOTTE];
        int i = 0;
        for(Tegel tegel : tegels){
            tegelWaardes[i] = tegel.getWaarde();
            i++;
        }
        return tegelWaardes;
    }
    public void setTegelWaardeCheat(){
        for(int i =0 ;i<controller.BORDGROOTTE;i++){
            tegels[i]=new Tegel(2048);
        }
    }
}

