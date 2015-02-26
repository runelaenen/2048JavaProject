package be.kdg.spel.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Rune on 12/02/2015.
 */
public class BestandsSysteem {

    public static void schrijf(String filename, String inhoud) throws FileNotFoundException {
        Path nieuwBestand = Paths.get("." + File.separator + "files" + File.separator + filename);
        try {
            if(!Files.exists(nieuwBestand.getParent())){
                Files.createDirectory(nieuwBestand.getParent());
            }
            if(!Files.exists(nieuwBestand)){
                Files.createFile(nieuwBestand);
            }

            List<String> gegevens = new ArrayList<String>();
            gegevens.add(inhoud);
            Files.write(nieuwBestand, gegevens, StandardOpenOption.APPEND);
        } catch(Exception ex){
            //TODO: exception uitwerken
        }
    }

    public static String lees(String filename) throws FileNotFoundException {
        String result = "";
        try(Scanner sc = new Scanner(Paths.get("." + File.separator + "files" + File.separator + filename))){
            while (sc.hasNext()) {
                String regel = sc.nextLine();
                result += regel + "\n";
            }
        }catch(IOException ex){
            result = "ERROR: FILE NOT FOUND";
        }

        return result;
    }
}
