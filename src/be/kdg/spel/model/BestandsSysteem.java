package be.kdg.spel.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Rune on 12/02/2015.
 */
public class BestandsSysteem {

    public static void schrijf(String filename, String inhoud) throws FileNotFoundException {
        PrintStream writer = new PrintStream(filename);
        writer.println(inhoud);
    }

    public static String lees(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        String returneer = "";
        while (scanner.hasNext()) {
            returneer += scanner.nextLine() + "\n";
        }
        return returneer;
    }
}
