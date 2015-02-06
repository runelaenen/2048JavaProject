package be.kdg.spel.model;

import be.kdg.spel.controller.Controller;

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

    public Tegels(Controller controller){
        this.controller = controller;
        this.tegels = new Tegel[controller.BORDGROOTTE];

        for(int i = 0; i<controller.BORDGROOTTE; i++){
            tegels[i] = new Tegel();
        }

        voegTegelToe();
        voegTegelToe();
    }

    public Tegel[] getTegelArray(){
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

    private List<Tegel> legePlaatsen() {
        List<Tegel> legetegels = new ArrayList<Tegel>(16);
        for (Tegel t : tegels) {
            if (t.isLeeg()) {
                legetegels.add(t);
            }
        }
        return legetegels;
    }

    public void beweegLijn(){
        boolean tegelToevoegen = false;
        for (int i = 0; i < controller.ZIJDEGROOTTE; i++) {
            Tegel[] gewoneLijn = lijn(i);
            Tegel[] verwerkt = verwerkLijn(gewoneLijn);

            for(int j = i*controller.ZIJDEGROOTTE, k=0; j < (i*controller.ZIJDEGROOTTE)+controller.ZIJDEGROOTTE; j++, k++){
                tegels[j] = verwerkt[k];
            }


            if (!tegelToevoegen && !Arrays.equals(gewoneLijn, verwerkt)) {
                tegelToevoegen = true;
            }
        }

        if (tegelToevoegen) {
            //voegTegelToe();
            //TODO: terug uit comments halen
        }
    }

    public void left(){
        System.out.println("Left()");

        beweegLijn();
    }

    public void right(){
        System.out.println("Right()");

        this.roteerBord();
        this.roteerBord();
        beweegLijn();
        this.roteerBord();
        this.roteerBord();
    }

    public void up(){
        System.out.println("Up()");

        this.roteerBord();
        this.roteerBord();
        this.roteerBord();
        beweegLijn();
        this.roteerBord();
    }

    public void down(){
        System.out.println("Down()");

        this.roteerBord();
        beweegLijn();
        this.roteerBord();
        this.roteerBord();
        this.roteerBord();

    }

    public Tegel[] lijn(int rij){
        Tegel[] lijn = new Tegel[controller.ZIJDEGROOTTE];
        for (int i = 0; i < controller.ZIJDEGROOTTE; i++) {
            lijn[i] = tegels[rij*controller.ZIJDEGROOTTE + i];
        }
        return lijn;
    }

    public Tegel[] verwerkLijn(Tegel[] lijn){
        List<Tegel> nieuweLijn = new ArrayList<Tegel>(0);

        for(int i = 0; i<controller.ZIJDEGROOTTE; i++){
            if(!lijn[i].isLeeg()){
                nieuweLijn.add(lijn[i]);
            }
        }

        boolean kleinerDanVier = true;
        while(kleinerDanVier) {
            if (nieuweLijn.size() < 4) {
                nieuweLijn.add(new Tegel(0));
            } else {
                kleinerDanVier = false;
            }
        }


        return nieuweLijn.toArray(new Tegel[4]);

        /*
        List<Tegel> lijst = new ArrayList<Tegel>(controller.ZIJDEGROOTTE);
        for (int i = 0; i < controller.ZIJDEGROOTTE && !nieuweLijn.get(i).isLeeg(); i++) {
            int score = nieuweLijn.get(i).getWaarde();
            if (i < (controller.ZIJDEGROOTTE-1) && nieuweLijn.get(i).getWaarde() == nieuweLijn.get(i+1).getWaarde()) {
                score = score*2;
                controller.addScore(score);

                if (score == 2048) {
                    //TODO: Gewonnen!
                }
                i++;
            }
            lijst.add(new Tegel(score));
        }
        if (lijst.size() == 0) {
            return nieuweLijn.toArray(new Tegel[4]);
        } else {
            return lijst.toArray(new Tegel[4]);
        }

        //return nieuweLijn.toArray(new Tegel[4]);
        */
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
}
