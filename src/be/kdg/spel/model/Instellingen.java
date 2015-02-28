package be.kdg.spel.model;

import be.kdg.spel.controller.Controller;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

/**
 * Created by Rune on 28/02/2015.
 */
public class Instellingen {
    Controller controller;

    public Instellingen(Controller controller) {
        this.controller = controller;
    }

    public void setDefault(){
        instellingenOpslaan(new Color(0xfaf8ef));
    }

    public void instellingenOpslaan(Color kleur) {

        try {
            BestandsSysteem.maakLeeg("instellingen.txt"); // maak bestand leeg voordat het uit te lezen
            BestandsSysteem.schrijf("instellingen.txt", "kleur;0x" + String.format("%02x%02x%02x", kleur.getRed(), kleur.getGreen(), kleur.getBlue()));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void leesEnActiveerInstellingen(){
        try {
            String bestand = BestandsSysteem.lees("instellingen.txt");

            StringTokenizer st = new StringTokenizer(bestand, "\n");
            while(st.hasMoreTokens()){
                String regel = st.nextToken();
                if(!regel.isEmpty()){
                    String[] regelArray = regel.split("[;]");
                    switch(regelArray[0]){
                        case "kleur":
                            Color aKleur = Color.decode(regelArray[1]);
                            controller.setAchtergrondsKleur(aKleur);
                            break;

                    }
                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
