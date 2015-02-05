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

    public void left(){
        boolean tegelToevoegen = false;
        for (int i = 0; i < controller.ZIJDEGROOTTE; i++) {
            Tegel[] gewoneLijn = lijn(i);
            Tegel[] verwerkt = verwerkLijn(gewoneLijn);
            //TODO: Wissel nieuwe lijn met oude lijn in tegels array

            for (int j = i*controller.ZIJDEGROOTTE, k=0; j < controller.ZIJDEGROOTTE; j++, k++) {
                tegels[j] = verwerkt[k];
            }

            if (!tegelToevoegen && !Arrays.equals(gewoneLijn, verwerkt)) {
                tegelToevoegen = true;
            }
        }

        if (tegelToevoegen) {
            voegTegelToe();
        }
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

    }
}
