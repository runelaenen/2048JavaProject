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
    private boolean checkVerloren;

    public Tegels(Controller controller) {
        this.controller = controller;
        this.tegels = new Tegel[Controller.BORDGROOTTE];
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

    public void newGameState() {
        for (int i = 0; i < Controller.BORDGROOTTE; i++) {
            tegels[i] = new Tegel();
        }

        voegTegelToe();
        voegTegelToe();
    }

    public void saveGameState() {
        try {
            FileOutputStream fileStream = new FileOutputStream("./files/gamestate.txt");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(getTegelWaardeArray());
            objectStream.writeObject(new Integer(controller.getScore()));

            objectStream.close();
            fileStream.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Er is een fout opgetreden bij het wegschrijven van uw opgeslagen spel! Gelieve uw gamestate.txt file te verwijderen en het spel opnieuw op te starten", "Fout met de game state", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    public void loadGameState() {
        try {
            FileInputStream fileStream = new FileInputStream("./files/gamestate.txt");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            // create tegelarray
            int[] tegelWaardes = (int[]) objectStream.readObject();

            for (int i = 0; i < Controller.BORDGROOTTE; i++) {
                tegels[i] = new Tegel(tegelWaardes[i]);
            }
            controller.setScore((int) objectStream.readObject());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Er is een fout opgetreden bij het inlezen van uw opgeslagen spel! Gelieve uw gamestate.txt file te verwijderen en het spel opnieuw op te starten", "Fout met de game state", JOptionPane.ERROR_MESSAGE, null);
            //TODO: deze error komt er elke keer bij het opstarten dit zou niet mogen!
        }
    }

    public void beweegLijn() {
        boolean tegelToevoegen = false;
        for (int i = 0; i < Controller.ZIJDEGROOTTE; i++) {
            Tegel[] gewoneLijn = lijn(i);
            Tegel[] verwerkt = verwerkLijn(gewoneLijn);

            for (int j = i * Controller.ZIJDEGROOTTE, k = 0; j < (i * Controller.ZIJDEGROOTTE) + Controller.ZIJDEGROOTTE; j++, k++) {
                tegels[j] = verwerkt[k];
            }


            if (!tegelToevoegen && !Arrays.equals(gewoneLijn, verwerkt)) {
                tegelToevoegen = true;
            }
        }

        if (!checkVerloren) {
            if (checkVerloren()) {
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

    private boolean checkVerloren() {
        checkVerloren = true;

        Tegel[] kopie = new Tegel[Controller.BORDGROOTTE];
        for (int i = 0; i < tegels.length; i++) {
            kopie[i] = new Tegel(tegels[i].getWaarde());
        }

        left();
        right();
        up();
        down();

        boolean isVeranderd = false;
        for (int i = 0; i < tegels.length; i++) {
            if (kopie[i].getWaarde() != tegels[i].getWaarde()) {
                isVeranderd = true;
            }
        }

        for (int i = 0; i < tegels.length; i++) {
            tegels[i] = new Tegel(kopie[i].getWaarde());
        }

        checkVerloren = false;


        return !isVeranderd;

    }

    public Tegel[] lijn(int rij) {
        Tegel[] lijn = new Tegel[Controller.ZIJDEGROOTTE];
        for (int i = 0; i < Controller.ZIJDEGROOTTE; i++) {
            lijn[i] = tegels[rij * Controller.ZIJDEGROOTTE + i];
        }
        return lijn;
    }

    public List<Tegel> schuifLijn(Tegel[] lijn) {
        List<Tegel> nieuweLijn = new ArrayList<Tegel>();

        for (int i = 0; i < Controller.ZIJDEGROOTTE; i++) {
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



        Tegel[] mergeLijn = new Tegel[Controller.ZIJDEGROOTTE];
        for (int i = 0; i < Controller.ZIJDEGROOTTE; i++) {

            if (
                    i < Controller.ZIJDEGROOTTE - 1 // als i +1 nog in de array past
                            && !nieuweLijn.get(i).isLeeg() // en als het vakje i in de nieuwe lijn niet leeg is (0)
                    ) {
                if (nieuweLijn.get(i).getWaarde() == nieuweLijn.get(i + 1).getWaarde()) {
                    nieuweLijn.get(i + 1).setWaarde(0);
                    nieuweLijn.get(i).setWaarde(nieuweLijn.get(i).getWaarde() * 2);

                    if (!checkVerloren) {
                        controller.addScore(nieuweLijn.get(i).getWaarde() * 2);

                        if (nieuweLijn.get(i).getWaarde() == 2048 && !gewonnen) {
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
        Tegel[][] tegels2D = new Tegel[Controller.ZIJDEGROOTTE][Controller.ZIJDEGROOTTE];

        for (int rij = 0; rij < Controller.ZIJDEGROOTTE; rij++) {
            for (int kolom = 0; kolom < Controller.ZIJDEGROOTTE; kolom++) {
                tegels2D[rij][kolom] = tegels[(rij * 4) + kolom];
            }
        }

        Tegel[][] grTegels = new Tegel[Controller.ZIJDEGROOTTE][Controller.ZIJDEGROOTTE];

        for (int i = 0; i < Controller.ZIJDEGROOTTE; ++i) {
            for (int j = 0; j < Controller.ZIJDEGROOTTE; ++j) {
                grTegels[i][j] = tegels2D[Controller.ZIJDEGROOTTE - j - 1][i];
            }
        }


        // terug in tegels zetten
        for (int rij = 0; rij < Controller.ZIJDEGROOTTE; rij++) {
            for (int kolom = 0; kolom < Controller.ZIJDEGROOTTE; kolom++) {
                tegels[(rij * 4) + kolom] = grTegels[rij][kolom];
            }
        }
    }

    public Object getTegelWaardeArray() {
        int[] tegelWaardes = new int[Controller.BORDGROOTTE];
        int i = 0;
        for (Tegel tegel : tegels) {
            tegelWaardes[i] = tegel.getWaarde();
            i++;
        }
        return tegelWaardes;
    }

    public void setTegelWaardeCheat() {
        for (int i = 0; i < Controller.BORDGROOTTE; i++) {
            tegels[i] = new Tegel(2048);
        }
    }
}

