package be.kdg.spel.model;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Created by Rune on 12/02/2015.
 */
public class BestandsSysteem {

    public static void schrijf(String filename, String inhoud) throws FileNotFoundException {
        try {
            Path nieuwBestand = Paths.get("." + File.separator + "files" + File.separator + filename);

            // dan de inhoud encrypteren adhv de key;
            // is gewoon base64, dus makkelijk te reversen maar niet gewoon in elke text editor
            inhoud = new String(Base64.getEncoder().encode(inhoud.getBytes()));

            // enkele checks doen
            if(!Files.exists(nieuwBestand.getParent())){
                Files.createDirectory(nieuwBestand.getParent());
            }
            if(!Files.exists(nieuwBestand)){
                Files.createFile(nieuwBestand);
            }

            // en het bestand wegschrijven
            List<String> gegevens = new ArrayList<String>();
            gegevens.add(inhoud);
            Files.write(nieuwBestand, gegevens, StandardOpenOption.APPEND);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Er is een fout opgetreden bij het wegschrijven van bestanden! Gelieve het spel opnieuw op te starten!","Fout bij het wegschrijven",JOptionPane.ERROR_MESSAGE,null);
        }
    }

    public static String lees(String filename) throws FileNotFoundException {
        // eerst geencrypteerde bestand uitlezen
        String result = "";
        try(Scanner sc = new Scanner(Paths.get("." + File.separator + "files" + File.separator + filename))){
            while (sc.hasNext()) {
                String regel = sc.nextLine();
                if(!regel.isEmpty()){
                    regel = new String(Base64.getDecoder().decode(regel.getBytes()));
                } else {
                    regel = "";
                }
                result += regel + "\n";
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Er is een fout opgetreden bij het lezen van uw bestanden! Gelieve het spel opnieuw op te starten","Fout bij het inlezen",JOptionPane.ERROR_MESSAGE,null);
        }

        return result;
    }

    public static void maakLeeg(String filename) throws FileNotFoundException {

        Path nieuwBestand = Paths.get("." + File.separator + "files" + File.separator + filename);
        try {
            if(!Files.exists(nieuwBestand.getParent())){
                Files.createDirectory(nieuwBestand.getParent());
            }
            if(!Files.exists(nieuwBestand)){
                Files.createFile(nieuwBestand);
            }

            List<String> gegevens = new ArrayList<String>();
            Files.write(nieuwBestand, gegevens);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Er is een fout opgetreden bij het verwerken van uw bestanden! Gelieve het spel opnieuw op te starten en de bestanden uit de map files te verwijderen!");
        }
    }
}
