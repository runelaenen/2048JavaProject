package be.kdg.spel.model;

import be.kdg.spel.controller.Controller;
import be.kdg.spel.view.GeluidUI;

import java.awt.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;

/**
 * Created by Rune on 28/02/2015.
 */
public class Instellingen {
    Controller controller;

    public Instellingen(Controller controller) {
        this.controller = controller;
        leesEnActiveerInstellingen();
    }

    public void setDefault(){
        instellingenOpslaan(new Color(0xfaf8ef), true, true);
    }

    public void instellingenOpslaan(Color kleur, boolean geluid, boolean muziek) {

        try {
            BestandsSysteem.maakLeeg("instellingen.txt"); // maak bestand leeg voordat het uit te lezen
            BestandsSysteem.schrijf("instellingen.txt", "kleur;0x" + String.format("%02x%02x%02x", kleur.getRed(), kleur.getGreen(), kleur.getBlue()));
            BestandsSysteem.schrijf("instellingen.txt", "geluid;" + geluid);
            BestandsSysteem.schrijf("instellingen.txt", "muziek;" + muziek);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void leesEnActiveerInstellingen(){
        try {
            String bestand = BestandsSysteem.lees("instellingen.txt");
            if(bestand == "") {
                setDefault();
                bestand = BestandsSysteem.lees("instellingen.txt");
            }

            StringTokenizer st = new StringTokenizer(bestand, "\n");
            while(st.hasMoreTokens()){
                String regel = st.nextToken();
                if(!regel.isEmpty()){
                    String[] regelArray = regel.split("[;]");
                    switch(regelArray[0]){
                        case "kleur":
                            controller.setAchtergrondsKleur(Color.decode(regelArray[1]));
                            break;
                        case "geluid":
                            controller.geluid().setGeluid(Boolean.parseBoolean(regelArray[1]));
                            break;
                        case "muziek":
                            boolean muziek = Boolean.parseBoolean(regelArray[1]);
                            controller.geluid().setMuziek(muziek);
                            if(!muziek){
                                controller.geluid().stopAchtergrondMuziek();
                            } else {
                                //controller.geluid().stopAchtergrondMuziek(); // voor de zekerheid eventuele achtergrondmuziek stoppen zodat het zeker geen twee keer speelt
                                controller.geluid().playMusic();
                            }
                            break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
